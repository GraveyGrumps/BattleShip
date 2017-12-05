package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
// @Table(name = "Settings")
public class Settings {

	@Id
	@Column(name = "S_ID")
	@SequenceGenerator(name = "S_ID_seq", sequenceName = "S_ID_seq")
	@GeneratedValue(generator = "S_ID_seq", strategy = GenerationType.AUTO)
	private int id;

	// Allow global chat?
	@Column(name = "Global_Chat")
	private int globalChat;

	// Allow in-game chat?
	@Column(name = "In_Game_Chat")
	private int inGameChat;

	// TODO what does this field signify?
	@Column(name = "Accept_Friendship")
	private int acceptFriendship;

	// Allow global chat?
	@Column(name = "Allow_Challenges")
	private int allowChallenges;

	// Is profile visible to others?
	@Column(name = "Viewable")
	private int viewable;
}
