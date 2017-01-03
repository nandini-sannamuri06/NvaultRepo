package com.nvault.doc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nvault.doc.dvo.UserDocDVO;
import com.nvault.doc.model.UserDoc;
import com.nvault.doc.service.DocService;
import com.nvault.doc.service.DocumentService;


@RestController
public class DocController {
	
	@Autowired
	private DocService docService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
    private ModelMapper modelMapper;
	

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDocDVO deleteDoc(@PathVariable("id") Integer id) {

		UserDoc deleteDoc = documentService.deleteDoc(id);
		return modelMapper.map(deleteDoc, UserDocDVO.class);
	}

	@RequestMapping(value = "/archieve/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDocDVO archieveDoc(@PathVariable("id") Integer id) {

		 UserDoc archieveDoc = documentService.archieveDoc(id);

		return modelMapper.map(archieveDoc, UserDocDVO.class);

	}

	@RequestMapping(value = "/getArchive", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserDocDVO> listAllArchiveDocs() {
		List<UserDocDVO> docsResults = new ArrayList<>();
		List<UserDoc> docs = documentService.getArchiveDocs();
		for (UserDoc userDoc : docs) {
			UserDocDVO userDocDVO = modelMapper.map(userDoc, UserDocDVO.class);
			docsResults.add(userDocDVO);
		}
		return docsResults;
	}

	@RequestMapping(value = "/getTrash", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserDocDVO> listAllTrashDocs() {
		List<UserDocDVO> docsResults = new ArrayList<>();
		List<UserDoc> docs = documentService.getTrashDocs();
		for (UserDoc userDoc : docs) {
			UserDocDVO userDocDVO = modelMapper.map(userDoc, UserDocDVO.class);
			docsResults.add(userDocDVO);
		}
		return docsResults;
	}

	
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
