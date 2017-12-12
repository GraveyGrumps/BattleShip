package com.revature.daos;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.revature.entities.User;

public interface UserDao {

    // Post
    User addUser(User user) throws ConstraintViolationException;

    // Get
    User getUserByUsernameAndPassword(String username, String password);

    User getUserById(int id);

    List<User> getAllUsers();

    User getUserByWinlossId(int id);

    // Put
    boolean modifyUserUserNameById(int id, String username);

    boolean modifyUserPasswordById(int id, String password);

    boolean modifyUserEmailById(int id, String email);

    boolean modifyUserIsAdminById(int id, int isAdmin);

    boolean modifyUserProfilePicById(int id, String picturePath);

    boolean modifyUserIsOfficerById(int id, int isOfficer);

    boolean modifyUserAdminNotesById(int id, String adminNotes);

    boolean modifyUserAppendAdminNotesById(int id, String adminNotes);

    boolean modifyUserVerifiedById(int id, int verified);

    User modifyUserViaUser(User u);

    // Delete
    boolean deleteUserById(int id);

}
