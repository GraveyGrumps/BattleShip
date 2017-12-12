package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.SettingsDao;
import com.revature.entities.Settings;
import com.revature.entities.User;

@Service
public class SettingsService {
    @Autowired
    private SettingsDao setDao;

    public Settings modifyAllowGlobalChat(Settings settings, int value, User currentUser) {
	int settingsId = settings.getId();

	// if (ValidationUtil.validateAccess(currentUser, settings)) {
	setDao.modifyAllowGlobalChatById(settingsId, value);
	return settings;
	// } else {
	// return null;
	// }
    }

    public Settings modifyInGameChat(Settings settings, int value, User currentUser) {
	int settingsId = settings.getId();

	// if (ValidationUtil.validateAccess(currentUser, settings)) {
	setDao.modifyAllowInGameChatById(settingsId, value);
	return settings;
	// } else {
	// return null;
	// }
    }

    public Settings modifyAllowFriendRequests(Settings settings, int value, User currentUser) {
	int settingsId = settings.getId();

	// if (ValidationUtil.validateAccess(currentUser, settings)) {
	setDao.modifyAllowFriendRequestsById(settingsId, value);
	return settings;
	// } else {
	// return null;
	// }
    }

    public Settings modifyViewableProfile(Settings settings, int value, User currentUser) {
	int settingsId = settings.getId();

	// if (ValidationUtil.validateAccess(currentUser, settings)) {
	setDao.modifyViewableProfileById(settingsId, value);
	return settings;
	// } else {
	// return null;
	// }
    }

    public Settings modifyAllowChallenges(Settings settings, int value, User currentUser) {
	int settingsId = settings.getId();

	// if (ValidationUtil.validateAccess(currentUser, settings)) {
	setDao.modifyAllowChallengesById(settingsId, value);
	return settings;
	// } else {
	// return null;
	// }
    }

    public Settings findById(int settingsId, User currentUser) {
	Settings set = setDao.getSettingsById(settingsId);
	// if (ValidationUtil.validateAccess(currentUser, set)) {
	return set;
	// } else {
	// return null;
	// }
    }

}
