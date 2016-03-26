package demo.neo4j.jcypher.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.neo4j.jcypher.config.Neo4jConfig;
import demo.neo4j.jcypher.dao.IUserDao;
import demo.neo4j.jcypher.domain.User;

/**
 * UserDao TestCase
 * 
 * @author geosmart
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao.cfg.xml"})
public class TestUserDao {
  private static final int USER_COUNT = 4;

  @Autowired
  IUserDao userDao;

  @Autowired
  Neo4jConfig neo4jConfig;

  @Before
  public void setup() {
	System.out.println("......setup......");
  }

  @Test
  public void initUsers() {
	User root = new User("root", "Superuser");
	List<User> users = new ArrayList<User>();
	for (int i = 0; i < USER_COUNT; i++) {
	  users.add(new User(String.format("user %s", i), "guest" + i));
	}
	for (User user : users) {
	  root.knows(user);
	}
	userDao.saveUser(users);
	userDao.saveUser(root);
  }

  @Test
  public void test_findUserFriends() {
	List<User> users = userDao.findUserFriends("root");
	for (User user : users) {
	  System.out.println(user.getId());
	}
  }


  @Test
  public void clearDataBase() {
	userDao.clearDataBase();
  }

  @After
  public void teardown() {
	System.out.println("......teardown.......");
  }

}
