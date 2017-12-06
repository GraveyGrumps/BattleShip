package com.revature.daos;

import java.util.List;

import com.revature.entities.Game;

public interface GameDao {
	//POST
	Game addGame(Game game);
	//GET
	Game getGameById(int id);
	List<Game> getAllGames();
	//PUT
	boolean changeGameStatusById(int id, String status);
	boolean addPlayer2ToGameById(int id, int p2Id);
	boolean changeGameTurnById(int id, int turn);
	boolean changeGameBoardStateById(int id, String boardState);
	boolean changeGameShipStateById(int id, String shipState);
	//DELETE
	boolean deleteGameById(int id);
	boolean deleteGamebyGame(Game game);
}
