package com.revature.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.SettingsDao;
import com.revature.daos.UserDao;
import com.revature.entities.Settings;
import com.revature.entities.User;
import com.revature.util.EncryptionUtil;
import com.revature.util.ValidationUtil;

@Service
public class SettingsService {
	private Logger log = Logger.getRootLogger();
	@Autowired
	private UserDao usrD;
	// @Autowired
	// private WinLossDao wld;
	@Autowired
	private SettingsDao setD;
	@Autowired
	private Settings stgs;
	@Autowired
	private EncryptionUtil encUtil;
	@Autowired
	private ValidationUtil valUtil;

	// public Settings create(Settings s) {
	// log.trace("Creating settings");
	//
	// return null;
	// }

	public List<Settings> getAll(User u) {
	 if (valUtil.validateAccess(u, 1)) {
	 }

	// public Settings modifySettings(Settings setttings, User u) {
	//
	// if (valUtil.validateAccess(u, s.)) {
	//
	// // modifyAllowGlobalChatById
	// // modifyAllowInGameChatById
	// // modifyAllowFriendRequestsById
	// // modifyAllowChallengesById
	// // modifyViewableProfileById
	//
	// } else {
	// return null;
	// }
	//
	// return null;
	// }

	public Settings findById(int settingsId, User currentUser) {
		Settings settings = setD.getSettingsById(settingsId);

		if (valUtil.validateAccess(currentUser, settings)) {
			return settings;
		} else {
			return null;
		}
	}

}
