package demo.neo4j.nativeapi.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import demo.neo4j.nativeapi.dao.IUserDao;
import demo.neo4j.nativeapi.domain.User;

/**
 * User Dao Implement
 * 
 * @see https://github.com/neo4j-examples/movies-java-spring-boot-jdbc
 * @see https://github.com/neo4j-contrib/neo4j-jdbc
 * @author geosmart
 * 
 */
public class UserDaoImpl implements IUserDao {

  @Autowired
  JdbcTemplate template;

  @Override
  public List<User> findUserFriends(String id) {
	String cql = "MATCH (u:User{id:?}) return u";

	// return template.queryForList(cql);
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
