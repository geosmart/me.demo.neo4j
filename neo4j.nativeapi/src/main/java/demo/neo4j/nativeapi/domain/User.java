package demo.neo4j.nativeapi.domain;


/**
 * User entity
 * 
 * @author geosmart
 * 
 */
public class User {
  private String id;

  private String name;

  private String note;

  private String friendId;

  public User() {}

  public User(String id, String name) {
	this.id = id;
	this.name = name;
  }


  public String getId() {
	return id;
  }

  public void setId(String id) {
	this.id = id;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public String getNote() {
	return note;
  }

  public void setNote(String note) {
	this.note = note;
  }

  public String getFriendId() {
	return friendId;
  }

  public void setFriendId(String friendId) {
	this.friendId = friendId;
  }



}
