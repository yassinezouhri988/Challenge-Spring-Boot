package org.sid.controller;

import org.sid.entities.AppUser;
import org.sid.entities.AthUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class AccountRestController {

	
    @PostMapping("/auth")
	public void fakeLogin(@RequestBody AthUser user) {}
}
