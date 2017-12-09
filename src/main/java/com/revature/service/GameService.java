package com.revature.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.GameDao;
import com.revature.daos.ReportDao;
import com.revature.entities.Game;
import com.revature.entities.Report;

@Service
public class GameService {

	@Autowired
	private GameDao gd;
	@Autowired
	private Report report;
	@Autowired
	private ReportDao rd;
	private Random random = new Random();
	private Logger log = Logger.getRootLogger();
	
	public List<Game> getPendingGames() {
		log.info("Service is calling dao to get all pending games");
		return gd.getAllPendingGames();
	}

	/* On game init the client sends: p1id, p2id, boardstate, shipstate,
	 * and turn length;
	 * The server will handle setting: status, turn, postdate, and turn deadline
	 */
	public Game addNewGame(Game game) {
		game.setPostDate(Timestamp.valueOf(LocalDateTime.now()));
		game.setStatus("pending");
		game.setTurn(random.nextInt(2));
		game.setTurnDeadline(new Timestamp(0));
		log.info("service has finished setting up game: " + game);
		log.info("service is sending game to dao");
		game = gd.addGame(game);
		log.info("service is creating a report for the game via gameid: " + game.getId());
		report.setGameId(game.getId());
		log.info("service is sending report to dao");
		rd.addReport(report);
		log.info("service is completed and returning game: " + game);
		return game;
	}

	public Game startGame(Game game) {
		
		return null;
	}

	
}
