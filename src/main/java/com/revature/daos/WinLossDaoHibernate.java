package com.revature.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.entities.WinLoss;

@Repository
public class WinLossDaoHibernate implements WinLossDao {

    private Logger log = Logger.getRootLogger();

    @Autowired
    SessionFactory sf;

    @Override
    @Transactional
    public WinLoss addWinLoss(WinLoss winloss) {
	log.trace("Adding a new WinLoss to the Database");
	sf.getCurrentSession().save(winloss);
	return winloss;
    }

    @Override
    @Transactional
    public WinLoss getWinLossById(int id) {
	log.trace("Getting WinLoss by id: " + id);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(WinLoss.class);
	criteria.add(Restrictions.eq("id", id));
	return (WinLoss) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<WinLoss> getAllWinLoss() {
	log.trace("Getting all WinLosses");
	Session session = sf.getCurrentSession();
	return (List<WinLoss>) session.createCriteria(WinLoss.class).list();
    }

    @Override
    @Transactional
    public boolean modifyWinLossWinsById(int id, int win) {
	log.trace("Incrementing win by: " + win + " where id: " + id);
	Session session = sf.getCurrentSession();
	WinLoss winloss = getWinLossById(id);
	if (winloss == null)
	    return false;
	winloss.incrementWins(win);
	session.merge(winloss);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyWinLossLossesById(int id, int loss) {
	log.trace("Incrementing losses by: " + loss + " where id: " + id);
	Session session = sf.getCurrentSession();
	WinLoss winloss = getWinLossById(id);
	if (winloss == null)
	    return false;
	winloss.incrementLosses(loss);
	session.merge(winloss);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyWinLossSeasonWinsById(int id, int win) {
	log.trace("Incrementing Seasonal Wins by: " + win + " where id: " + id);
	Session session = sf.getCurrentSession();
	WinLoss winloss = getWinLossById(id);
	if (winloss == null)
	    return false;
	winloss.incrementSeasonalWins(win);
	session.merge(winloss);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyWinLossSeasonLossesById(int id, int loss) {
	log.trace("Incrementing Seasonal losses by: " + loss + " where id: " + id);
	Session session = sf.getCurrentSession();
	WinLoss winloss = getWinLossById(id);
	if (winloss == null)
	    return false;
	winloss.incrementSeasonalLosses(loss);
	session.merge(winloss);
	return true;
    }

    @Override
    @Transactional
    public boolean clearWinLossSeasonById(int id) {
	log.trace("Clearing seasonal wins and losses where id: " + id);
	Session session = sf.getCurrentSession();
	WinLoss winloss = getWinLossById(id);
	if (winloss == null)
	    return false;
	winloss.setSeasonLosses(0);
	winloss.setSeasonWins(0);
	session.merge(winloss);
	return true;
    }

    @Override
    @Transactional
    public boolean deleteWinLossById(int id) {
	log.trace("Deleting WinLoss by Id: " + id);
	Session session = sf.getCurrentSession();
	WinLoss winloss = getWinLossById(id);
	if (winloss == null)
	    return false;
	session.delete(winloss);
	return true;
    }

	@Override
	@Transactional
	public WinLoss modifyWinlossViaWinloss(WinLoss wL) {
		log.trace("merging game via W/L: " + wL);
		Session session = sf.getCurrentSession();
		session.merge(wL);
		return wL;
	}


}
