package com.revature.daos;

import java.util.List;

import com.revature.entities.WinLoss;

public interface WinLossDao {
	//POST
	WinLoss addWinLoss(WinLoss winloss);
	//GET
	WinLoss getWinLossById(int id);
	List<WinLoss> getAllWinLoss();
	//PUT
	boolean changeWinLossWinsById(int id, int win);
	boolean changeWinLossLossesById(int id, int loss);
	boolean changeWinLossSeasonWinsById(int id, int win);
	boolean changeWinLossSeasonLossesById(int id, int loss);
	boolean clearWinLossSeason();
	//DELETE
	boolean deleteWinLossById(int id);
}
