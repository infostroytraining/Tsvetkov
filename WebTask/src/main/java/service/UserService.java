package main.java.service;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.DAO.exception.DAOException;
import main.java.DAO.impl.UserDAO;
import main.java.db.Transaction;
import main.java.db.TransactionManager;
import main.java.db.exception.TransactionException;
import main.java.entity.User;
import main.java.service.exception.ServiceException;

public class UserService {
	private static final Logger log = LogManager.getLogger();
	private UserDAO userDAO;
	private TransactionManager transactionManager;

	public UserService(UserDAO userDAO, TransactionManager transactionManager) {
		this.userDAO = userDAO;
		this.transactionManager = transactionManager;
	}

	public User add(User user) throws ServiceException {
		log.info("In UserService");
		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					return userDAO.create(user);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}

	}

	public User getUserById(int id) throws ServiceException {
		log.info("In UserService");
		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					return userDAO.get(id);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}

	}

	public User updateUser(User user) throws ServiceException {
		log.info("In UserService");
		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					return userDAO.update(user);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}
	public int getUserIdByUsername (String username) throws ServiceException{
		log.info("In UserService");
		try {
			return transactionManager.doTask(new Transaction<Integer>() {
				@Override
				public Integer execute() throws DAOException {
					return userDAO.getUserIdByUsername(username);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}
}
