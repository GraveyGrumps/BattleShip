package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.WinLossDao;
import com.revature.entities.Game;
import com.revature.entities.User;
import com.revature.entities.WinLoss;
import com.revature.util.ValidationUtil;

@Service
public class WinLossService {
	@Autowired
	private WinLossDao wld;
	private Logger log = Logger.getRootLogger();
	public List<WinLoss> getAllWinLosses() {
		return wld.getAllWinLoss();
	}
	public WinLoss getWinLossById(int id) {
		return wld.getWinLossById(id);
	}
	public WinLoss updateWL(WinLoss wL) {
		return wld.modifyWinlossViaWinloss(wL);
	}

}
