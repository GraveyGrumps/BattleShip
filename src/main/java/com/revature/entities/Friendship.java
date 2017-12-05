package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Friendships")
public class Friendship {

	@Id
	@Column(name = "Usr_1")
	private int user1Id;

	@Id
	@Column(name = "Usr_2")
	private int user2Id;

	@Column(name = "Pending")
	private int pending;

	@Column(name = "Pm_Log")
	private String chatLog;
}
