package com.revature.daos;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.revature.entities.Friendship;

public interface FriendshipDao {
    // POST
    boolean addFriendship(Friendship friendship) throws ConstraintViolationException;

    // GET
    List<Friendship> getAllFriendshipsById(int id);

    List<Friendship> getAllFriendshipsByPending(int pending);

    // PUT
    Friendship modifyFriendshipViaFriendship(Friendship friendship);
    boolean modifyFriendshipByUser1IdandPending(int id, int pending);

    String modifyChatLog(int p1Id, int p2Id, String chat);

    // DELETE
    boolean deleteByIdAndPending(int id, int pending);

    boolean deleteByIds(int p1Id, int P2Id);

    Friendship getFriendshipByIds(int p1Id, int p2Id);
}
