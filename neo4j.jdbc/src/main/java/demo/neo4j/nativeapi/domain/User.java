package demo.neo4j.nativeapi.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * User entity
 * 
 * @author geosmart
 * 
 */
public class User {
  private String id;

  private String name;

  List<User> friends;

  public User() {}

  public User(String id, String name) {
	this.id = id;
	this.name = name;
	this.friends = new ArrayList<User>();
  }

  public void knows(User user) {
	friends.add(user);
  }

  public String getId() {
	return id;
  }

  public void setId(String id) {
	this.id = id;
  }

  public List<User> getFriends() {
	return friends;
  }

  public void setFriends(List<User> friends) {
	this.friends = friends;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }



}
