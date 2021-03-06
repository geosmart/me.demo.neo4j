package demo.neo4j.nativeapi.dao;

import java.util.List;

import demo.neo4j.nativeapi.domain.User;

/**
 * User Dao
 * 
 * @author geosmart
 * 
 */
public interface IUserDao {

  /**
   * query user's friends
   * 
   * @cypher MATCH (ee:User)-[:friends]-(friends) WHERE ee.id = "root" RETURN friends
   * 
   * @param id
   * @return
   */
  User findUser(String id);


  /**
   * find user's relationship by userid
   * 
   * @see default set traverser depth 1
   * @param id
   * @return
   */
  List<User> findRelationUsers(String id);

  void createRelationships();

  /**
   * save single List<User>
   * 
   * @param user
   */
  void createUser(List<User> users);

  /**
   * save single User
   * 
   * @param user
   */
  void createUser(User user);

  /**
   * clear UserDB
   */
  void clearDatabase();
}
