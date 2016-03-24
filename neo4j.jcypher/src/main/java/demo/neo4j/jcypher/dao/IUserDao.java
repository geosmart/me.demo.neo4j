package demo.neo4j.jcypher.dao;

import java.util.List;

import demo.neo4j.jcypher.entity.User;

/**
 * Neo4j dao
 * 
 * @author geosmart
 *
 */
public interface IUserDao {

  User findByLogin(String login);

  List<User> findFriendsOfRoot();

  void createMovieDatabaseByGraphModel();

}
