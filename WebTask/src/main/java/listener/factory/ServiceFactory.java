package main.java.listener.factory;

import main.java.DAO.impl.UserDAO;
import main.java.db.TransactionManager;
import main.java.service.UserService;

public class ServiceFactory {

	public static UserService getUserService() {
		UserDAO userDAO = new UserDAO();
		TransactionManager transactionManager = new TransactionManager();
		return new UserService(userDAO, transactionManager);
	}

	private static void loadPostgreDrivet() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
