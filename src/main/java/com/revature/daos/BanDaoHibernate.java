package com.revature.daos;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.entities.Ban;

@Repository
public class BanDaoHibernate implements BanDao {
    private Logger log = Logger.getRootLogger();
    @Autowired
    SessionFactory sf;

    @Override
    @Transactional
    public Ban addBan(Ban ban) {
	log.trace("Adding a ban to the database");
	sf.getCurrentSession().save(ban);
	return ban;
    }

    @Override
    @Transactional
    public Ban getBanById(int id) {
	log.trace("Getting a ban by id: " + id);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Ban.class);
	criteria.add(Restrictions.eq("userId", id));
	return (Ban) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Ban> getAllBans() {
	log.trace("Getting all Bans");
	Session session = sf.getCurrentSession();
	return (List<Ban>) session.createCriteria(Ban.class).list();
    }

    @Override
    @Transactional
    public boolean modifyBanStatusById(int id, String status) {
	log.trace("Modifying ban status: " + status + " where ban id: " + id);
	Session session = sf.getCurrentSession();
	Ban ban = getBanById(id);
	if (ban == null)
	    return false;
	ban.setBanStatus(status);
	session.merge(ban);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyBannedUntilById(int id, Timestamp time) {
	log.trace("Modifying time banned: " + time + " where ban id: " + id);
	Session session = sf.getCurrentSession();
	Ban ban = getBanById(id);
	if (ban == null)
	    return false;
	ban.setBanLiftDate(time);
	session.merge(ban);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyBanRecordById(int id, String record) {
	log.trace("Modifying ban record: " + record + " where ban id: " + id);
	Session session = sf.getCurrentSession();
	Ban ban = getBanById(id);
	if (ban == null)
	    return false;
	ban.setRecord(record);
	session.merge(ban);
	return true;
    }

    @Override
    @Transactional
    public boolean deleteBanById(int id) {
	log.trace("Delete ban with id: " + id);
	Session session = sf.getCurrentSession();
	Ban ban = getBanById(id);
	if (ban == null)
	    return false;
	session.delete(ban);
	return true;
    }

}
