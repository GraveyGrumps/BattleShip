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

	public WinLoss(int id, int seasonWins, int seasonLosses, int wins, int losses) {
		super();
		this.id = id;
		this.seasonWins = seasonWins;
		this.seasonLosses = seasonLosses;
		this.wins = wins;
		this.losses = losses;
	}

	public WinLoss() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeasonWins() {
		return seasonWins;
	}

	public void setSeasonWins(int seasonWins) {
		this.seasonWins = seasonWins;
	}

	public int getSeasonLosses() {
		return seasonLosses;
	}

	public void setSeasonLosses(int seasonLosses) {
		this.seasonLosses = seasonLosses;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + losses;
		result = prime * result + seasonLosses;
		result = prime * result + seasonWins;
		result = prime * result + wins;
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
		WinLoss other = (WinLoss) obj;
		if (id != other.id)
			return false;
		if (losses != other.losses)
			return false;
		if (seasonLosses != other.seasonLosses)
			return false;
		if (seasonWins != other.seasonWins)
			return false;
		if (wins != other.wins)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WinLoss [id=" + id + ", seasonWins=" + seasonWins + ", seasonLosses=" + seasonLosses + ", wins=" + wins
				+ ", losses=" + losses + "]";
	}
	
	
}
