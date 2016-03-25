package demo.neo4j.jcypher.dao.impl;

import iot.jcypher.domain.IDomainAccess;
import iot.jcypher.query.result.JcError;

import java.util.List;

import demo.neo4j.jcypher.config.Neo4jConfig;
import demo.neo4j.jcypher.dao.IUserDao;
import demo.neo4j.jcypher.entity.User;

/**
 * User Dao Implement
 * 
 * @author geosmart
 * 
 */
public class UserDaoImpl implements IUserDao {
  Neo4jConfig neo4jConfig;

  @Override
  public User findByLogin(String login) {
	IDomainAccess domainAccess = neo4jConfig.createDomainAccess();
	List<User> users = domainAccess.loadByType(User.class, -1, 0, 1);
	return users.get(0);
  }

  @Override
  public List<User> findFriendsOfRoot() {
	// TODO Auto-generated method stub
	return null;
  }

  @Override
  public void saveUser(List<User> users) {
	IDomainAccess domainAccess = neo4jConfig.createDomainAccess();
	List<JcError> errors = domainAccess.store(users);
	if (!errors.isEmpty())
	  printErrors(errors);
  }

  @Override
  public void saveUser(User user) {
	IDomainAccess domainAccess = neo4jConfig.createDomainAccess();
	List<JcError> errors = domainAccess.store(user);
	if (!errors.isEmpty())
	  printErrors(errors);
  }

  /**
   * print errors to System.out
   * 
   * @param result
   */
  private static void printErrors(List<JcError> errors) {
	StringBuilder sb = new StringBuilder();
	sb.append("---------------Errors:");
	appendErrorList(errors, sb);
	sb.append("\n---------------end Errors:");
	String str = sb.toString();
	System.out.println("");
	System.out.println(str);
  }

  private static void appendErrorList(List<JcError> errors, StringBuilder sb) {
	int num = errors.size();
	for (int i = 0; i < num; i++) {
	  JcError err = errors.get(i);
	  sb.append('\n');
	  if (i > 0) {
		sb.append("-------------------\n");
	  }
	  sb.append("codeOrType: ");
	  sb.append(err.getCodeOrType());
	  sb.append("\nmessage: ");
	  sb.append(err.getMessage());
	  if (err.getAdditionalInfo() != null) {
		sb.append("\nadditional info: ");
		sb.append(err.getAdditionalInfo());
	  }
	}
  }

  public Neo4jConfig getNeo4jConfig() {
	return neo4jConfig;
  }

  public void setNeo4jConfig(Neo4jConfig neo4jConfig) {
	this.neo4jConfig = neo4jConfig;
  }


}
