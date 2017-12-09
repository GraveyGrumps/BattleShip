package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@Column(name = "USR_ID")
	@SequenceGenerator(name = "USR_seq", sequenceName = "USR_seq")
	@GeneratedValue(generator = "USR_seq", strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Username")
	private String username;

	@Column(name = "Password")
	@JsonProperty(access = Access.READ_ONLY)
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
	private String profilePic;

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

	public User(int id, String username, String password, String email, int admin, int settingsId, int winLossId,
			String profilePic, int clanId, int isOfficer, String adminNotes, String hash, int verified) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.admin = admin;
		this.settingsId = settingsId;
		this.winLossId = winLossId;
		this.profilePic = profilePic;
		this.clanId = clanId;
		this.isOfficer = isOfficer;
		this.adminNotes = adminNotes;
		this.hash = hash;
		this.verified = verified;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getSettingsId() {
		return settingsId;
	}

	public void setSettingsId(int settingsId) {
		this.settingsId = settingsId;
	}

	public int getWinLossId() {
		return winLossId;
	}

	public void setWinLossId(int winLossId) {
		this.winLossId = winLossId;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public int getClanId() {
		return clanId;
	}

	public void setClanId(int clanId) {
		this.clanId = clanId;
	}

	public int getIsOfficer() {
		return isOfficer;
	}

	public void setIsOfficer(int isOfficer) {
		this.isOfficer = isOfficer;
	}

	public String getAdminNotes() {
		return adminNotes;
	}

	public void appendAdminNotes(String notes) {
		this.adminNotes += notes;
	}

	public void setAdminNotes(String adminNotes) {
		this.adminNotes = adminNotes;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getVerified() {
		return verified;
	}

	public void setVerified(int verified) {
		this.verified = verified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + admin;
		result = prime * result + ((adminNotes == null) ? 0 : adminNotes.hashCode());
		result = prime * result + clanId;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + id;
		result = prime * result + isOfficer;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((profilePic == null) ? 0 : profilePic.hashCode());
		result = prime * result + settingsId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + verified;
		result = prime * result + winLossId;
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
		User other = (User) obj;
		if (admin != other.admin)
			return false;
		if (adminNotes == null) {
			if (other.adminNotes != null)
				return false;
		} else if (!adminNotes.equals(other.adminNotes))
			return false;
		if (clanId != other.clanId)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		if (id != other.id)
			return false;
		if (isOfficer != other.isOfficer)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (profilePic == null) {
			if (other.profilePic != null)
				return false;
		} else if (!profilePic.equals(other.profilePic))
			return false;
		if (settingsId != other.settingsId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (verified != other.verified)
			return false;
		if (winLossId != other.winLossId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", admin="
				+ admin + ", settingsId=" + settingsId + ", winLossId=" + winLossId + ", profilePic=" + profilePic
				+ ", clanId=" + clanId + ", isOfficer=" + isOfficer + ", adminNotes=" + adminNotes + ", hash=" + hash
				+ ", verified=" + verified + "]";
	}

}
