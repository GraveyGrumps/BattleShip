package com.revature.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
	private Logger log = Logger.getRootLogger();

	public List<Game> getPendingGames() {
		log.info("Service is calling dao to get all pending games");
		return gd.getAllPendingGames();
	}

	/**
	 * On game init the client sends: p1id, p2id, boardstate, shipstate, and turn
	 * length; The server will handle setting: status, turn, postdate, and turn
	 * deadline
	 */
	public Game addNewGame(Game game) {
		game.setPostDate(Timestamp.valueOf(LocalDateTime.now()));
		game.setStatus("pending");
		game.setTurn(0);
		game.setTurnDeadline(setTurnDeadline(game.getTurnLength()));
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

	public Game updateGame(Game game) {
		log.trace("service is updating game");
		game.setTurnDeadline(setTurnDeadline(game.getTurnLength()));
		return gd.modifyGameViaGame(game);
	}

	public List<Game> getMyGames(int id) {
		log.trace("service is getting all games that has id: " + id);
		return gd.getAllGamesWithId(id);
	}

	public List<Game> getAllGames() {
		log.trace("service is getting all games");
		return gd.getAllGames();
	}

	public Game startGame(Game game) {
		log.trace("this may need to be deleted");
		return null;
	}

	public Game loadGame(int id) {
		log.trace("getting game with game id of: " + id);
		return gd.getGameById(id);
	}

	private Timestamp setTurnDeadline(int turnlen) {
		log.trace("setting turn deadline");
		LocalDateTime turnEnds = LocalDateTime.now().plusMinutes(turnlen);
		log.trace("turn ends: " + turnEnds);
		return Timestamp.valueOf(turnEnds);
	}

	public void delete(int id) {
		gd.deleteGameById(id);
	}

}
