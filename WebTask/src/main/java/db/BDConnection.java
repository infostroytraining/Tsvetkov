package main.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BDConnection {
	private static final Logger log = LogManager.getLogger();
	private static String dbURL = "jdbc:postgresql://localhost:5432/webbd";
	private static String username = "postgres";
	private static String password = "root";
	private static Connection connection;

	static {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(dbURL, username, password);
			// connection.setAutoCommit(false);
			if (connection != null) {
				log.info("BD connected");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}
