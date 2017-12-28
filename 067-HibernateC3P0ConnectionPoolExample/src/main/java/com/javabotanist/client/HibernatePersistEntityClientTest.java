package com.javabotanist.client;

import com.javabotanist.dao.UserDAO;
import com.javabotanist.entities.User;

public class HibernatePersistEntityClientTest {

	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		User user = getUser();
		userDAO.createUser(user);
	}

	public static User getUser() {
		User user = new User();
		user.setFirstName("Nithin");
		user.setLastName("Prasad");
		return user;
	}
	
}