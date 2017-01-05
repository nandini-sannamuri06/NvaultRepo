package com.nvault.s3.service;

import com.nvault.s3.model.S3Bucket;

public interface S3BucketService {
	
	public S3Bucket saveBucket(S3Bucket b);
	
	public S3Bucket findByuserName(String name);
}
