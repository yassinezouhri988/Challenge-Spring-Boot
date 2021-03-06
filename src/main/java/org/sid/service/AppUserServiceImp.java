package org.sid.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;





@Service
@Transactional
public class AppUserServiceImp implements AppUserService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AppUserRepository personneRepository;
	
	@Autowired
	private AppUserService personneService ;
	
	@Autowired 
	private DocImageService docImageService ;
	
	@Override
	public AppUser createUser(AppUser personne) {
		String PasswCR =bCryptPasswordEncoder.encode(personne.getPassword());
		personne.setPassword(PasswCR);
		return personneRepository.save(personne);
	}

	@Override
	public List<AppUser> getAllUsers() {
		return this.personneRepository.findAll();
	}

	@Override
	public AppUser getUserById(long UserId) {
		Optional<AppUser> PersonneDb = this.personneRepository.findById(UserId);	
		if(PersonneDb.isPresent()) {
			return PersonneDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + UserId);
		}
	}

	@Override
	public AppUser findUserByUserName(String username) {
		return personneRepository.findByUsername(username);
	}

	@Override
	public AppUser findUserByEmail(String email) {
		return personneRepository.findByEmail(email);
	}

	@Override
	public Map<String, String> CreateUsers(MultipartFile fileJson) throws IOException {
		HashMap<String, String> map = new HashMap<>();		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<AppUser>> typeReference = new TypeReference<List<AppUser>>(){};
		InputStream inputStream = fileJson.getInputStream();
		int EngrEnregistrementsSec=0;int EngrEnregistrementsfail=0;
		try {
			List<AppUser> users = mapper.readValue(inputStream,typeReference);	
			for(int i=0;i<users.size();i++) {
				if(personneService.findUserByEmail(users.get(i).getEmail())==null || personneService.findUserByEmail(users.get(i).getEmail())==null) {
					String urlPhoto=docImageService.GetUserPhotoURL(docImageService.loadImageUrl(users.get(i).getAvatar()).getId());
					users.get(i).setAvatar(urlPhoto);
					this.createUser(users.get(i));
					EngrEnregistrementsSec++;
				}
				else {EngrEnregistrementsfail++;}
			}
		    map.put("Enregistrements importés avec succès",String.valueOf(EngrEnregistrementsSec) );
		    map.put("Enregistrements non importés",String.valueOf(EngrEnregistrementsfail) );
		} catch (IOException e){
			throw new ResourceNotFoundException("Unable to save users: " + e.getMessage());
		}
		return map;
	}

	@Override
	public AppUser GetCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		return personneService.findUserByUserName(username);
	}
	
	

}
