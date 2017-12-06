package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Clans")
public class Clan {

	@Id
	@Column(name = "CL_ID")
	@SequenceGenerator(name = "CL_ID_seq", sequenceName = "CL_ID_seq")
	@GeneratedValue(generator = "CL_ID_seq", strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Chat_Log")
	private String chatLog;

	@Column(name = "Logo")
	private String logo;
}
