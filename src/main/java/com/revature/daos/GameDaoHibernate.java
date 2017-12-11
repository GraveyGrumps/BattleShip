package com.revature.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.entities.Game;

@Repository
public class GameDaoHibernate implements GameDao {
    private Logger log = Logger.getRootLogger();
    @Autowired
    private SessionFactory sf;

    @Override
    @Transactional
    public Game addGame(Game game) {
	log.trace("adding a game to the DataBase");
	sf.getCurrentSession().save(game);
	return game;
    }

    @Override
    @Transactional
    public Game getGameById(int id) {
	log.trace("Getting game by Id: " + id);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Game.class);
	criteria.add(Restrictions.eq("id", id));
	return (Game) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Game> getAllGames() {
	log.trace("Getting all games");
	Session session = sf.getCurrentSession();
	return (List<Game>) session.createCriteria(Game.class).list();
    }

    @Override
    @Transactional
    public boolean modifyGameStatusById(int id, String status) {
	log.trace("modifying game status: " + status + " where id:" + id);
	Session session = sf.getCurrentSession();
	Game game = getGameById(id);
	if (game == null)
	    return false;
	game.setStatus(status);
	session.merge(game);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyGamePlayer2ById(int id, int p2Id) {
	log.trace("Adding p2 of id : " + p2Id + " to game with id: " + id);
	Session session = sf.getCurrentSession();
	Game game = getGameById(id);
	if (game == null)
	    return false;
	game.setPlayer2Id(p2Id);
	session.merge(game);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyGameTurnById(int id, int turn) {
	log.trace("Setting Turn: " + turn + " to game with id: " + id);
	Session session = sf.getCurrentSession();
	Game game = getGameById(id);
	if (game == null)
	    return false;
	game.setTurn(turn);
	session.merge(game);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyGameBoardStateById(int id, String boardState) {
	log.trace("Setting boardState: " + boardState + " to game with id: " + id);
	Session session = sf.getCurrentSession();
	Game game = getGameById(id);
	if (game == null)
	    return false;
	game.setBoardState(boardState);
	session.merge(game);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyGameShipStateById(int id, String shipState) {
	log.trace("Setting shipState: " + shipState + " to game with id: " + id);
	Session session = sf.getCurrentSession();
	Game game = getGameById(id);
	if (game == null)
	    return false;
	game.setShipState(shipState);
	session.merge(game);
	return true;
    }

    @Override
    @Transactional
    public boolean deleteGameById(int id) {
	log.trace("Deleting game with id: " + id);
	Session session = sf.getCurrentSession();
	Game game = getGameById(id);
	if (game == null)
	    return false;
	session.delete(game);
	return true;
    }

    @Override
    @Transactional
    public boolean modifyGameTurnLengthById(int id, int turnLength) {
	log.trace("Setting Turn Length: " + turnLength + " to game with id: " + id);
	Session session = sf.getCurrentSession();
	Game game = getGameById(id);
	if (game == null)
	    return false;
	game.setTurnLength(turnLength);
	session.merge(game);
	return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Game> getAllPendingGames() {
	log.trace("Getting all pending games");
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Game.class);
	criteria.add(Restrictions.ilike("status", "pending"));
	return (List<Game>) criteria.list();
    }

    @Override
    @Transactional
    public Game modifyGameViaGame(Game game) {
	log.trace("merging game via game: " + game);
	Session session = sf.getCurrentSession();
	session.merge(game);
	return game;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Game> getAllGamesWithId(int id) {
	log.trace("Getting all games with id: " + id);
	Session session = sf.getCurrentSession();
	Criteria criteria = session.createCriteria(Game.class);
	criteria.add(
		Restrictions.disjunction().add(Restrictions.eq("player1Id", id)).add(Restrictions.eq("player2Id", id)));
	return (List<Game>) criteria.list();
    }

}
