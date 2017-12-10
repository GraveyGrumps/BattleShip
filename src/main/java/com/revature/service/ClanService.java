package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.daos.ClanDao;
import com.revature.daos.UserDao;
import com.revature.entities.Clan;
import com.revature.entities.User;
import com.revature.util.ValidationUtil;

public class ClanService {
    @Autowired
    private UserDao usDao;
    @Autowired
    private ClanDao clDao;
    @Autowired
    private Clan cl;

    // Anyone can create a clan
    public Clan createClan(String clanName, String logoFilePath) {
	cl.setName(clanName);
	cl.setLogo(logoFilePath);
	clDao.addClan(cl);
	return cl;
    }

    // Anyone can view all clans
    public List<Clan> getAllClans() {
	return clDao.getAllClans();
    }

    public Clan changeClanName(User currentUser, Clan clan, String newClanName) {
	int clanId = clan.getId();
	if (currentUser.getClanId() == clanId && currentUser.getIsOfficer() == 1) {
	    clDao.modifyClanNameById(clanId, newClanName);
	    return clan;
	} else {
	    return null;
	}
    }

    public Clan changeClanLogo(User currentUser, Clan clan, String newLogoPath) {
	int clanId = clan.getId();
	if (currentUser.getClanId() == clanId && currentUser.getIsOfficer() == 1) {
	    clDao.modifyClanLogoById(clanId, newLogoPath);
	    return clan;
	} else {
	    return null;
	}
    }

    // For now, only admins can delete a clan
    public void deleteClan(User currentUser, Clan clan) {
	if (ValidationUtil.validateAdmin(currentUser)) {
	    clDao.deleteClanById(clan.getId());
	}
    }
}
