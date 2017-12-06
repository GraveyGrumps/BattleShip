package com.revature.daos;

import java.sql.Timestamp;
import java.util.List;

import com.revature.entities.Ban;

public interface BanDao {
	//POST
	Ban addBan(Ban ban);
	//GET
	Ban getBanById(int id);
	List<Ban> getAllBans();
	//PUT
	boolean modifyBanStatusById(int id, String status);
	boolean modifyBannedUntilById(int id, Timestamp time);
	boolean modifyBanRecordById(int id, String record);
	//DELETE
	boolean deleteBanById(int id);

}
