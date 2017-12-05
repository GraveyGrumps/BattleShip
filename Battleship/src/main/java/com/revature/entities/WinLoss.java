package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Win_Loss")
public class WinLoss {

	@Id
	@Column(name = "WL_ID")
	@SequenceGenerator(name = "WL_ID_seq", sequenceName = "WL_ID_seq")
	@GeneratedValue(generator = "WL_ID_seq", strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Season_Wins")
	private int seasonWins;

	@Column(name = "Season_Losses")
	private int seasonLosses;

	@Column(name = "Wins")
	private int wins;

	@Column(name = "Losses")
	private int losses;
}
