package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.revature.daos.UserDao;
import com.revature.entities.Settings;
import com.revature.entities.User;
import com.revature.service.SettingsService;
import com.revature.service.UserService;

@Controller
@RequestMapping("user")
// need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class UserController {

    private Logger log = Logger.getRootLogger();
    @Autowired
    private UserService us;
    @Autowired
    private SettingsService setSvc;
    @Autowired
    private UserDao usrDao;

    @PostMapping("login")
    @ResponseBody
    public User login(@RequestBody User user, HttpServletRequest request) {
	User u = us.login(user);
	if (u == null) {
	    log.trace("Invalid credentials");
	    return null;
	} else {
	    log.trace("Valid credentials");
	    log.trace("adding user");
	    log.trace(u);
	    request.getSession().setAttribute("user", u);
	    return u;
	}
    }

    @PostMapping("new")
    @ResponseBody
    public User createUser(@RequestBody User user, HttpServletRequest request) {
	return us.create(user);
    }

    @GetMapping("all")
    @ResponseBody
    public List<User> getAll(HttpServletRequest request) {
	return us.getAllUsers((User) request.getAttribute("user"));
    }

    @PutMapping("modify")
    @ResponseBody
    public User modifyUser(@RequestBody User user, HttpServletRequest request) {
	return us.modifyUser(user, (User) request.getAttribute("user"));
    }

    @GetMapping("{id}")
    @ResponseBody
    public User getUserById(@PathVariable int id, HttpServletRequest request) {
	return us.getUserById(id, (User) request.getAttribute("user"));
    }

    @GetMapping("winloss/{id}")
    @ResponseBody
    public User getUserByWinlossId(@PathVariable int id) {
	log.info("Getting user by Winloss id");
	log.trace("id is: " + id);
	return us.getUserByWinlossId(id);
    }

    @GetMapping("settings/{userId}")
    @ResponseBody
    public Settings getUserSettings(@PathVariable int userId) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	int settingsId = us.getUserById(userId, currentUser).getSettingsId();
	return setSvc.findById(settingsId, currentUser);
    }

    @PutMapping("settings/save")
    @ResponseBody
    public Settings saveUserSettings(@RequestBody Settings inputSettings) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	Settings settingsToEdit = setSvc.findById(inputSettings.getId(), currentUser);
	setSvc.modifyAllowGlobalChat(settingsToEdit, inputSettings.getGlobalChat(), currentUser);
	setSvc.modifyInGameChat(settingsToEdit, inputSettings.getInGameChat(), currentUser);
	setSvc.modifyAllowFriendRequests(settingsToEdit, inputSettings.getAcceptFriendship(), currentUser);
	setSvc.modifyAllowChallenges(settingsToEdit, inputSettings.getAllowChallenges(), currentUser);
	setSvc.modifyViewableProfile(settingsToEdit, inputSettings.getViewable(), currentUser);
	return setSvc.findById(inputSettings.getId(), currentUser);
    }
}
