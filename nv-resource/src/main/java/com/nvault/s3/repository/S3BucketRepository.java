package com.nvault.s3.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nvault.s3.model.S3Bucket;

@Repository
public interface S3BucketRepository extends JpaRepository<S3Bucket, Integer>{

	S3Bucket findByUserName(String name);
	

}
