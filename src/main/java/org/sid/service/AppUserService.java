package org.sid.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.sid.entities.AppUser;
import org.springframework.web.multipart.MultipartFile;



public interface AppUserService {

	AppUser createUser(AppUser appUser);

	List<AppUser> getAllUsers();

	AppUser getUserById(long UserId);
	
	AppUser findUserByUserName(String username);

	AppUser findUserByEmail(String email);
	
	Map<String, String> CreateUsers(MultipartFile fileJson) throws IOException;
	
	AppUser GetCurrentUser();
}
