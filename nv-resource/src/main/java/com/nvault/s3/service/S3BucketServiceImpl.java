package com.nvault.s3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvault.s3.model.S3Bucket;
import com.nvault.s3.repository.S3BucketRepository;

@Service
public class S3BucketServiceImpl implements S3BucketService{
	
	@Autowired
	public S3BucketRepository bucketRepository;

	@Override
	public S3Bucket saveBucket(S3Bucket b) {
		return bucketRepository.save(b);
	}

	@Override
	public S3Bucket findByuserName(String name) {
		return bucketRepository.findByUserName(name);
	}

}
