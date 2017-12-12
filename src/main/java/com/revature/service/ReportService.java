package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.ReportDao;
import com.revature.entities.Report;
import com.revature.entities.User;

@Service
public class ReportService {
    @Autowired
    private ReportDao rpDao;
    @Autowired
    private Report rep;

    public Report sendReport() {
	return rep;
    }

    public Report changeWinner(User currentUser, Report report, User winner) {
	if (/* ValidationUtil.validateAdmin(currentUser) && */ rpDao.getReportById(report.getId()) != null) {
	    rpDao.changeReportWinnerById(report.getId(), winner.getId());
	    return report;
	} else {
	    return null;
	}
    }

    public List<Report> getAllReports(User currentUser) {
	// if (ValidationUtil.validateAdmin(currentUser)) {
	return rpDao.getAllReports();
	// } else {
	// return null;
	// }
    }

    public List<Report> getFlaggedReports(User currentUser) {
	// if (ValidationUtil.validateAdmin(currentUser)) {
	return rpDao.getFlaggedReports();
	// } else {
	// return null;
	// }
    }

    public Report changeNotes(User currentUser, Report report, String newNotes) {
	if (/* ValidationUtil.validateAdmin(currentUser) && */rpDao.getReportById(report.getId()) != null) {
	    rpDao.changeReportNotesById(report.getId(), newNotes);
	    return report;
	} else {
	    return null;
	}
    }

    public Report changeFlag(User currentUser, Report report, int newFlag) {
	if (/* ValidationUtil.validateAdmin(currentUser) && */ rpDao.getReportById(report.getId()) != null
		&& (newFlag == 0 || newFlag == 1)) {
	    rpDao.changeReportFlagById(report.getId(), newFlag);
	    return report;
	} else {
	    return null;
	}
    }

    public void deleteReport(User currentUser, Report report) {
	if (/* ValidationUtil.validateAdmin(currentUser) && */ rpDao.getReportById(report.getId()) != null) {
	    rpDao.deleteReportById(report.getId());
	}
    }

    public Report loadgameReport(int id) {
	return rpDao.getReportByGameId(id);
    }

    public Report modify(Report rep2) {
	return rpDao.modifyReportViaReport(rep2);
    }
}
