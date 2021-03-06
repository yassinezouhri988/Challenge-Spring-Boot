package org.sid.service;

import javax.transaction.Transactional;

import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	public AccountServiceImpl(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}



	@Override
	public AppUser saveUser(AppUser user) {

		String PasswCR =bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(PasswCR);
		return appUserRepository.save(user);
	}


	@Override
	public AppUser loadUserByEmail(String email) {
		return appUserRepository.findByEmail(email);
	}

}
