package com.revature.daos;

import java.util.List;

import com.revature.entities.Clan;

public interface ClanDao {
    // POST
    Clan addClan(Clan clan);

    // GET
    Clan getClanById(int id);

    List<Clan> getAllClans();

    // PUT
    Clan modifyClanNameById(int id, String clanName);

    String modifyClanChatLogById(int id, String chatLog);

    Clan modifyClanLogoById(int id, String logoPath);

    boolean clearClanChatById(int id);

    // DELETE
    boolean deleteClanById(int id);
}
