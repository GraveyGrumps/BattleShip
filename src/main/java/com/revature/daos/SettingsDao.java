package com.revature.daos;

import com.revature.entities.Settings;

public interface SettingsDao {

	//POST
	Settings addSettings(Settings setting);
	//GET
	Settings getSettingsById(int id);
	//PUT
	boolean changeAllSettingsById(int id, Settings setting);
	boolean changeAllowGlobalChatById(int id, int allow);
	boolean changeAllowInGameChatById(int id, int allow);
	boolean changeAllowFriendRequestsById(int id, int allow);
	boolean changeAllowChallengesById(int id, int allow);
	boolean changeViewableProfileById(int id, int allow);
	//DELETE
	boolean deleteSettingsById(int id);
	boolean deleteSettingsBySettings(Settings setting);
}
