package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao() {
		UserDaoJdbc userDao = new UserDaoJdbc();
		userDao.setDataSource(dataSource());
		return userDao;

	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		return dataSource;
	}

}
