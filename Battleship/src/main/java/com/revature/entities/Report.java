package com.revature.entities;

import java.util.Date;

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

	@Column(name = "Game_ID")
	private int gameId;

	// TODO is this the user id or the player # (1/2)?
	@Column(name = "Game_ID")
	private int winner;

	// TODO if you have the game id, you can get its chat log, so is this necessary?
	@Column(name = "Game_ID")
	private String chatLog;

	@Column(name = "Report_Date")
	private Date reportDate;

	// User ID
	@Column(name = "Claimant")
	private int claimant;

	// user id
	@Column(name = "Defendant")
	private int defendant;

	@Column(name = "Report_Notes")
	private String reportNotes;

	// TODO flag
	// @Column(name = "Flag")
}
