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

	public Ban(int userId, String banStatus, Timestamp banLiftDate, String record) {
		super();
		this.userId = userId;
		this.banStatus = banStatus;
		this.banLiftDate = banLiftDate;
		this.record = record;
	}

	public Ban() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBanStatus() {
		return banStatus;
	}

	public void setBanStatus(String banStatus) {
		this.banStatus = banStatus;
	}

	public Timestamp getBanLiftDate() {
		return banLiftDate;
	}

	public void setBanLiftDate(Timestamp banLiftDate) {
		this.banLiftDate = banLiftDate;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((banLiftDate == null) ? 0 : banLiftDate.hashCode());
		result = prime * result + ((banStatus == null) ? 0 : banStatus.hashCode());
		result = prime * result + ((record == null) ? 0 : record.hashCode());
		result = prime * result + userId;
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
		Ban other = (Ban) obj;
		if (banLiftDate == null) {
			if (other.banLiftDate != null)
				return false;
		} else if (!banLiftDate.equals(other.banLiftDate))
			return false;
		if (banStatus == null) {
			if (other.banStatus != null)
				return false;
		} else if (!banStatus.equals(other.banStatus))
			return false;
		if (record == null) {
			if (other.record != null)
				return false;
		} else if (!record.equals(other.record))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ban [userId=" + userId + ", banStatus=" + banStatus + ", banLiftDate=" + banLiftDate + ", record="
				+ record + "]";
	}
	
}