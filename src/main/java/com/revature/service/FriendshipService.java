package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.FriendshipDao;
import com.revature.daos.UserDao;
import com.revature.entities.Friendship;
import com.revature.entities.User;
import com.revature.util.ValidationUtil;

@Service
public class FriendshipService {
    Logger log = Logger.getRootLogger();

    @Autowired
    private FriendshipDao frDao;
    @Autowired
    private UserDao usrDao;
    @Autowired
    private Friendship frsp;

    public Friendship sendFriendRequest(User currentUser, User otherUser) {
	int friend1Id = currentUser.getId();
	int friend2Id = otherUser.getId();
	if (ValidationUtil.validateAccess(currentUser, frsp) && frDao.getFriendshipByIds(friend1Id, friend2Id) == null
		&& friend1Id != friend2Id) {
	    frsp.setUser1Id(friend1Id);
	    frsp.setUser2Id(friend2Id);
	    frsp.setPending(1);
	    frDao.addFriendship(frsp);
	    return frsp;
	}
	return null;
    }

    public Friendship acceptFriendRequest(User currentUser, User otherUser) {
	int friend1Id = currentUser.getId();
	int friend2Id = otherUser.getId();
	frsp = frDao.getFriendshipByIds(friend1Id, friend2Id);
	if (ValidationUtil.validateAccess(currentUser, frsp) && frsp != null) {
	    log.trace("in frSvc method");

	    // TODO Instead of the line below, add a function in the FriendshipDaoHibernate
	    // (see TODO in FriendshipDaoHibernate)
	    frsp.setPending(0);
	    return frsp;
	}
	return null;
    }

    public void declineFriendRequest(User currentUser, User otherUser) {
	int friend1Id = currentUser.getId();
	int friend2Id = otherUser.getId();
	frsp = frDao.getFriendshipByIds(friend1Id, friend2Id);
	if (ValidationUtil.validateAccess(currentUser, frsp) && frsp != null) {
	    frDao.deleteByIds(friend1Id, friend2Id);
	}
    }

    public void removeFriend(User currentUser, User otherUser) {
	int friend1Id = currentUser.getId();
	int friend2Id = otherUser.getId();
	frsp = frDao.getFriendshipByIds(friend1Id, friend2Id);
	if (ValidationUtil.validateAccess(currentUser, frsp)
		&& frDao.getFriendshipByIds(friend1Id, friend2Id) != null) {
	    frDao.deleteByIds(friend1Id, friend2Id);
	}
    }

    public Friendship adminCreateFriendship(User currentUser, User friend1, User friend2) {
	int friend1Id = friend1.getId();
	int friend2Id = friend2.getId();
	if (ValidationUtil.validateAdmin(currentUser) && friend1Id != friend2Id
		&& frDao.getFriendshipByIds(friend1Id, friend2Id) == null
		&& frDao.getFriendshipByIds(friend2Id, friend1Id) == null) {
	    frsp.setUser1Id(friend1Id);
	    frsp.setUser2Id(friend2Id);
	    frsp.setPending(0);
	    frsp.setChatLog("");
	    frDao.addFriendship(frsp);
	    log.trace("frsp :" + frsp);
	    return frsp;
	}
	return null;
    }

    public Friendship adminDestroyFriendship(User currentUser, User friend1, User friend2) {
	if (ValidationUtil.validateAdmin(currentUser)) {
	    frDao.deleteByIds(friend1.getId(), friend2.getId());
	    frDao.deleteByIds(friend2.getId(), friend1.getId());
	    return frsp;
	} else {
	    return null;
	}
    }

    public List<User> getAllFriendsById(User currentUser, User friend) {
	if (ValidationUtil.validateAdmin(currentUser) || currentUser.getId() == friend.getId()) {
	    List<Friendship> allFriendships = frDao.getAllFriendshipsById(friend.getId());

	    List<User> allFriends = new ArrayList<User>();
	    for (int i = 0; i < allFriendships.size(); i++) {
		if (allFriendships.get(i).getPending() == 0) {
		    if (allFriendships.get(i).getUser1Id() != friend.getId()) {
			allFriends.add(usrDao.getUserById(allFriendships.get(i).getUser1Id()));
		    } else {
			allFriends.add(usrDao.getUserById(allFriendships.get(i).getUser2Id()));
		    }
		}
	    }
	    return allFriends;
	} else {
	    return null;
	}
    }

    public Friendship getFriendshipByIds(int user1Id, int user2Id) {
	return frDao.getFriendshipByIds(user1Id, user2Id);
    }
}
