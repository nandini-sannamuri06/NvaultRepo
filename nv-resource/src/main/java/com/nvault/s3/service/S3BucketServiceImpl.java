package com.nvault.s3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvault.s3.model.S3Bucket;
import com.nvault.s3.model.S3Folder;
import com.nvault.s3.repository.S3BucketRepository;
import com.nvault.s3.repository.S3FolderRepository;

@Service
public class S3BucketServiceImpl implements S3BucketService{
	
	@Autowired
	public S3BucketRepository bucketRepository;
	
	@Autowired
	public S3FolderRepository folderRepository;

	@Override
	public S3Bucket saveBucket(S3Bucket b) {
		return bucketRepository.save(b);
	}

	@Override
	public S3Bucket findByuserName(String name) {
		return bucketRepository.findByUserName(name);
	}

	@Override
	public S3Folder saveFolder(S3Folder folder) {
		return folderRepository.save(folder);
	}

	@Override
	public List<S3Folder> listAllFolders(String folderName) {
		return folderRepository.findByBaseFolder(folderName);
	}

}
