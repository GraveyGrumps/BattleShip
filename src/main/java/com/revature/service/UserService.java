package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.SettingsDao;
import com.revature.daos.UserDao;
import com.revature.daos.WinLossDao;
import com.revature.entities.Settings;
import com.revature.entities.User;
import com.revature.entities.WinLoss;
import com.revature.util.EncryptionUtil;
import com.revature.util.ValidationUtil;

@Service
public class UserService {
    private Logger log = Logger.getRootLogger();
    @Autowired
    private UserDao ud;
    @Autowired
    private WinLossDao wld;
    @Autowired
    private SettingsDao sd;
    @Autowired
    private WinLoss wl;
    @Autowired
    private Settings s;
    @Autowired
    private EncryptionUtil eu;

    public User login(User user) {
	User u = ud.getUserByUsernameAndPassword(user.getUsername(), eu.Encrypt(user.getPassword()));
	return u;
    }

    public User create(User user) {
	log.trace("Creating user: " + user);
	log.trace("Spinning up a new winloss");
	wl = wld.addWinLoss(wl);
	s = sd.addSettings(s);
	user.setSettingsId(s.getId());
	user.setWinLossId(wl.getId());
	user.setPassword(eu.Encrypt(user.getPassword()));
	user.setHash(eu.Encrypt(user.getUsername()));
	user.setIsOfficer(0);
	user.setAdmin(0);
	user.setClanId(1);
	user.setVerified(0);
	try {
	    ud.addUser(user);
	} catch (Exception e) {
	    log.trace("Invalid User.  Cannot Create");
	    wld.deleteWinLossById(wl.getId());
	    sd.deleteSettingsById(s.getId());
	    return null;
	}
	return user;
    }

    public List<User> getAllUsers(User u) {
	//if (u.getAdmin() == 1) {
	    return ud.getAllUsers();
	//} else {
	//    return null;
	//}
    }

    public User modifyUser(User user, User u) {
	if (ValidationUtil.validateAccess(u, user)) {
	    return ud.modifyWholeUser(user);
	} else {
	    return null;
	}

    }

    public User getUserById(int id, User u) {
	// Find user
	User foundUser = ud.getUserById(id);
	// Return if access permitted
	// if (vu.validateAccess(u, foundUser)) {
	return foundUser;
	// } else {
	// return null;
	// }
    }

    public User getUserByWinlossId(int id) {

	return ud.getUserByWinlossId(id);
    }

	public int getWL(int id) {
		// TODO Auto-generated method stub
		return ud.getUserById(id).getWinLossId();
	}

}
