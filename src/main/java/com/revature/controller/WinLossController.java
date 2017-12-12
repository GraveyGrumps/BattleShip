package com.revature.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.entities.Game;
import com.revature.entities.WinLoss;
import com.revature.service.WinLossService;

@Controller
@RequestMapping("winloss")
// need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class WinLossController {
	private Logger log = Logger.getRootLogger();
	@Autowired
	private WinLossService wls;
	
	@GetMapping
	@ResponseBody
	public List<WinLoss> getAllWinLosses(){
		log.info("Getting all winLosses");
		return wls.getAllWinLosses();
	}
	@GetMapping("{id}")
	@ResponseBody
	public WinLoss getWinLossById(@PathVariable int id) {
		log.info("Getting winloss by id");
		log.trace("id is: " + id);
		return wls.getWinLossById(id);
	}
	@PutMapping("modify")
	@ResponseBody
	public WinLoss modifyGame(@RequestBody WinLoss WL) {
		log.info("Modifying a W/L");
		return wls.updateWL(WL);
	}
}

