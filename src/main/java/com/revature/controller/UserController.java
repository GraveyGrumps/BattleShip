package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.entities.User;
import com.revature.service.UserService;

@Controller
@RequestMapping("user")
//need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService us;
	
	@PostMapping("login")
	@ResponseBody
	public User login(@RequestBody User cred) {
		return us.login(cred);
	}
}
