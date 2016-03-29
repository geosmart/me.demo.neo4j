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
  List<User> findUserFriends(String id);


  /**
   * save single List<User>
   * 
   * @param user
   */
  void saveUser(List<User> users);

  /**
   * save single User
   * 
   * @param user
   */
  void saveUser(User user);

  /**
   * clear UserDB
   */
  void clearDataBase();

}
