package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.daos.ClanDao;
import com.revature.entities.Clan;
import com.revature.entities.User;

@Controller
@RequestMapping("clan")
// need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class ClanController {
	
	@Autowired
	ClanDao cd;
	
	@PostMapping("new")
	@ResponseBody
	public Clan createClan(@RequestBody Clan clan, HttpServletRequest request) {
		return cd.addClan(clan);
	}

}
