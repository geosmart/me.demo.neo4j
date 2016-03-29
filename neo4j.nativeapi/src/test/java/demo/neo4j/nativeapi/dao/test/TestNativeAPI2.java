package demo.neo4j.nativeapi.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.neo4j.nativeapi.dao.IUserDao;
import demo.neo4j.nativeapi.domain.User;
import demo.neo4j.nativeapi.util.JsonUtil;

/**
 * UserDao TestCase
 * 
 * @author geosmart
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app.cfg.xml"})
public class TestNativeAPI2 {
  private static final int USER_COUNT = 10;
  @Autowired
  IUserDao userDao;

  @Before
  public void setup() {
	System.out.println("......setup......");
  }

  @Test
  public void test_createUser() {
	// create a circle relationship
	List<User> users = new ArrayList<User>();
	for (int i = 0; i < USER_COUNT; i++) {
	  String userId = String.format("user_%s", i);
	  int index = (i == USER_COUNT - 1 ? 0 : i + 1);
	  String friendId = String.format("user_%s", index);
	  User user = new User(userId, "guest" + i);
	  user.setFriendId(friendId);
	  System.out.println(JsonUtil.convertEntityObj2MapWithoutNull(user, false));
	  users.add(user);
	}
	userDao.createUser(users);
	userDao.createRelationships();
  }

  @Ignore
  @Test
  public void test_createRelationships() {
	userDao.createRelationships();
  }


  @Test
  public void test_findUser() {
	User user = userDao.findUser("user_1");
	System.out.println(user.getFriendId());
  }

  @Test
  public void test_findRelationUsers() {
	List<User> result = userDao.findRelationUsers("user_1");
	for (User user : result) {
	  System.out.println(user.getFriendId());
	}
  }


  @Test
  public void test_clearDatabase() {
	userDao.clearDatabase();
  }


  @After
  public void teardown() {
	System.out.println("......teardown.......");
  }

}
