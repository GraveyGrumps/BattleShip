package com.revature.daos;

import java.util.List;

import com.revature.entities.Game;

public interface GameDao {
	//POST
	Game addGame(Game game);
	//GET
	Game getGameById(int id);
	List<Game> getAllGames();
	List<Game> getAllPendingGames();
	List<Game> getAllGamesWithId(int id);
	//PUT
	Game modifyGameViaGame(Game game);
	boolean modifyGameStatusById(int id, String status);
	boolean modifyGamePlayer2ById(int id, int p2Id);
	boolean modifyGameTurnById(int id, int turn);
	boolean modifyGameBoardStateById(int id, String boardState);
	boolean modifyGameShipStateById(int id, String shipState);
	boolean modifyGameTurnLengthById(int id, int turnLength);
	//DELETE
	boolean deleteGameById(int id);
}
