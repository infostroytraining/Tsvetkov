package main.java.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.DAO.impl.UserDAO;
import main.java.entity.User;

public class UserService {
	private static final Logger log = LogManager.getLogger();
	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User add(User user) {
		log.info("In UserService");
		User createdUser = null;
		if (user != null) {
			try {
				createdUser = userDAO.create(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return createdUser;
	}

	public User get(int id) {
		log.info("In UserService");
		User getUser = null;
		try {
			getUser = userDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getUser;

	}
}
