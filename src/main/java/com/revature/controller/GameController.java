package com.revature.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.entities.Game;
import com.revature.service.GameService;

@Controller
@RequestMapping("game")
// need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class GameController {
	
	@Autowired
	private GameService gs;
	private Logger log = Logger.getRootLogger();
	
	@GetMapping("pending")
	public List<Game> getPendingGames(){
		log.info("Getting Pending Games");
		return gs.getPendingGames();
	}
	
	@GetMapping("load")
	@ResponseBody
	public Game loadGame(@RequestParam("id") int id) {
		log.info("Loading Game " + id);		
		return gs.loadGame(id);
	}
	
	@PostMapping("new")
	@ResponseBody
	public Game addNewGame(@RequestBody Game game) {
		log.info("Adding a new Game");
		return gs.addNewGame(game);
	}
	
	@PostMapping("start")
	public Game startGame(@RequestBody Game game) {
		log.info("Starting a game");
		log.trace("Game is: " + game);
		return gs.startGame(game);
	}

}
