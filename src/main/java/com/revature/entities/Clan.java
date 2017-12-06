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

	public Clan(int id, String name, String chatLog, String logo) {
		super();
		this.id = id;
		this.name = name;
		this.chatLog = chatLog;
		this.logo = logo;
	}

	public Clan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChatLog() {
		return chatLog;
	}

	public void setChatLog(String chatLog) {
		this.chatLog = chatLog;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chatLog == null) ? 0 : chatLog.hashCode());
		result = prime * result + id;
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Clan other = (Clan) obj;
		if (chatLog == null) {
			if (other.chatLog != null)
				return false;
		} else if (!chatLog.equals(other.chatLog))
			return false;
		if (id != other.id)
			return false;
		if (logo == null) {
			if (other.logo != null)
				return false;
		} else if (!logo.equals(other.logo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Clan [id=" + id + ", name=" + name + ", chatLog=" + chatLog + ", logo=" + logo + "]";
	}
	
	
}
