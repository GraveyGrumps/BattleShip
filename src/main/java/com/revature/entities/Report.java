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
}
