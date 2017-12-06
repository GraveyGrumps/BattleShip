package com.revature.daos;

import java.sql.Timestamp;
import java.util.List;

import com.revature.entities.Report;

public interface ReportDao {
	//POST
	Report addReport(Report report);
	//GET
	Report getReportById(int id);
	Report getReportByGameId(int gameId);
	List<Report> getFlaggedReports();
	List<Report> getAllReports();
	//UPDATE
	boolean changeReportViaReportById(int id, Report report);
	boolean changeReportWinnerById(int id, int winner);
	boolean changeChatLogById(int id, String chatLog);
	boolean changeReportDateById(int id, Timestamp time);
	boolean changeClaimantById(int id, int claimant);
	boolean changeDefendantById(int id, int defendant);
	boolean changeReportNotesById(int id, String reportNotes);
	boolean changeReportFlagById(int id, int flag);
	//DELETE
	boolean deleteReportById(int id);
	boolean deleteReportByReport(Report report);
}
