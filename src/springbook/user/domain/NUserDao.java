package springbook.user.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import springbook.user.dao.UserDao;

public class NUserDao extends UserDao{

	@Override
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysq://localhost/springbook", "root", "autoset");
		return c;
	}

	
}
