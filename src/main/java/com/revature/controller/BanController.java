package com.revature.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.daos.BanDao;
import com.revature.daos.UserDao;
import com.revature.entities.Ban;
import com.revature.entities.User;
import com.revature.service.BanService;
import com.revature.service.UserService;

@Controller
@RequestMapping("ban")
// need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class BanController {
    Logger log = Logger.getRootLogger();
    @Autowired
    BanDao banDao;
    @Autowired
    BanService banSvc;
    @Autowired
    UserService usrSvc;
    @Autowired
    UserDao usrDao;

    @GetMapping("all")
    @ResponseBody
    public List<Ban> getAllBans() {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	return banSvc.getAllBans(currentUser);
    }

    @GetMapping("{userId}")
    @ResponseBody
    public Ban getClan(@PathVariable int userId) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	Ban banToFind = banSvc.findById(userId, currentUser);
	if (banToFind != null) {
	    return banToFind;
	} else {
	    return null;
	}
    }

    @PostMapping("perm")
    @ResponseBody
    public Ban banPermanently(@RequestBody Ban inputBan) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	User userToBan = usrSvc.getUserById(inputBan.getUserId(), currentUser);
	return banSvc.banPermanently(currentUser, userToBan, inputBan.getRecord());
    }

    @PostMapping("temp/{numDays}")
    @ResponseBody
    public Ban banTemporarily(@RequestBody Ban inputBan, @PathVariable int numDays) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	User userToBan = usrSvc.getUserById(inputBan.getUserId(), currentUser);
	return banSvc.banTemporarily(currentUser, userToBan, inputBan.getRecord(), numDays);
    }

    @PutMapping("unban")
    @ResponseBody
    public Ban unban(@RequestBody Ban inputBan) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	User userToUnban = usrSvc.getUserById(inputBan.getUserId(), currentUser);
	return banSvc.unban(currentUser, userToUnban);
    }
}
