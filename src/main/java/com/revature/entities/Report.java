package com.revature.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Reports")

public class Report {
	@Id
	@Column(name = "RP_ID")
	@SequenceGenerator(name = "RP_ID_seq", sequenceName = "RP_ID_seq")
	@GeneratedValue(generator = "RP_ID_seq", strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "GM_ID")
	private int gameId;

	// TODO is this the user id or the player #?
	@Column(name = "Winner")
	private int winner;

	@Column(name = "Chat_Log")
	private String chatLog;

	@Column(name = "Report_Date")
	private Timestamp reportDate;

	// user id
	@Column(name = "Claimant")
	private int claimant;

	// user id
	@Column(name = "Defendant")
	private int defendant;

	@Column(name = "Report_Notes")
	private String reportNotes;

	// 1 requires admin's attention
	@Column(name = "Flag")
	private int flag;

	public Report(int id, int gameId, int winner, String chatLog, Timestamp reportDate, int claimant, int defendant,
			String reportNotes, int flag) {
		super();
		this.id = id;
		this.gameId = gameId;
		this.winner = winner;
		this.chatLog = chatLog;
		this.reportDate = reportDate;
		this.claimant = claimant;
		this.defendant = defendant;
		this.reportNotes = reportNotes;
		this.flag = flag;
	}

	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public String getChatLog() {
		return chatLog;
	}

	public void setChatLog(String chatLog) {
		this.chatLog = chatLog;
	}

	public Timestamp getReportDate() {
		return reportDate;
	}

	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}

	public int getClaimant() {
		return claimant;
	}

	public void setClaimant(int claimant) {
		this.claimant = claimant;
	}

	public int getDefendant() {
		return defendant;
	}

	public void setDefendant(int defendant) {
		this.defendant = defendant;
	}

	public String getReportNotes() {
		return reportNotes;
	}

	public void setReportNotes(String reportNotes) {
		this.reportNotes = reportNotes;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chatLog == null) ? 0 : chatLog.hashCode());
		result = prime * result + claimant;
		result = prime * result + defendant;
		result = prime * result + flag;
		result = prime * result + gameId;
		result = prime * result + id;
		result = prime * result + ((reportDate == null) ? 0 : reportDate.hashCode());
		result = prime * result + ((reportNotes == null) ? 0 : reportNotes.hashCode());
		result = prime * result + winner;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (chatLog == null) {
			if (other.chatLog != null)
				return false;
		} else if (!chatLog.equals(other.chatLog))
			return false;
		if (claimant != other.claimant)
			return false;
		if (defendant != other.defendant)
			return false;
		if (flag != other.flag)
			return false;
		if (gameId != other.gameId)
			return false;
		if (id != other.id)
			return false;
		if (reportDate == null) {
			if (other.reportDate != null)
				return false;
		} else if (!reportDate.equals(other.reportDate))
			return false;
		if (reportNotes == null) {
			if (other.reportNotes != null)
				return false;
		} else if (!reportNotes.equals(other.reportNotes))
			return false;
		if (winner != other.winner)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", gameId=" + gameId + ", winner=" + winner + ", chatLog=" + chatLog
				+ ", reportDate=" + reportDate + ", claimant=" + claimant + ", defendant=" + defendant
				+ ", reportNotes=" + reportNotes + ", flag=" + flag + "]";
	}
	
	
}
