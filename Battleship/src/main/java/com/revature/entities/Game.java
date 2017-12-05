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
@Table(name = "Games")
public class Game {
	@Id
	@Column(name = "GM_ID")
	@SequenceGenerator(name = "GM_ID_seq", sequenceName = "GM_ID_seq")
	@GeneratedValue(generator = "GM_ID_seq", strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Status")
	private int status;

	@Column(name = "P1_USR_ID")
	private int player1Id;

	@Column(name = "P2_USR_ID")
	private int player2Id;

	// Player one or player two's turn?
	@Column(name = "Turn")
	private int turn;

	// JSON of 2d array representing tile info
	@Column(name = "Board_State")
	private String boardState;

	// JSON of array representing ship tile info
	@Column(name = "Ship_State")
	private String shipState;

	// When game was created
	@Column(name = "Post_Date")
	private Date postDate;

	// turn must be completed by this time
	@Column(name = "Turn_Deadline")
	private Date turnDeadline;
}
