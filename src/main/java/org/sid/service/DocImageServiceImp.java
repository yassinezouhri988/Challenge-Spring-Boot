package org.sid.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.sid.dao.DocImageRepository;
import org.sid.entities.DocImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;





@Service
@Transactional
public class DocImageServiceImp implements DocImageService{

	 
	 @Autowired
	 private  DocImageRepository docImageRepository;
	
	@Autowired
	Environment environment;
	
	@Override
	public Optional<DocImage> getFile(Long fileId) {
		return docImageRepository.findById(fileId);
	}







	@Override
	public DocImage loadImageUrl(String url) {
		try {
            byte[] bytes = Files.readAllBytes(Paths.get(url));
            File file = new File(url);
    	    Path path = new File(file.getName()).toPath();
    	    String mimeType = Files.probeContentType(path);
            DocImage DocImage = new DocImage(mimeType,file.getName(),bytes);
            System.out.println("Done");
    	    return docImageRepository.save(DocImage); 
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not find the photo!");
        }	
	}

	@Override
	public String GetUserPhotoURL(long Id) {
		String url="http://localhost:"+environment.getProperty("local.server.port")+"/downloadFile/"+Id;
		return url;
	}

}
