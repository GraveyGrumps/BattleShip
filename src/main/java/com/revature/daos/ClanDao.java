package com.revature.daos;

import java.util.List;

import com.revature.entities.Clan;

public interface ClanDao {
	//POST
	Clan addClan(Clan clan);
	//GET
	Clan getClanById(int id);
	List<Clan> getAllClans();
	//PUT
	boolean modfiyClanNameById(int id, String clanName);
	boolean modifyClanChatLogById(int id, String chatLog);
	boolean modifyClanLogoById(int id, String logoPath);
	//DELETE
	boolean deleteClanById(int id);
}