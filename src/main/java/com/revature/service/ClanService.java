package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.ClanDao;
import com.revature.entities.Clan;
import com.revature.entities.User;

@Service
public class ClanService {
    @Autowired
    private ClanDao clanDao;
    @Autowired
    private Clan clan;

    // Anyone can create a clan
    public Clan createClan(String clanName, String logoFilePath) {
	clan.setName(clanName);
	clan.setLogo(logoFilePath);
	clanDao.addClan(clan);
	return clan;
    }

    public Clan createClan(String clanName) {
	clan.setName(clanName);
	clanDao.addClan(clan);
	return clan;
    }

    // Anyone can view all clans
    public List<Clan> getAllClans() {
	return clanDao.getAllClans();
    }

    public Clan changeClanName(User currentUser, Clan clan, String newClanName) {
	int clanId = clan.getId();
	if ((currentUser.getClanId() == clanId && currentUser.getIsOfficer() == 1)
	/* || ValidationUtil.validateAdmin(currentUser) */) {
	    return clanDao.modifyClanNameById(clanId, newClanName);
	} else {
	    return null;
	}
    }

    public Clan changeClanLogo(User currentUser, Clan clan, String newLogoPath) {
	int clanId = clan.getId();
	if ((currentUser.getClanId() == clanId && currentUser.getIsOfficer() == 1)
	/* || ValidationUtil.validateAdmin(currentUser) */) {
	    return clanDao.modifyClanLogoById(clanId, newLogoPath);
	} else {
	    return null;
	}
    }

    // For now, only admins can delete a clan
    public void deleteClan(User currentUser, Clan clan) {
	// if (ValidationUtil.validateAdmin(currentUser))
	clanDao.deleteClanById(clan.getId());
    }

    public Clan getClan(int clanId) {
	return clanDao.getClanById(clanId);
    }
}
