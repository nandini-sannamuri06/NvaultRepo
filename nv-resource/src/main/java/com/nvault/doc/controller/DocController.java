package com.nvault.doc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nvault.doc.model.UserDoc;
import com.nvault.doc.service.DocService;
@RestController
public class DocController {
	
	@Autowired
	private DocService docService;
	
	@Autowired
	private Environment env;
	
	/*Doc Upload*/
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<?> uploadFile(
	      @RequestParam("file") MultipartFile uploadfile) {
	    try {
	      // Get the filename and build the local file path
	      String filename = uploadfile.getOriginalFilename();
	      String directory = env.getProperty("nv-path");
	      String filepath = Paths.get(directory, filename).toString();
	      // Save the file locally
	      UserDoc userDoc=new UserDoc();
	      userDoc.setFileName(filename);
	      userDoc.setPath(directory);
	      userDoc.setType(filename.split("//.")[1]);
	      BufferedOutputStream stream =
	          new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	      stream.write(uploadfile.getBytes());
	      stream.close();
	      docService.saveFile(userDoc);
	    }
	    catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	    
	    return new ResponseEntity<>(HttpStatus.OK);
	  } // method uploadFile
}
