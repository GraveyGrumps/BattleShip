package com.revature.daos;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.entities.Settings;

@Repository
public class SettingsDaoHibernate implements SettingsDao {
    private Logger log = Logger.getRootLogger();

    @Autowired
    SessionFactory sf;

    @Override
    @Transactional
    public Settings addSettings(Settings setting) {
	log.trace("Adding setting to DataBase");
	sf.getCurrentSession().save(setting);
	return setting;
    }

    @Override
    @Transactional
    public Settings getSettingsById(int id) {
	log.trace("getting setting by Id: " + id);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Settings.class);
	criteria.add(Restrictions.eq("id", id));
	return (Settings) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public boolean modifyAllowGlobalChatById(int id, int allow) {
	log.trace("Setting global chat to: " + allow + " where id: " + id);
	Session session = sf.getCurrentSession();
	Settings setting = getSettingsById(id);
	if (setting == null)
	    return false;
	setting.setGlobalChat(allow);
	session.merge(setting);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyAllowInGameChatById(int id, int allow) {
	log.trace("Setting in game chat to: " + allow + " where id: " + id);
	Session session = sf.getCurrentSession();
	Settings setting = getSettingsById(id);
	if (setting == null)
	    return false;
	setting.setInGameChat(allow);
	session.merge(setting);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyAllowFriendRequestsById(int id, int allow) {
	log.trace("Setting friend request to: " + allow + " where id: " + id);
	Session session = sf.getCurrentSession();
	Settings setting = getSettingsById(id);
	if (setting == null)
	    return false;
	setting.setAcceptFriendship(allow);
	session.merge(setting);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyAllowChallengesById(int id, int allow) {
	log.trace("Setting challenges to: " + allow + " where id: " + id);
	Session session = sf.getCurrentSession();
	Settings setting = getSettingsById(id);
	if (setting == null)
	    return false;
	setting.setAllowChallenges(allow);
	session.merge(setting);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyViewableProfileById(int id, int allow) {
	log.trace("Setting viewable profile to: " + allow + " where id: " + id);
	Session session = sf.getCurrentSession();
	Settings setting = getSettingsById(id);
	if (setting == null)
	    return false;
	setting.setViewable(allow);
	session.merge(setting);
	return true;
    }

    @Override
    @Transactional
    public boolean deleteSettingsById(int id) {
	log.trace("Deleting Setting with id: " + id);
	Session session = sf.getCurrentSession();
	Settings setting = getSettingsById(id);
	if (setting == null)
	    return false;
	session.delete(setting);
	return true;
    }

}
