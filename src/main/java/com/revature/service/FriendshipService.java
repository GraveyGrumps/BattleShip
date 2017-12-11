package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.FriendshipDao;
import com.revature.entities.Friendship;
import com.revature.entities.User;
import com.revature.util.ValidationUtil;

@Service
public class FriendshipService {
    @Autowired
    private FriendshipDao frDao;
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
	    frDao.addFriendShip(frsp);
	    return frsp;
	}
	return null;
    }

    public Friendship acceptFriendRequest(User currentUser, User otherUser) {
	int friend1Id = currentUser.getId();
	int friend2Id = otherUser.getId();
	frsp = frDao.getFriendshipByIds(friend1Id, friend2Id);
	if (ValidationUtil.validateAccess(currentUser, frsp) && frsp != null) {
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
		&& frDao.getFriendshipByIds(friend1Id, friend2Id) == null) {
	    frsp.setUser1Id(friend1Id);
	    frsp.setUser2Id(friend2Id);
	    frsp.setPending(0);
	    frsp.setChatLog("");
	    frDao.addFriendShip(frsp);
	    return frsp;
	}
	return null;
    }

    public Friendship adminDestroyFriendship(User currentUser, User friend1, User friend2) {
	if (ValidationUtil.validateAdmin(currentUser)) {
	    frDao.deleteByIds(friend1.getId(), friend2.getId());
	    return frsp;
	} else {
	    return null;
	}

    }

    // public List<Friendship> getAllFriendships(User currentUser) {
    // if (ValidationUtil.validateAdmin(currentUser)) {
    // return fspD.getAllFriendships();
    // } else {
    // return null;
    // }
    // }
}
