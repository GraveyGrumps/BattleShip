package com.revature.daos;

import java.util.List;

import com.revature.entities.User;

public interface UserDao {

	//Post
	User addUser(User user);
	//Get
	User getUserByUsernameAndPassword(String username, String password);
	User getUserById(int id);
	List<User> getAllUsers();
	//Put
	boolean changeUserUserNameById(int id, String username);
	boolean changeUserPasswordById(int id, String password);
	boolean changeUserEmailById(int id, String email);
	boolean changeUserIsAdminById(int id, int isAdmin);
	boolean changeUserProfilePicById(int id, String picturePath);
	boolean changeUserIsOfficerById(int id, int isOfficer);
	boolean changeUserAdminNotesById(int id, String adminNotes);
	boolean changeUserVerifiedById(int id, int verified);
	//Delete
	boolean deleteUserById(int id);
	boolean deleteUserbyUser(User user);
}
