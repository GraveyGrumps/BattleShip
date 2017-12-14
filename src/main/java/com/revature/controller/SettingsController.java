package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("settings")
// need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class SettingsController {

    @Autowired
    private UserService us;
    @Autowired
    private SettingsService setSvc;
    @Autowired
    private UserDao usrDao;

    @GetMapping("{userId}")
    @ResponseBody
    public Settings getUserSettings(@PathVariable int userId) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	int settingsId = us.getUserById(userId, currentUser).getSettingsId();
	return setSvc.findById(settingsId, currentUser);
    }

    @PutMapping("save")
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
