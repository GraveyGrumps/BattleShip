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
@Table(name = "Bans")
public class Ban {

	@Id
	@Column(name = "USR_id")
	@SequenceGenerator(name = "USR_id_seq", sequenceName = "USR_id_seq")
	@GeneratedValue(generator = "USR_id_seq", strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "Ban_Status")
	private String banStatus;

	@Column(name = "Banished_Until")
	private Timestamp banLiftDate;

	@Column(name = "Record")
	private String record;
}