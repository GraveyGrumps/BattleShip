package com.revature.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.entities.User;

@Repository
public class UserDaoHibernate implements UserDao {
	private Logger log = Logger.getRootLogger();
	@Autowired
	SessionFactory sf;

	@Override
	@Transactional
	public User addUser(User user) throws ConstraintViolationException {

		sf.getCurrentSession().save(user);
		return user;
	}

	@Override
	@Transactional
	public User getUserByUsernameAndPassword(String username, String password) {
		log.trace("Geting user by username: " + username + " and password: " + password);
		Session session = sf.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.and(Restrictions.eq("username", username), Restrictions.eq("password",password)));
		return (User) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		log.trace("Getting user by id: " + id);
		Session session = sf.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		return (User) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAllUsers() {
		log.trace("Getting all users");
		Session session = sf.getCurrentSession();
		return (List<User>) session.createCriteria(User.class).list();
	}

	@Override
	@Transactional
	public boolean modifyUserUserNameById(int id, String username) {
		log.trace("Modifying user name: " + username + " where id: " + id);
		Session session = sf.getCurrentSession();
		User user = getUserById(id);
		if(user == null)
			return false;
		user.setUsername(username);
		session.merge(user);
		session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean modifyUserPasswordById(int id, String password) {
		log.trace("Modifying password: " + password + " where id: " + id);
		Session session = sf.getCurrentSession();
		User user = getUserById(id);
		if(user == null)
			return false;
		user.setPassword(password);
		session.merge(user);
		session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean modifyUserEmailById(int id, String email) {
		log.trace("Modifying email: " + email + " where id: " + id);
		Session session = sf.getCurrentSession();
		User user = getUserById(id);
		if(user == null)
			return false;
		user.setEmail(email);
		session.merge(user);
		session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean modifyUserIsAdminById(int id, int isAdmin) {
		log.trace("Modifying user is Admin: " + isAdmin + " where id: " + id);
		Session session = sf.getCurrentSession();
		User user = getUserById(id);
		if(user == null)
			return false;
		user.setAdmin(isAdmin);
		session.merge(user);
		session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean modifyUserProfilePicById(int id, String picturePath) {
		log.trace("Modifying profilepic: " + picturePath + " where id: " + id);
		Session session = sf.getCurrentSession();
		User user = getUserById(id);
		if(user == null)
			return false;
		user.setProfilePic(picturePath);
		session.merge(user);
		session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean modifyUserIsOfficerById(int id, int isOfficer) {
		log.trace("Modifying isOfficer: " + isOfficer + " where id: " + id);
		Session session = sf.getCurrentSession();
		User user = getUserById(id);
		if(user == null)
			return false;
		user.setIsOfficer(isOfficer);
		session.merge(user);
		session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean modifyUserAdminNotesById(int id, String adminNotes) {
		log.trace("setting AdminNotes: " + adminNotes + " where id: " + id);
		Session session = sf.getCurrentSession();
		User user = getUserById(id);
		if(user == null)
			return false;
		user.setAdminNotes(adminNotes);
		session.merge(user);
		session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean modifyUserVerifiedById(int id, int verified) {
		log.trace("Modifying verified: " + verified + " where id: " + id);
		Session session = sf.getCurrentSession();
		User user = getUserById(id);
		if(user == null)
			return false;
		user.setVerified(verified);
		session.merge(user);
		session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean deleteUserById(int id) {
		log.trace("Deleting user with id: " + id);
		Session session = sf.getCurrentSession();
		User user = getUserById(id);
		if(user == null)
			return false;
		session.delete(user);
		session.close();
		return true;
	}

	@Override
	@Transactional
	public boolean modifyUserAppendAdminNotesById(int id, String adminNotes) {
		log.trace("Appending AdminNotes: " + adminNotes + " where id: " + id);
		Session session = sf.getCurrentSession();
		User user = getUserById(id);
		if(user == null)
			return false;
		user.appendAdminNotes(adminNotes);
		session.merge(user);
		session.close();
		return true;
	}

	@Override
	@Transactional
	public User modifyWholeUser(User u) {
		log.trace("Modifying entire user via a user object");
		Session session = sf.getCurrentSession();
		session.merge(u);
		return u;
	}

}