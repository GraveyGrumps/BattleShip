package com.revature.daos;

import java.util.List;

import com.revature.entities.WinLoss;

public interface WinLossDao {
	//POST
	WinLoss addWinLoss(WinLoss winloss);
	
	//DELETE
	boolean deleteWinLossById(int id);
	WinLoss modify(WinLoss wL);

    // GET
    WinLoss getWinLossById(int id);

    List<WinLoss> getAllWinLoss();

    // PUT
    boolean modifyWinLossWinsById(int id, int win);

    boolean modifyWinLossLossesById(int id, int loss);

    boolean modifyWinLossSeasonWinsById(int id, int win);

    boolean modifyWinLossSeasonLossesById(int id, int loss);

    boolean clearWinLossSeasonById(int id);
}
