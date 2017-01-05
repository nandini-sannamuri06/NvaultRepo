package com.nvault.s3.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
	static {

	}

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

	@RequestMapping(value = "/fetchDocs", method = RequestMethod.GET)
	public ResponseEntity<List<UserDocDVO>> getDocs(@RequestParam("folderType") String folderName) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NVaultUser user = (NVaultUser) auth.getPrincipal();
		S3Bucket bucket = bucketService.findByuserName(user.getUsername());
		AWSCredentials credentials = new BasicAWSCredentials(env.getProperty("accessKey"),
				env.getProperty("securityKey"));
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		Iterator<S3ObjectSummary> it = s3Client.listObjects(bucket.getBucketName()).getObjectSummaries().iterator();
		List<UserDocDVO> userDocs = new ArrayList<UserDocDVO>();
		while (it.hasNext()) {
			UserDocDVO userDocDVO = new UserDocDVO();
			S3ObjectSummary summary = (S3ObjectSummary) it.next();
			userDocDVO.setFileName(summary.getKey());
			userDocDVO.setModifiedDate(summary.getLastModified());
			userDocDVO.setSize(summary.getSize());
			userDocs.add(userDocDVO);
		}
		return new ResponseEntity<List<UserDocDVO>>(userDocs, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/uploadDocs", method = RequestMethod.POST)
	public ResponseEntity<String> uploadDocs(@RequestBody File f) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NVaultUser user = (NVaultUser) auth.getPrincipal();
		S3Bucket bucket = bucketService.findByuserName(user.getUsername());
		AmazonS3 s3Client = new AmazonS3Client(
				new BasicAWSCredentials(env.getProperty("accessKey"), env.getProperty("securityKey")));
		if (bucket != null) {
			try {
				String fileName = f.getName();
				s3Client.putObject(bucket.getBucketName(), "home/" + fileName, f);
				return new ResponseEntity<String>("File Uploaded SuccessFully", HttpStatus.CREATED);
			} catch (Exception e) {
				System.out.println("Exception occured in uploading file" + e.getMessage());
				return new ResponseEntity<String>("File is not Uploaded", HttpStatus.BAD_REQUEST);
			}
		} else {
			creationProcess(user.getMail().split("@")[0], user.getUsername());
			String fileName = f.getName();
			s3Client.putObject(bucket.getBucketName(), "home/" + fileName, f);
			return new ResponseEntity<String>("File Uploaded SuccessFully", HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/updateDocs", method = RequestMethod.PUT)
	public ResponseEntity<String> moveToTrash(@RequestBody HashMap<String, Object> docMap) {
		File file = (File) docMap.get("file");
		String bucketName = docMap.get("bucketName").toString();
		String fileName = file.getName();
		String folderName = docMap.get("folderName").toString();
		String status = deletionProcess(fileName, file, bucketName, folderName);
		if ("success".equalsIgnoreCase(status)) {
			return new ResponseEntity<String>("Successfully Moved to" + folderName, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Document is not Moved to" + folderName, HttpStatus.BAD_REQUEST);
		}

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
	public String deletionProcess(String fileName, File file, String bucketName, String folderName) {
		System.out.println(folderName + "/" + fileName);
		AmazonS3 s3Client = new AmazonS3Client(
				new BasicAWSCredentials(env.getProperty("accessKey"), env.getProperty("securityKey")));
		try {
			s3Client.putObject(bucketName, folderName + "/" + fileName, file);
			s3Client.deleteObject(bucketName + "/home", fileName);
			return "success";
		} catch (Exception e) {
			return "failure";
		}

	}
}
