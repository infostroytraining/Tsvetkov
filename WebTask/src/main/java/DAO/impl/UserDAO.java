package main.java.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.DAO.DAO;
import main.java.DAO.exception.DAOException;
import main.java.db.ConnectionHolder;
import main.java.db.Transaction;
import main.java.db.TransactionManager;
import main.java.entity.User;

public class UserDAO implements DAO<User> {
	private static final String INSERT_USER = "INSERT INTO users.users (firstname, lastname, email, login, password) values(?,?,?,?,?);";
	private static final String SELECT_USER = "SELECT firstname, lastname, email, login, password, image FROM users.users WHERE id=?;";
	private static final String UPDATE_USER = "UPDATE users.users SET firstname=?,lastname=?,email=?,login=?,password=?,image=?WHERE id=?;";
	private static final String GET_USER_BY_USERNAME = "SELECT id FROM users.users WHERE login=?;";
	private static final String DELETE_BY_ID = "DELETE FROM users.users WEHRE id=?;";
	private static final String SELECT_ALL_FROM_USERS = "SELECT * FROM users.users;";
	private static final Logger log = LogManager.getLogger();

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User create(User entity) {

		Connection connection = ConnectionHolder.getConnection();
		log.entry(entity.toString());
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_USER, statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setString(3, entity.getEmail());
			statement.setString(4, entity.getLogin());
			statement.setString(5, entity.getPassword());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				entity.setId(generatedKeys.getInt(1));
				// connection.commit();
			}

		} catch (SQLException e) {
			log.error("SQLException during insert USER");
			e.printStackTrace();
		}
		log.exit(entity.toString());
		return entity;
	}

	@Override
	public User get(int id) {
		Connection connection = ConnectionHolder.getConnection();
		User user = new User();
		log.entry(id);
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_USER);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setId(id);
				user.setImage(rs.getBytes("image"));
			}
		} catch (SQLException e) {
			log.error("SQLException during read USER by ID");
			e.printStackTrace();
		}
		log.exit(user.toString());
		return user;
	}

	@Override
	public User update(User entity) {
		Connection connection = ConnectionHolder.getConnection();
		log.entry(entity.toString());
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE_USER);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setString(3, entity.getEmail());
			statement.setString(4, entity.getLogin());
			statement.setString(5, entity.getPassword());
			statement.setBytes(6, entity.getImage());
			statement.setInt(7, entity.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			log.error("SQLException during update USER");
			e.printStackTrace();
		}
		log.info(entity.toString());
		return entity;
	}

	@Override
	public void remove(int id) {
		User user = new User();
		log.entry(id);
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_USER);
			statement.setInt(1, id);
		} catch (SQLException e) {
			log.error("SQLException during delete USER by ID");
			e.printStackTrace();
		}
		log.exit(user.toString());

	}

	@Override
	public List<User> getAll() {
		List<User> result = new ArrayList<>();
		log.info("get all users");
		Connection connection = ConnectionHolder.getConnection();
		User user = new User();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_ALL_FROM_USERS);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getInt("id"));
				user.setImage(rs.getBytes("image"));
				result.add(user);
			}
		} catch (SQLException e) {
			log.error("SQLException during read USER by ID");
			e.printStackTrace();
		}
		log.exit(result.size()+ "users in DB");
		return result;
	}

	public int getUserIdByUsername(String username) {
		Connection connection = ConnectionHolder.getConnection();
		int result = 0;
		User user = new User();
		log.entry(username);
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(GET_USER_BY_USERNAME);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				result = rs.getInt("id");
			}
			log.exit(user.toString());
		} catch (SQLException e) {
			log.error("SQLException during read USER by ID");
			e.printStackTrace();
		}
		return result;

	}

}
