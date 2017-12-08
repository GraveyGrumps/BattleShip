package com.revature.util;

import org.springframework.stereotype.Component;

import com.revature.entities.User;

@Component
public class ValidationUtil {

	public boolean validateAccess(User u, int id) {
		//isAdmin value == 1 then admin, isAdmin value == 0, then isn't admin
		return (u.getId() == id || u.getAdmin() == 1);
	}
}
