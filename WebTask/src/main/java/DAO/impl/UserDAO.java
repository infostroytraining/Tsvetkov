package main.java.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.DAO.DAO;
import main.java.db.BDConnection;
import main.java.entity.User;

public class UserDAO implements DAO<User> {
	private static final String INSERT_USER = "INSERT INTO users.users (firstname, lastname, email, login, password) values(?,?,?,?,?);";
	private static final String SELECT_USER = "SELECT firstname, lastname, email, login, password, image FROM users.users WHERE id=?;";
	private static final String UPDATE_USER = "UPDATE users.users SET firstname=?,lastname=?,email=?,login=?,password=?,image=?WHERE id=?;";
	private static final Logger log = LogManager.getLogger();

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User create(User entity) {
		log.entry(entity.toString());
		PreparedStatement statement = null;
		try {
			statement = BDConnection.getConnection().prepareStatement(INSERT_USER, statement.RETURN_GENERATED_KEYS);
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
		User user = new User();
		log.entry(id);
		PreparedStatement statement = null;
		try {
			statement = BDConnection.getConnection().prepareStatement(SELECT_USER);
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
		log.entry(entity.toString());
		PreparedStatement statement = null;
		try {
			statement = BDConnection.getConnection().prepareStatement(UPDATE_USER);
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
		log.exit(entity.toString());
		return entity;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAll() {

		return null;
	}

}
