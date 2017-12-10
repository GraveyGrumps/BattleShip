package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.daos.FriendshipDao;
import com.revature.entities.Friendship;
import com.revature.entities.User;
import com.revature.util.ValidationUtil;

public class FriendshipService {
    @Autowired
    private FriendshipDao fsDao;
    @Autowired
    private Friendship fs;

    public Friendship sendFriendRequest(User currentUser, User otherUser) {
	int friend1Id = currentUser.getId();
	int friend2Id = otherUser.getId();
	if (ValidationUtil.validateAccess(currentUser, fs) && fsDao.getFriendshipByIds(friend1Id, friend2Id) == null
		&& friend1Id != friend2Id) {
	    fs.setUser1Id(friend1Id);
	    fs.setUser2Id(friend2Id);
	    fs.setPending(1);
	    fsDao.addFriendShip(fs);
	    return fs;
	}
	return null;
    }

    public Friendship acceptFriendRequest(User currentUser, User otherUser) {
	int friend1Id = currentUser.getId();
	int friend2Id = otherUser.getId();
	fs = fsDao.getFriendshipByIds(friend1Id, friend2Id);
	if (ValidationUtil.validateAccess(currentUser, fs) && fs != null) {
	    fs.setPending(0);
	    return fs;
	}
	return null;
    }

    public void declineFriendRequest(User currentUser, User otherUser) {
	int friend1Id = currentUser.getId();
	int friend2Id = otherUser.getId();
	fs = fsDao.getFriendshipByIds(friend1Id, friend2Id);
	if (ValidationUtil.validateAccess(currentUser, fs) && fs != null) {
	    fsDao.deleteByIds(friend1Id, friend2Id);
	}
    }

    public void removeFriend(User currentUser, User otherUser) {
	int friend1Id = currentUser.getId();
	int friend2Id = otherUser.getId();
	fs = fsDao.getFriendshipByIds(friend1Id, friend2Id);
	if (ValidationUtil.validateAccess(currentUser, fs) && fsDao.getFriendshipByIds(friend1Id, friend2Id) != null) {
	    fsDao.deleteByIds(friend1Id, friend2Id);
	}
    }

    public Friendship adminCreateFriendship(User currentUser, User friend1, User friend2) {
	int friend1Id = friend1.getId();
	int friend2Id = friend2.getId();
	if (ValidationUtil.validateAdmin(currentUser) && friend1Id != friend2Id
		&& fsDao.getFriendshipByIds(friend1Id, friend2Id) == null) {
	    fs.setUser1Id(friend1Id);
	    fs.setUser2Id(friend2Id);
	    fs.setPending(0);
	    fs.setChatLog("");
	    fsDao.addFriendShip(fs);
	    return fs;
	}
	return null;
    }

    public Friendship adminDestroyFriendship(User currentUser, User friend1, User friend2) {
	if (ValidationUtil.validateAdmin(currentUser)) {
	    fsDao.deleteByIds(friend1.getId(), friend2.getId());
	    return fs;
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
