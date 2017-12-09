package com.revature.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.SettingsDao;
import com.revature.daos.UserDao;
import com.revature.entities.Settings;
import com.revature.entities.User;
import com.revature.util.ValidationUtil;

@Service
public class SettingsService {
	private Logger log = Logger.getRootLogger();
	@Autowired
	private UserDao usrD;
	@Autowired
	private SettingsDao setD;
	@Autowired
	private Settings stgs;
	@Autowired
	private ValidationUtil valUtil;

	// public Settings create(Settings s) {
	// log.trace("Creating settings");
	//
	// return null;
	// }

	// public List<Settings> getAll(User u) {
	// if (u.getAdmin() == 1) {
	// return setD.get();
	// } else {
	// return null;
	// }
	// }

	public Settings modifyAllowGlobalChat(Settings settings, int value, User currentUser) {
		int settingsId = settings.getId();

		if (valUtil.validateAccess(currentUser, settings)) {
			setD.modifyAllowGlobalChatById(settingsId, value);
			log.trace("modified allow global chat");
			return settings;
		} else {
			return null;
		}
	}

	public Settings modifyInGameChat(Settings settings, int value, User currentUser) {
		int settingsId = settings.getId();

		if (valUtil.validateAccess(currentUser, settings)) {
			setD.modifyAllowInGameChatById(settingsId, value);
			log.trace("modified in-game chat");
			return settings;
		} else {
			return null;
		}
	}

	public Settings modifyAllowFriendRequests(Settings settings, int value, User currentUser) {
		int settingsId = settings.getId();

		if (valUtil.validateAccess(currentUser, settings)) {
			setD.modifyAllowFriendRequestsById(settingsId, value);
			log.trace("modified allow friend requests");
			return settings;
		} else {
			return null;
		}
	}

	public Settings modifyViewableProfile(Settings settings, int value, User currentUser) {
		int settingsId = settings.getId();

		if (valUtil.validateAccess(currentUser, settings)) {
			setD.modifyViewableProfileById(settingsId, value);
			log.trace("modified viewable profile");
			return settings;
		} else {
			return null;
		}
	}

	public Settings modifyAllowChallenges(Settings settings, int value, User currentUser) {
		int settingsId = settings.getId();

		if (valUtil.validateAccess(currentUser, settings)) {
			setD.modifyAllowChallengesById(settingsId, value);
			log.trace("modified allow challenges");
			return settings;
		} else {
			return null;
		}
	}

	public Settings findById(int settingsId, User currentUser) {
		Settings settings = setD.getSettingsById(settingsId);

		if (valUtil.validateAccess(currentUser, settings)) {
			return settings;
		} else {
			return null;
		}
	}

}
