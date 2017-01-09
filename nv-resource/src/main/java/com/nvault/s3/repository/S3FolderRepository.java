package com.nvault.s3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nvault.s3.model.S3Folder;
@Repository
public interface S3FolderRepository extends JpaRepository<S3Folder, Integer>{

	List<S3Folder> findByBaseFolder(String folderName);

}
