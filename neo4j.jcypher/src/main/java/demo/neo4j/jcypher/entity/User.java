package demo.neo4j.jcypher.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity
 * 
 * @author geosmart
 * 
 */
public class User {
  private String id;

  private String name;

  Set<User> friends;

  public User() {}

  public User(String id, String name) {
	this.id = id;
	this.name = name;
	this.friends = new HashSet<User>();
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

  public Set<User> getFriends() {
	return friends;
  }

  public void setFriends(Set<User> friends) {
	this.friends = friends;
  }


  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }



}
