package com.revature.daos;

import java.util.List;

import com.revature.entities.Friendship;

public interface FriendshipDao {
	//POST
	boolean addFriendShip(Friendship friendship);
	//GET
	List<Friendship> getAllFriendshipsById(int id);
	List<Friendship> getAllFriendshipsByPending(int pending);
	//PUT
	boolean modifyFriendshipByUser1IdandPending(int id, int pending);
	String modifyChatLog(int p1Id, int p2Id, String chat);	
	//DELETE
	boolean deleteByIdAndPending(int id, int pending);
	boolean deleteByIds(int p1Id, int P2Id);
}
