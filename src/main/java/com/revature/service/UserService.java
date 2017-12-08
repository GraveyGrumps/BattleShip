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

@Service
public class UserService {
	private Logger log = Logger.getRootLogger();
	@Autowired
	UserDao ud;
	@Autowired
	WinLossDao wld;
	@Autowired
	SettingsDao sd;
	@Autowired
	WinLoss wl;
	@Autowired
	Settings s;
	@Autowired
	private EncryptionUtil eu;
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

	public List<User> getAllUsers() {
		return ud.getAllUsers();
	}

	public User modifyUser(User user) {
		return ud.modifyWholeUser(user);
	}

}
