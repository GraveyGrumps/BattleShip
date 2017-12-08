package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.entities.User;
import com.revature.service.UserService;
import com.revature.util.EncryptionUtil;

@Controller
@RequestMapping("user")
// need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService us;
	
	@PostMapping("login")
	@ResponseBody
	public User login(@RequestBody User user, HttpServletRequest request) {
		User u = us.login(user);
		if (u == null)
			return null;
		else
			return u;
	}

	@PostMapping("new")
	@ResponseBody
	public User createUser(@RequestBody User user, HttpServletRequest request) {
		return us.create(user);
	}
	
	@PostMapping("all")
	@ResponseBody
	public List<User> getAll(HttpServletRequest request){
		return us.getAllUsers();
	}
	
	@PostMapping("modify")
	@ResponseBody
	public User modifyUser(@RequestBody User user,HttpServletRequest request) {
		return us.modifyUser(user);
	}
	

}
