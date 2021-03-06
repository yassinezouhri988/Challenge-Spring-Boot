package org.sid.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.sid.entities.DocImage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;



public interface DocImageService {
	Optional<DocImage> getFile(Long fileId);
	DocImage loadImageUrl(String url);
	String GetUserPhotoURL(long Id);
}
