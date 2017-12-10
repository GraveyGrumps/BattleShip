package com.revature.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.entities.Friendship;

@Repository
public class FriendshipDaoHibernate implements FriendshipDao {
    private Logger log = Logger.getRootLogger();
    @Autowired
    private SessionFactory sf;

    @Override
    @Transactional
    public boolean addFriendShip(Friendship friendship) throws ConstraintViolationException {
	log.trace("Adding a new Friendship to the Database");
	sf.getCurrentSession().save(friendship);
	return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Friendship> getAllFriendshipsById(int id) {
	log.trace("Getting all friendships with id: " + id);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Friendship.class);
	criteria.add(
		Restrictions.disjunction().add(Restrictions.eq("user1Id", id)).add(Restrictions.eq("user2Id", id)));
	return (List<Friendship>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Friendship> getAllFriendshipsByPending(int pending) {
	log.trace("Getting all friendships with pending: " + pending);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Friendship.class);
	criteria.add(Restrictions.eq("pending", pending));
	return (List<Friendship>) criteria.list();
    }

    @Override
    @Transactional
    public boolean modifyFriendshipByUser1IdandPending(int id, int pending) {
	log.trace("Changing pending to user2 where id: " + id + " and pending: " + pending);
	Session session = sf.getCurrentSession();
	Friendship friendship = getFrienshipByIdAndPending(id, pending);
	if (friendship == null)
	    return false;
	friendship.setUser2Id(pending);
	friendship.setPending(0);
	session.merge(friendship);
	session.close();
	return true;
    }

    @Override
    @Transactional
    public String modifyChatLog(int p1Id, int p2Id, String chat) {
	log.trace("Adding to chat log: " + chat + " between p1: " + p1Id + " and p2: " + p2Id);
	Session session = sf.getCurrentSession();
	Friendship friendship = getFriendshipByIds(p1Id, p2Id);
	if (friendship == null)
	    return "";
	friendship.addToChatLog(chat);
	session.merge(friendship);
	return friendship.getChatLog();
    }

    @Override
    @Transactional
    public boolean deleteByIdAndPending(int id, int pending) {
	log.trace("Deleting Friendship by id: " + id + " and pending: " + pending);
	Session session = sf.getCurrentSession();
	Friendship friendship = getFrienshipByIdAndPending(id, pending);
	if (friendship == null)
	    return false;
	session.delete(friendship);
	session.close();
	return true;
    }

    @Override
    @Transactional
    public boolean deleteByIds(int p1Id, int p2Id) {
	log.trace("Deteling Friendship by id1: " + p1Id + " and id2: " + p2Id);
	Session session = sf.getCurrentSession();
	Friendship friendship = getFriendshipByIds(p1Id, p2Id);
	if (friendship == null)
	    return false;
	session.delete(friendship);
	session.close();
	return true;
    }

    @Transactional
    private Friendship getFrienshipByIdAndPending(int id, int pending) {
	log.trace("Finding friendship with id: " + id + " and pending: " + pending);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Friendship.class);
	criteria.add(Restrictions.and(Restrictions.eq("pending", pending), Restrictions.eq("user1Id", id)));
	return (Friendship) criteria.uniqueResult();
    }

    @Transactional
    public Friendship getFriendshipByIds(int p1Id, int p2Id) {
	log.trace("Finding frienship with p1Id: " + p1Id + " and p2Id: " + p2Id);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Friendship.class);
	criteria.add(Restrictions.and(Restrictions.eq("user1Id", p1Id), Restrictions.eq("user2Id", p2Id)));
	return (Friendship) criteria.uniqueResult();
    }
}
