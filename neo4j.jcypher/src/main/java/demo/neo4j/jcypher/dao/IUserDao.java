package demo.neo4j.jcypher.dao;

import java.util.List;

import demo.neo4j.jcypher.entity.User;

/**
 * User Dao
 * 
 * @author geosmart
 * 
 */
public interface IUserDao {

  void saveUser(List<User> users);

  void saveUser(User user);

  User findByLogin(String login);

  List<User> findFriendsOfRoot();



}
