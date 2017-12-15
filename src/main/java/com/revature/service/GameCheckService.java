package com.revature.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.revature.daos.GameDao;
import com.revature.daos.UserDao;
import com.revature.entities.Game;
import com.revature.entities.User;

/*
 * The purpose of this class is to check games and see if
 * their current time limit is exceeded.  If so, then the game
 * Needs to be canceled.
 */
@Configuration
@EnableScheduling
public class GameCheckService {

	@Autowired
	private EmailServiceImpl esi;

	private Logger log = Logger.getRootLogger();
	@Autowired
	private GameDao gd;
	@Autowired
	private UserDao ud;

	// check every 5 minutes
	@Scheduled(fixedDelay = 300000)
	public void checkAllGame() {
		log.info("checking all games to see if they should still be active");

		List<Game> games = gd.getAllGames();
		for (Game game : games) {
			checkGame(game);
		}
	}

	private void checkGame(Game game) {
		if (game.getTurnDeadline().before(Timestamp.valueOf(LocalDateTime.now()))) {
			log.trace("Deleting game with id: " + game.getId());
			gd.deleteGameById(game.getId());
		} else if (game.getStatus().equals("inprogress")) {
			gameWithinFiveMinutesOfEnding(game);
		}

	}

	private void gameWithinFiveMinutesOfEnding(Game game) {
		long end = game.getTurnDeadline().getTime();
		long now = Timestamp.valueOf(LocalDateTime.now()).getTime();
		long difference = end - now;
		log.warn(game.getTurnDeadline().getTime());
		log.warn(Timestamp.valueOf(LocalDateTime.now()).getTime());
		log.warn(difference / 60000.0);
		if (difference / 60000.0 < 5) {
			User user;
			if (game.getTurn() == 0) {
				user = ud.getUserById(game.getPlayer1Id());
			} else {
				user = ud.getUserById(game.getPlayer2Id());
			}
			sendEmail(difference, user);
		}

	}

	private void sendEmail(long difference, User user) {
		String message = "This is an Automated Message:\n\tYou have " + (difference / 60000.0)
				+ " minutes before you forfeit your game!";
		esi.sendMail("battleshipemailbot@gmail.com", user.getEmail(), "BATTLEBLIP: YOUR TURN IS ALMOST OVER", message);

	}
}
