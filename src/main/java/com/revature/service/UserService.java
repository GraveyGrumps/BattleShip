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
	@Autowired
	private ValidationUtil vu;

	public User login(User user) {
		User u = ud.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		return u;
	}

	public User create(User user) {
		log.trace("Creating user");
		log.trace("Spinning up a new winloss");
		wl = wld.addWinLoss(wl);
		s = sd.addSettings(s);
		user.setSettingsId(s.getId());
		user.setWinLossId(wl.getId());
		user.setPassword(eu.Encrypt(user.getPassword()));
		user.setHash(eu.Encrypt(user.getUsername()));
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
		if (u.getAdmin() == 1) {
			return ud.getAllUsers();
		} else {
			return null;
		}
	}

	public User modifyUser(User user, User u) {
		if (vu.validateAccess(u, user)) {
			return ud.modifyWholeUser(user);
		} else {
			return null;
		}

	}

	public User getUserById(int id, User u) {
		// Find user
		User foundUser = ud.getUserById(id);

		// Return if access permitted
		if (vu.validateAccess(u, foundUser)) {
			return foundUser;
		} else {
			return null;
		}
	}

}
