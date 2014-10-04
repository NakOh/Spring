package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao(){
<<<<<<< HEAD:src/springbook/user/dao/CountingDaoFactory.java
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
		
=======
		return new UserDao(connectionMaker());
>>>>>>> parent of 252d95b... XML도�:src/springbook/user/dao/DaoFactory.java
	}
	

	@Bean
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}
	
	@Bean
	public DataSource dataSource(){
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/springbook");
		dataSource.setUsername("root");
		dataSource.setPassword("autoset");
		
		return dataSource;
	}
	
}
