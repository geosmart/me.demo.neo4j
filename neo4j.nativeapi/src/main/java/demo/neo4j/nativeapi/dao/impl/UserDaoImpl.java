package demo.neo4j.nativeapi.dao.impl;

import java.util.List;

import demo.neo4j.nativeapi.dao.IUserDao;
import demo.neo4j.nativeapi.domain.User;

/**
 * User Dao Implement
 * 
 * @author geosmart
 * 
 */
public class UserDaoImpl implements IUserDao {

  @Override
  public List<User> findUserFriends(String id) {
	// TODO Auto-generated method stub
	return null;
  }

  @Override
  public void saveUser(List<User> users) {
	// TODO Auto-generated method stub

  }

  @Override
  public void saveUser(User user) {
	// TODO Auto-generated method stub

  }

  @Override
  public void clearDataBase() {
	// TODO Auto-generated method stub

  }


}
