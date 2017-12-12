package com.revature.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.daos.UserDao;
import com.revature.entities.Friendship;
import com.revature.entities.User;
import com.revature.service.FriendshipService;
import com.revature.service.UserService;

@Controller
@RequestMapping("friend")
// need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class FriendshipController {
    Logger log = Logger.getRootLogger();
    @Autowired
    FriendshipService frSvc;
    @Autowired
    UserService usrSvc;
    @Autowired
    UserDao usrDao;

    @GetMapping("{userId}")
    @ResponseBody
    public List<User> getAllFriendsOf(@PathVariable int userId) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	User friend = usrSvc.getUserById(userId, currentUser);
	return frSvc.getAllFriendsById(currentUser, friend);
    }

    @PostMapping("create")
    @ResponseBody
    public Friendship adminCreateFriendship(@RequestBody Friendship inputFs) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	log.trace("in controller method");
	return frSvc.adminCreateFriendship(currentUser, usrSvc.getUserById(inputFs.getUser1Id(), currentUser),
		usrSvc.getUserById(inputFs.getUser2Id(), currentUser));
    }

    @DeleteMapping("destroy")
    @ResponseBody
    public ResponseEntity<Object> adminDestroyFriendship(@RequestBody Friendship inputFs) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	Friendship fsToDelete = frSvc.adminDestroyFriendship(currentUser,
		usrSvc.getUserById(inputFs.getUser1Id(), currentUser),
		usrSvc.getUserById(inputFs.getUser2Id(), currentUser));
	if (fsToDelete != null)
	    return new ResponseEntity<>(HttpStatus.OK);
	else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    }

    @PostMapping("send/{userId}")
    @ResponseBody
    public Friendship sendFriendRequest(@PathVariable int userId) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	Friendship newFs = frSvc.sendFriendRequest(currentUser, usrSvc.getUserById(userId, currentUser));
	return newFs;
    }

    @PutMapping("accept/{userId}")
    @ResponseBody
    public Friendship acceptFriendRequest(@PathVariable int userId) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	Friendship fs = frSvc.getFriendshipByIds(currentUser.getId(), userId);
	if (fs != null) {
	    return frSvc.acceptFriendRequest(currentUser, usrSvc.getUserById(userId, currentUser));
	} else {
	    return null;
	}
    }

    @PutMapping("decline/{userId}")
    @ResponseBody
    public Friendship declineFriendRequest(@PathVariable int userId) {
	// User currentUser = usrDao.getUserById(2); // admin
	// // User currentUser = request.getParameter("user");
	// Friendship newFs = frSvc.sendFriendRequest(currentUser,
	// usrSvc.getUserById(userId, currentUser));
	// return newFs;

	return null;
    }

    @DeleteMapping("remove/{userId}")
    @ResponseBody
    public ResponseEntity<Object> removeFriend(@RequestBody Friendship inputFs) {
	// User currentUser = usrDao.getUserById(2); // admin
	// // User currentUser = request.getParameter("user");
	// Friendship fsToDelete = frSvc.adminDestroyFriendship(currentUser,
	// usrSvc.getUserById(inputFs.getUser1Id(), currentUser),
	// usrSvc.getUserById(inputFs.getUser2Id(), currentUser));
	// if (fsToDelete != null)
	// return new ResponseEntity<>(HttpStatus.OK);
	// else {
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }
    }

}
