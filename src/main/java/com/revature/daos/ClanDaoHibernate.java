package com.revature.daos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Clan;

@Repository
public class ClanDaoHibernate implements ClanDao {
	private Logger log = Logger.getRootLogger();
	@Autowired
	SessionFactory sf;
	
	@Override
	@Transactional
	public Clan addClan(Clan clan) {
		log.trace("Adding a clan to the database");
		sf.getCurrentSession().save(clan);
		return clan;
	}

	@Override
	@Transactional
	public Clan getClanById(int id) {
		log.trace("Getting Clan by Id: " + id);
		Session session = sf.getCurrentSession();
		Criteria criteria = session.createCriteria(Clan.class);
		criteria.add(Restrictions.eq("id", id));
		return (Clan) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Clan> getAllClans() {
		log.trace("Getting all Clans");
		Session session = sf.getCurrentSession();
		return (List<Clan>) session.createCriteria(Clan.class).list();
	}

	@Override
	@Transactional
	public boolean modfiyClanNameById(int id, String clanName) {
		log.trace("Modifying Clanname: " + clanName + " where id: " + id);
		Session session = sf.getCurrentSession();
		Clan clan = getClanById(id);
		clan.setName(clanName);
		session.merge(clan);
		return true;
	}

	@Override
	@Transactional
	public String modifyClanChatLogById(int id, String chatLog) {
		log.trace("Adding to clan ChatLog: " + chatLog + " where id: " + id);
		Session session = sf.getCurrentSession();
		Clan clan = getClanById(id);
		clan.addToChatLog(chatLog);
		session.merge(clan);
		return clan.getChatLog();
	}

	@Override
	@Transactional
	public boolean modifyClanLogoById(int id, String logoPath) {
		log.trace("Modifying Clan Logo: " + logoPath + " where id: " + id);
		Session session = sf.getCurrentSession();
		Clan clan = getClanById(id);
		clan.setLogo(logoPath);
		session.merge(clan);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteClanById(int id) {
		log.trace("Deleting Clan with id: " + id);
		Session session = sf.getCurrentSession();
		Clan clan = getClanById(id);
		session.delete(clan);
		return true;
	}

	@Override
	public boolean clearClanChatById(int id) {
		log.trace("Clearing chat log of clan with id: " + id);
		Session session = sf.getCurrentSession();
		Clan clan = getClanById(id);
		clan.setChatLog("");
		session.merge(clan);
		return true;
	}

}
