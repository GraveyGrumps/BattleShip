package com.revature.daos;

import com.revature.entities.Settings;

public interface SettingsDao {

	//POST
	Settings addSettings(Settings setting);
	//GET
	Settings getSettingsById(int id);
	//PUT
	boolean modifyAllowGlobalChatById(int id, int allow);
	boolean modifyAllowInGameChatById(int id, int allow);
	boolean modifyAllowFriendRequestsById(int id, int allow);
	boolean modifyAllowChallengesById(int id, int allow);
	boolean modifyViewableProfileById(int id, int allow);
	//DELETE
	boolean deleteSettingsById(int id);
}
