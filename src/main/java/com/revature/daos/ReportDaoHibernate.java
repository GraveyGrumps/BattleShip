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

import com.revature.entities.Report;
import com.revature.entities.WinLoss;

@Repository
public class ReportDaoHibernate implements ReportDao {
    private Logger log = Logger.getRootLogger();

    @Autowired
    SessionFactory sf;

    @Override
    @Transactional
    public Report addReport(Report report) {
	log.trace("Adding a new Report to the Database");
	sf.getCurrentSession().save(report);
	return report;
    }

    @Override
    @Transactional
    public Report getReportById(int id) {
	log.trace("Getting Report by Id: " + id);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Report.class);
	criteria.add(Restrictions.eq("id", id));
	return (Report) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public Report getReportByGameId(int gameId) {
	log.trace("Getting Report by Game Id: " + gameId);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Report.class);
	criteria.add(Restrictions.eq("gameId", gameId));
	return (Report) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Report> getFlaggedReports() {
	log.trace("Getting all flagged reports");
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Report.class);
	criteria.add(Restrictions.eq("flag", 1));
	return (List<Report>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Report> getAllReports() {
	log.trace("Getting all reports");
	Session session = sf.getCurrentSession();
	return (List<Report>) session.createCriteria(Report.class).list();
    }

    @Override
    @Transactional
    public boolean changeReportWinnerById(int id, int winner) {
	log.trace("Changing winner: " + winner + " where id: " + id);
	Session session = sf.getCurrentSession();
	Report report = getReportByGameId(id);
	if (report == null)
	    return false;
	report.setWinner(winner);
	session.merge(report);
	return true;
    }

    @Override
    @Transactional
    public String changeChatLogById(int id, String chatLog) {
	log.trace("Adding to ChatLog: " + chatLog + " where id: " + id);
	Session session = sf.getCurrentSession();
	Report report = getReportByGameId(id);
	if (report == null)
	    return "";
	report.appendToChatLog(chatLog);
	session.merge(report);
	return report.getChatLog();
    }

    @Override
    @Transactional
    public boolean changeReportDateById(int id, Timestamp time) {
	log.trace("Changing Report Date: " + time + " where id: " + id);
	Session session = sf.getCurrentSession();
	Report report = getReportByGameId(id);
	if (report == null)
	    return false;
	report.setReportDate(time);
	session.merge(report);
	return true;
    }

    @Override
    @Transactional
    public boolean changeClaimantById(int id, int claimant) {
	log.trace("Changing claimant: " + claimant + " where id: " + id);
	Session session = sf.getCurrentSession();
	Report report = getReportByGameId(id);
	if (report == null)
	    return false;
	report.setClaimant(claimant);
	session.merge(report);
	return true;
    }

    @Override
    @Transactional
    public boolean changeDefendantById(int id, int defendant) {
	log.trace("Changing defendant: " + defendant + " where id: " + id);
	Session session = sf.getCurrentSession();
	Report report = getReportByGameId(id);
	if (report == null)
	    return false;
	report.setDefendant(defendant);
	session.merge(report);
	return true;
    }

    @Override
    @Transactional
    public boolean changeReportNotesById(int id, String reportNotes) {
	log.trace("Changing reportNotes: " + reportNotes + " where id: " + id);
	Session session = sf.getCurrentSession();
	Report report = getReportByGameId(id);
	if (report == null)
	    return false;
	report.setReportNotes(reportNotes);
	session.merge(report);
	return true;
    }

    @Override
    @Transactional
    public boolean changeReportFlagById(int id, int flag) {
	log.trace("Changing Report Flag: " + flag + " where id: " + id);
	Session session = sf.getCurrentSession();
	Report report = getReportByGameId(id);
	if (report == null)
	    return false;
	report.setFlag(flag);
	session.merge(report);
	return true;
    }

    @Override
    @Transactional
    public boolean deleteReportById(int id) {
	log.trace("Deleting Report where id: " + id);
	Session session = sf.getCurrentSession();
	Report report = getReportByGameId(id);
	if (report == null)
	    return false;
	session.delete(report);
	return true;
    }

	@Override
	@Transactional
	public Report modify(Report rep2) {
		log.trace("merging report via report: " + rep2);
		Session session = sf.getCurrentSession();
		session.merge(rep2);
		return rep2;
	}

}
