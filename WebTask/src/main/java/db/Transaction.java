package main.java.db;

import main.java.DAO.exception.DAOException;

public interface Transaction<T> {

	public T execute() throws DAOException;

}
