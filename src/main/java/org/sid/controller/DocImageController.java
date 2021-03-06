package org.sid.controller;

import org.sid.dao.DocImageRepository;
import org.sid.entities.DocImage;
import org.sid.service.DocImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HttpHeaders;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class DocImageController {
	@Autowired 
	private DocImageService docImageService ;
	
	
	@Autowired
	 private  DocImageRepository docImageRepository;
	

	
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFileById(@PathVariable Long fileId){
		DocImage doc = docImageService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
}
