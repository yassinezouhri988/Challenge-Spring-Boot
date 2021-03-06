package org.sid.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	private AccountService accountService;
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String UsernameEmail=username;
		AppUser user1=accountService.loadUserByUsername(UsernameEmail);
		AppUser user2=accountService.loadUserByEmail(UsernameEmail);
		if(user1==null && user2==null) {
			throw new UsernameNotFoundException(username);
		};
		Collection<GrantedAuthority> authorities =new ArrayList<>();
		if(user1!=null) {
			authorities.add(new SimpleGrantedAuthority(user1.getRole()));
			return new User(user1.getUsername(),user1.getPassword() , authorities); 
		}else if(user2!=null) {
			authorities.add(new SimpleGrantedAuthority(user2.getRole()));
			return new User(user2.getUsername(),user2.getPassword() , authorities); 
		}
		return null;
	}
	

}
