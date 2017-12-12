package com.revature.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Friendships")
public class Friendship implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

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

    public Friendship(int user1Id, int user2Id, int pending, String chatLog) {
	super();
	this.user1Id = user1Id;
	this.user2Id = user2Id;
	this.pending = pending;
	this.chatLog = chatLog;
    }

    public Friendship() {
	super();
    }

    public int getUser1Id() {
	return user1Id;
    }

    public void setUser1Id(int user1Id) {
	this.user1Id = user1Id;
    }

    public int getUser2Id() {
	return user2Id;
    }

    public void setUser2Id(int user2Id) {
	this.user2Id = user2Id;
    }

    public int getPending() {
	return pending;
    }

    public void setPending(int pending) {
	this.pending = pending;
    }

    public String getChatLog() {
	return chatLog;
    }

    public void setChatLog(String chatLog) {
	this.chatLog = chatLog;
    }

    public void addToChatLog(String chatLog) {
	this.chatLog += chatLog;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((chatLog == null) ? 0 : chatLog.hashCode());
	result = prime * result + pending;
	result = prime * result + user1Id;
	result = prime * result + user2Id;
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
	Friendship other = (Friendship) obj;
	if (chatLog == null) {
	    if (other.chatLog != null)
		return false;
	} else if (!chatLog.equals(other.chatLog))
	    return false;
	if (pending != other.pending)
	    return false;
	if (user1Id != other.user1Id)
	    return false;
	if (user2Id != other.user2Id)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Friendship [user1Id=" + user1Id + ", user2Id=" + user2Id + ", pending=" + pending + ", chatLog="
		+ chatLog + "]";
    }

}
