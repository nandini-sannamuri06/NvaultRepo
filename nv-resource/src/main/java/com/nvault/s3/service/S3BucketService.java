package com.nvault.s3.service;

import java.util.List;

import com.nvault.s3.model.S3Bucket;
import com.nvault.s3.model.S3Folder;

public interface S3BucketService {
	
	public S3Bucket saveBucket(S3Bucket b);
	
	public S3Bucket findByuserName(String name);
	
	public S3Folder saveFolder(S3Folder folder);
	
	public List<S3Folder> listAllFolders(String folderName);
	
}
