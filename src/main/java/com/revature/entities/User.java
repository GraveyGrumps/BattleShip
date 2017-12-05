package com.revature.entities;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@Column(name = "USR_ID")
	@SequenceGenerator(name = "USR_ID_seq", sequenceName = "USR_ID_seq")
	@GeneratedValue(generator = "USR_ID_seq", strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Username")
	private String username;

	@Column(name = "Password")
	private String password;

	@Column(name = "Email")
	private String email;

	// Is user an admin?
	@Column(name = "Admin")
	private int admin;

	@Column(name = "S_ID")
	private int settingsId;

	@Column(name = "WL_ID")
	private int winLossId;

	@Column(name = "Profile_Pic")
	private File profilePic;

	@Column(name = "CL_ID")
	private int clanId;

	// Is this user a clan officer?
	@Column(name = "Officer")
	private int isOfficer;

	// Notes that the admin writes about this user
	@Column(name = "Admin_Notes")
	private String adminNotes;

	// for email verification
	@Column(name = "Hash")
	private String hash;

	// Is email verified?
	@Column(name = "Verified")
	private int verified;
}
