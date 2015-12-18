package main.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.DAO.exception.DAOException;
import main.java.db.exception.TransactionException;

public class TransactionManager {
	private final Logger log = LogManager.getLogger();
	private String dbURL = "jdbc:postgresql://localhost:5432/webbd";
	private String username = "postgres";
	private String password = "root";
	private Connection connection;

	public <T> T doTask(Transaction<T> transaction, int transactionIsolation) throws TransactionException {
		try {
			try {
				connection = DriverManager.getConnection(dbURL, username, password);
				ConnectionHolder.setConnection(connection);
				if (connection != null) {
					log.info("BD connected");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			T result = transaction.execute();
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		} catch (DAOException e) {
			throw new TransactionException(e);
		}
	}
}
