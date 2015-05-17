package springbook.user.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static springbook.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static springbook.user.service.UserService.MIN_RECCOMEND_FOR_GOLD;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.Level;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/springbook/user/dao/applicationContext.xml")
public class UserServiceTest {
	@Autowired
	UserService userService;
	List<User> users;

	@Before
	public void setUp() {
		users = Arrays.asList(new User("bumjin", "박범진", "p1", Level.BASIC,
				MIN_LOGCOUNT_FOR_SILVER - 1, 0), new User("joytouch", "강명성",
				"p2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0), new User(
				"erwins", "신승한", "p3", Level.SILVER,
				MIN_RECCOMEND_FOR_GOLD - 1, 29), new User("madnite1", "이상호",
				"p4", Level.SILVER, MIN_RECCOMEND_FOR_GOLD, 30), new User(
				"green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE));
	}

	@Test
	public void bean() {
		assertThat(this.userService, is(notNullValue()));
	}

	@Test
	public void upgradeLevels() {
		userService.userDao.deleteAll();
		for (User user : users)
			userService.userDao.add(user);
		userService.upgradeLevels();

		checkLevel(users.get(0), false);
		checkLevel(users.get(1), true);
		checkLevel(users.get(2), false);
		checkLevel(users.get(3), true);
		checkLevel(users.get(4), false);
	}

	@Test
	public void add() {
		userService.userDao.deleteAll();
		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);

		userService.add(userWithLevel);
		userService.add(userWithoutLevel);

		User userWithLevelRead = userService.userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userService.userDao.get(userWithoutLevel
				.getId());

		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));

	}

	private void checkLevel(User user, boolean upgraded) {
		User userUpdate = userService.userDao.get(user.getId());
		if (upgraded) {
			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
		} else {
			assertThat(userUpdate.getLevel(), is(user.getLevel()));
		}
	}
}
