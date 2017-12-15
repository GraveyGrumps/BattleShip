package com.revature.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.BanDao;
import com.revature.entities.Ban;
import com.revature.entities.User;
import com.revature.util.ValidationUtil;

@Service
public class BanService {
	private Logger log = Logger.getRootLogger();
	@Autowired
	private BanDao banDao;

	public Ban unban(User currentUser, User userToUnban) {
		// Retrieving ban first prevents redundant null return statements
		int userId = userToUnban.getId();
		Ban ban = banDao.getBanById(userId);

		if (ValidationUtil.validateAdmin(currentUser) && !ValidationUtil.validateAdmin(userToUnban) && ban != null) {
			banDao.modifyBanStatusById(userId, "not banned");
			banDao.modifyBannedUntilById(userId, Timestamp.valueOf(LocalDateTime.now()));
			return banDao.getBanById(userId);
		} else {
			return null;
		}
	}

	public Ban banPermanently(User currentUser, User userToBan, String record) {
		// Ensure current user is an admin and user to ban is not
		if (ValidationUtil.validateAdmin(currentUser) && !ValidationUtil.validateAdmin(userToBan)) {
			int userId = userToBan.getId();
			Ban ban = banDao.getBanById(userId);

			if (ban == null) {
				ban = banDao.addBan(ban);
			}
			banDao.modifyBanStatusById(userId, "permanently banned");
			banDao.modifyBanRecordById(userId, record);
			banDao.modifyBannedUntilById(userId, Timestamp.valueOf(LocalDateTime.now().plusDays(36500)));
			return banDao.getBanById(userId);
		} else {
			return null;
		}
	}

	public Ban banTemporarily(User currentUser, User userToBan, String record, int daysToBan) {
		// Ensure current user is an admin and user to ban is not
		if (ValidationUtil.validateAdmin(currentUser) && !ValidationUtil.validateAdmin(userToBan)) {
			int banId = userToBan.getId();
			Ban ban = banDao.getBanById(banId);

			if (ban == null) {
				ban = banDao.addBan(ban);
			}
			banDao.modifyBanStatusById(banId, "temporarily banned");
			banDao.modifyBanRecordById(banId, record);
			banDao.modifyBannedUntilById(banId, Timestamp.valueOf(LocalDateTime.now().plusDays(daysToBan)));
			return banDao.getBanById(banId);
		} else {
			return null;
		}
	}

	public List<Ban> getAllBans(User currentUser) {
		if (ValidationUtil.validateAdmin(currentUser)) {
			return banDao.getAllBans();
		} else {
			return null;
		}
	}

	public Ban findById(int banId, User currentUser) {
		Ban ban = banDao.getBanById(banId);
		log.trace("finding ban by id");
		if (ValidationUtil.validateAccess(currentUser, ban)) {
			return ban;
		} else {
			return null;
		}
	}

}
