package org.sid.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.sid.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;



@RestController
@RequestMapping("/api/users")
@Api(value = "HelloWorld Resource", description = "User's operations")
public class AppUserController {

	@Autowired
	private AppUserRepository personneRepository;
	
	@Autowired
	private AppUserService personneService ;
	
	@RequestMapping(value = "/generate", method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<InputStreamResource> downloadJsonFile1(@RequestParam(value = "count", required = true) int count )throws IOException, ParseException {
		List<AppUser> personnes = personneRepository.findAll();
		List<AppUser> Listpersonnes= new ArrayList<>();

		for(int i=0;i<personnes.size();i++) {
			if(i<count && count<=personnes.size()) {
				Listpersonnes.add(personnes.get(i));
			}else if(personnes.size()<=count){
				Listpersonnes.add(personnes.get(i));
			}
		}
		
		for(int i=0;i<Listpersonnes.size();i++) {
			String date_s = Listpersonnes.get(i).getBirthDate().toString(); 
			SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
			java.util.Date date = dt.parse(date_s); 
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-mm-dd");
			Listpersonnes.get(i).setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(Listpersonnes.get(i).getBirthDate().toString()));
			System.out.println(Listpersonnes.get(i).getBirthDate());
		}
		
		
		
		ObjectMapper mapper= new ObjectMapper();
		byte[] buf = mapper.writeValueAsBytes(Listpersonnes);
	    return ResponseEntity
	            .ok()
	            .contentLength(buf.length)
	            .header("Content-Disposition", "attachment; filename=\"users.json\"")
	            .contentType(MediaType.parseMediaType("application/json"))
	            .body(new InputStreamResource(new ByteArrayInputStream(buf)));
	}
	
	@PostMapping("/batch")
	public ResponseEntity<Map<String, String>> UploadUsersInfo(@RequestParam("jsonfile") MultipartFile files) throws IOException, URISyntaxException {
		HashMap<String, String> map = (HashMap<String, String>) personneService.CreateUsers(files);		
		return ResponseEntity.ok().body(map);
	}
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public ResponseEntity<AppUser> GetMe(){
		return ResponseEntity.ok().body(personneService.GetCurrentUser());
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<AppUser> GetInfoUser(@PathVariable String username){
		return ResponseEntity.ok().body(personneService.findUserByUserName(username));
	}
	
}
