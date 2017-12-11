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
    private BanDao bDao;

    public Ban unBan(User currentUser, User userToUnban) {
	// Retrieving ban first prevents redundant null return statements
	int userId = userToUnban.getId();
	Ban ban = bDao.getBanById(userId);

	if (ValidationUtil.validateAdmin(currentUser) && !ValidationUtil.validateAdmin(userToUnban) && ban != null) {
	    bDao.modifyBanStatusById(userId, "not banned");
	    bDao.modifyBannedUntilById(userId, Timestamp.valueOf(LocalDateTime.now()));
	    return ban;
	} else {
	    return null;
	}

    }

    public Ban banPermanently(User currentUser, User userToBan, String record) {
	// Ensure current user is an admin and user to ban is not
	if (ValidationUtil.validateAdmin(currentUser) && !ValidationUtil.validateAdmin(userToBan)) {
	    int userId = userToBan.getId();
	    Ban ban = bDao.getBanById(userId);

	    if (ban == null) {
		ban = bDao.addBan(ban);
	    }
	    bDao.modifyBanStatusById(userId, "permanently banned");
	    bDao.modifyBanRecordById(userId, record);
	    bDao.modifyBannedUntilById(userId, Timestamp.valueOf(LocalDateTime.of(2100, 1, 1, 1, 1, 1, 1)));
	    return ban;
	} else {
	    return null;
	}
    }

    public Ban banTemporarily(User currentUser, User userToBan, String record, int daysToBan) {
	// Ensure current user is an admin and user to ban is not
	if (ValidationUtil.validateAdmin(currentUser) && !ValidationUtil.validateAdmin(userToBan)) {
	    int banId = userToBan.getId();
	    Ban ban = bDao.getBanById(banId);

	    if (ban == null) {
		ban = bDao.addBan(ban);
	    }
	    bDao.modifyBanStatusById(banId, "temporarily banned");
	    bDao.modifyBanRecordById(banId, record);
	    bDao.modifyBannedUntilById(banId, Timestamp.valueOf(LocalDateTime.now().plusDays(daysToBan)));
	    return ban;
	} else {
	    return null;
	}
    }

    public List<Ban> getAllBans(User currentUser) {
	if (ValidationUtil.validateAdmin(currentUser)) {
	    return bDao.getAllBans();
	} else {
	    return null;
	}
    }

    public Ban findById(int banId, User currentUser) {
	Ban ban = bDao.getBanById(banId);
	log.trace("finding ban by id");
	if (ValidationUtil.validateAccess(currentUser, ban)) {
	    return ban;
	} else {
	    return null;
	}
    }

}
