package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.SettingsDao;
import com.revature.entities.Settings;
import com.revature.entities.User;
import com.revature.util.ValidationUtil;

@Service
public class SettingsService {
    @Autowired
    private SettingsDao stDao;
    @Autowired
    private Settings st;

    public Settings modifyAllowGlobalChat(Settings settings, int value, User currentUser) {
	int settingsId = settings.getId();

	if (ValidationUtil.validateAccess(currentUser, settings)) {
	    stDao.modifyAllowGlobalChatById(settingsId, value);
	    return settings;
	} else {
	    return null;
	}
    }

    public Settings modifyInGameChat(Settings settings, int value, User currentUser) {
	int settingsId = settings.getId();

	if (ValidationUtil.validateAccess(currentUser, settings)) {
	    stDao.modifyAllowInGameChatById(settingsId, value);
	    return settings;
	} else {
	    return null;
	}
    }

    public Settings modifyAllowFriendRequests(Settings settings, int value, User currentUser) {
	int settingsId = settings.getId();

	if (ValidationUtil.validateAccess(currentUser, settings)) {
	    stDao.modifyAllowFriendRequestsById(settingsId, value);
	    return settings;
	} else {
	    return null;
	}
    }

    public Settings modifyViewableProfile(Settings settings, int value, User currentUser) {
	int settingsId = settings.getId();

	if (ValidationUtil.validateAccess(currentUser, settings)) {
	    stDao.modifyViewableProfileById(settingsId, value);
	    return settings;
	} else {
	    return null;
	}
    }

    public Settings modifyAllowChallenges(Settings settings, int value, User currentUser) {
	int settingsId = settings.getId();

	if (ValidationUtil.validateAccess(currentUser, settings)) {
	    stDao.modifyAllowChallengesById(settingsId, value);
	    return settings;
	} else {
	    return null;
	}
    }

    public Settings findById(int settingsId, User currentUser) {
	st = stDao.getSettingsById(settingsId);
	if (ValidationUtil.validateAccess(currentUser, st)) {
	    return st;
	} else {
	    return null;
	}
    }

}
