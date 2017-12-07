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
@Table(name = "Games")
public class Game {
	@Id
	@Column(name = "GM_ID")
	@SequenceGenerator(name = "GM_seq", sequenceName = "GM_seq")
	@GeneratedValue(generator = "GM_seq", strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Status")
	private String status;

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
	private Timestamp postDate;

	// turn must be completed by this time
	@Column(name = "Turn_Deadline")
	private Timestamp turnDeadline;

	
	public Game(int id, String status, int player1Id, int player2Id, int turn, String boardState, String shipState,
			Timestamp postDate, Timestamp turnDeadline) {
		super();
		this.id = id;
		this.status = status;
		this.player1Id = player1Id;
		this.player2Id = player2Id;
		this.turn = turn;
		this.boardState = boardState;
		this.shipState = shipState;
		this.postDate = postDate;
		this.turnDeadline = turnDeadline;
	}


	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getPlayer1Id() {
		return player1Id;
	}


	public void setPlayer1Id(int player1Id) {
		this.player1Id = player1Id;
	}


	public int getPlayer2Id() {
		return player2Id;
	}


	public void setPlayer2Id(int player2Id) {
		this.player2Id = player2Id;
	}


	public int getTurn() {
		return turn;
	}


	public void setTurn(int turn) {
		this.turn = turn;
	}


	public String getBoardState() {
		return boardState;
	}


	public void setBoardState(String boardState) {
		this.boardState = boardState;
	}


	public String getShipState() {
		return shipState;
	}


	public void setShipState(String shipState) {
		this.shipState = shipState;
	}


	public Timestamp getPostDate() {
		return postDate;
	}


	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}


	public Timestamp getTurnDeadline() {
		return turnDeadline;
	}


	public void setTurnDeadline(Timestamp turnDeadline) {
		this.turnDeadline = turnDeadline;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boardState == null) ? 0 : boardState.hashCode());
		result = prime * result + id;
		result = prime * result + player1Id;
		result = prime * result + player2Id;
		result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
		result = prime * result + ((shipState == null) ? 0 : shipState.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + turn;
		result = prime * result + ((turnDeadline == null) ? 0 : turnDeadline.hashCode());
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
		Game other = (Game) obj;
		if (boardState == null) {
			if (other.boardState != null)
				return false;
		} else if (!boardState.equals(other.boardState))
			return false;
		if (id != other.id)
			return false;
		if (player1Id != other.player1Id)
			return false;
		if (player2Id != other.player2Id)
			return false;
		if (postDate == null) {
			if (other.postDate != null)
				return false;
		} else if (!postDate.equals(other.postDate))
			return false;
		if (shipState == null) {
			if (other.shipState != null)
				return false;
		} else if (!shipState.equals(other.shipState))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (turn != other.turn)
			return false;
		if (turnDeadline == null) {
			if (other.turnDeadline != null)
				return false;
		} else if (!turnDeadline.equals(other.turnDeadline))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Game [id=" + id + ", status=" + status + ", player1Id=" + player1Id + ", player2Id=" + player2Id
				+ ", turn=" + turn + ", boardState=" + boardState + ", shipState=" + shipState + ", postDate="
				+ postDate + ", turnDeadline=" + turnDeadline + "]";
	}
	
	
}
