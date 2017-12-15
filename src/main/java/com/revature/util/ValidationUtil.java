package com.revature.util;

import org.springframework.stereotype.Component;

import com.revature.entities.Ban;
import com.revature.entities.Clan;
import com.revature.entities.Friendship;
import com.revature.entities.Settings;
import com.revature.entities.User;

@Component
public class ValidationUtil {

	/**
	 * Checks whether or not a user has access to another user.
	 * 
	 * @param currentUser
	 *            The user currently logged in.
	 * @param targetUser
	 *            The user object attempting to be accessed.
	 * @return True whether or not access is permitted, false whether or not access
	 *         denied.
	 */
	public static boolean validateAccess(User currentUser, User targetUser) {
		return (currentUser.getId() == targetUser.getId() || currentUser.getAdmin() == 1);
	}

	/**
	 * Checks whether or not a user has access to a settings object.
	 * 
	 * @param currentUser
	 *            The user currently logged in.
	 * @param targetSettings
	 *            The settings object attempting to be accessed.
	 * @return True whether or not access is permitted, false whether or not access
	 *         denied.
	 */
	public static boolean validateAccess(User currentUser, Settings targetSettings) {
		return (currentUser.getSettingsId() == targetSettings.getId() || currentUser.getAdmin() == 1);
	}

	/**
	 * Checks whether or not a user has access to a ban object.
	 * 
	 * @param currentUser
	 *            The user currently logged in.
	 * @param targetBan
	 *            The ban object attempting to be accessed.
	 * @return
	 */
	public static boolean validateAccess(User currentUser, Ban targetBan) {
		return (currentUser.getId() == targetBan.getUserId() || currentUser.getAdmin() == 1);
	}

	/**
	 * Checks whether or not a user has access to a clan object.
	 * 
	 * @param currentUser
	 *            The user currently logged in.
	 * @param targetClan
	 *            The clan object attempting to be accessed.
	 * @return
	 */
	public static boolean validateAccess(User currentUser, Clan targetClan) {
		return (currentUser.getClanId() == targetClan.getId() || currentUser.getAdmin() == 1);
	}

	/**
	 * Checks whether or not a user has access to a friendship object.
	 * 
	 * @param currentUser
	 *            The user currently logged in.
	 * @param targetFriendship
	 *            The friendship object attempting to be accessed.
	 * @return
	 */
	public static boolean validateAccess(User currentUser, Friendship targetFriendship) {
		int currentUserId = currentUser.getId();
		return (currentUserId == targetFriendship.getUser1Id() || currentUserId == targetFriendship.getUser2Id()
				|| currentUser.getAdmin() == 1);
	}

	/**
	 * Checks whether or not a user is an admin.
	 * 
	 * @param checkedUser
	 *            The user being checked for admin privileges.
	 * @return True if checkedUser is an admin, otherwise false.
	 */
	public static boolean validateAdmin(User checkedUser) {
		return (checkedUser.getAdmin() == 1);
	}
}
