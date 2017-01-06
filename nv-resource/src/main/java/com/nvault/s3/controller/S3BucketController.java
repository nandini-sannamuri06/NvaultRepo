package com.nvault.s3.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.nvault.doc.dvo.UserDocDVO;
import com.nvault.model.NVaultUser;
import com.nvault.s3.model.S3Bucket;
import com.nvault.s3.service.S3BucketService;

@RestController
public class S3BucketController {

	@Autowired
	public Environment env;
	@Autowired
	public S3BucketService bucketService;

	@RequestMapping(value = "/createBucket", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> createBucket(@RequestBody HashMap<String, String> userMap) {
		try {
			new Thread(new Runnable() {
				public void run() {
					creationProcess(userMap.get("bucketName"), userMap.get("userName"));
				}
			}).start();
		} catch (Exception e) {
			System.out.println("Exception Occured in bucketCreation" + e.getMessage());
		}
		return new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

	}

	@RequestMapping(value = "/fetchDocs", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	//Need to write logic for fetching the Folders.
	public ResponseEntity<List<UserDocDVO>> getDocs(@RequestParam("folderName") String folderName) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NVaultUser user = (NVaultUser) auth.getPrincipal();
		S3Bucket bucket = bucketService.findByuserName(user.getUsername());
		AWSCredentials credentials = new BasicAWSCredentials(env.getProperty("accessKey"),
				env.getProperty("securityKey"));
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucket.getBucketName())
                .withPrefix(folderName+"/");
       ObjectListing objects = s3Client.listObjects(listObjectsRequest);
		List<UserDocDVO> userDocs = new ArrayList<UserDocDVO>();
		List<S3ObjectSummary> list = objects.getObjectSummaries();
		for(S3ObjectSummary summary : list) {
			if(!summary.getKey().endsWith("/")){
			UserDocDVO userDocDVO = new UserDocDVO();
			userDocDVO.setFileName(summary.getKey().split("/")[1]);
			userDocDVO.setModifiedDate(summary.getLastModified());
			userDocDVO.setSize(summary.getSize()/1024);
			userDocs.add(userDocDVO);
			}
		}
		System.out.println("userDocs"+userDocs);
		return new ResponseEntity<List<UserDocDVO>>(userDocs, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/uploadDocs", method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> uploadDocs(@RequestParam("file") MultipartFile f) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NVaultUser user = (NVaultUser) auth.getPrincipal();
		S3Bucket bucket = bucketService.findByuserName(user.getUsername());
		AmazonS3 s3Client = new AmazonS3Client(
				new BasicAWSCredentials(env.getProperty("accessKey"), env.getProperty("securityKey")));
		if (bucket != null) {
			try {
				InputStream stream = new ByteArrayInputStream(f.getBytes());
	            ObjectMetadata meta = new ObjectMetadata();
	            meta.setContentLength(f.getSize());
				s3Client.putObject(bucket.getBucketName(), "home/"+f.getOriginalFilename(), stream, meta);
				return new ResponseEntity<String>("File Uploaded SuccessFully", HttpStatus.CREATED);
			} catch (Exception e) {
				System.out.println("Exception occured in uploading file" + e.getMessage());
				return new ResponseEntity<String>("File is not Uploaded", HttpStatus.BAD_REQUEST);
			}
		} else {
			creationProcess(user.getMail().split("@")[0], user.getUsername());
			InputStream stream = new ByteArrayInputStream(f.getBytes());
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(f.getSize());
			s3Client.putObject(bucket.getBucketName(), "home/"+f.getOriginalFilename(), stream, meta);
			return new ResponseEntity<String>("File Uploaded SuccessFully", HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/updateDocs", method = RequestMethod.GET,produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> updateDocs(@RequestParam("fileName") String fileName, @RequestParam("folderName") String folderName) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NVaultUser user = (NVaultUser) auth.getPrincipal();
		S3Bucket bucket = bucketService.findByuserName(user.getUsername());
		String status = deletionProcess(fileName,bucket.getBucketName(), folderName);
		if ("success".equalsIgnoreCase(status)) {
			return new ResponseEntity<String>("Successfully Moved to" + folderName, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Document is not Moved to" + folderName, HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value="/createFolder", method = RequestMethod.POST)
	public void createFolder(){
		
	}

	/**
	 * @param bucketName
	 * @param userName
	 *            This Method is used for creation of Bucket in Amazon s3.
	 */
	public void creationProcess(String bucketName, String userName) {
		S3Bucket bucket = new S3Bucket();
		bucket.setBucketName(bucketName);
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		AmazonS3 s3Client = new AmazonS3Client(
				new BasicAWSCredentials(env.getProperty("accessKey"), env.getProperty("securityKey")));
		try {
			s3Client.createBucket(bucketName);
			bucket.setUserName(userName);
		} catch (Exception e) {
			bucket = null;
			System.out.println("bucket already Exists" + e.getMessage());
		}
		try {
			// create empty content
			InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
			// create a PutObjectRequest passing the folder name suffixed by
			// /
			PutObjectRequest homeObjReq = new PutObjectRequest(bucketName, env.getProperty("homePath"), emptyContent,
					metadata);
			PutObjectRequest trashObjReq = new PutObjectRequest(bucketName, env.getProperty("trashPath"), emptyContent,
					metadata);
			PutObjectRequest archiveObjReq = new PutObjectRequest(bucketName, env.getProperty("archivePath"),
					emptyContent, metadata);

			// send request to S3 to create folder
			s3Client.putObject(homeObjReq);
			s3Client.putObject(trashObjReq);
			s3Client.putObject(archiveObjReq);
			bucket.setUserName(userName);
			bucketService.saveBucket(bucket);
		} catch (Exception e) {
			bucket = null;
			System.out.println("bucket already Exists" + e.getMessage());
		}

	}

	/**
	 * @param fileName
	 * @param file
	 * @param bucketName
	 *            This method is used to move the Document to Trash/Archive.
	 */
	public String deletionProcess(String fileName,String bucketName, String folderName) {
		System.out.println(folderName + "/" + fileName);
		AmazonS3 s3Client = new AmazonS3Client(
				new BasicAWSCredentials(env.getProperty("accessKey"), env.getProperty("securityKey")));
		try {
			// Need to get the File from Home Location.
			S3Object object = s3Client.getObject(new GetObjectRequest(bucketName+"/home", fileName));
			InputStream is = object.getObjectContent();
			s3Client.putObject(bucketName, folderName+"/"+fileName, is, object.getObjectMetadata());
			s3Client.deleteObject(bucketName + "/home", fileName);
			return "success";
		} catch (Exception e) {
			return "failure";
		}

	}
}
