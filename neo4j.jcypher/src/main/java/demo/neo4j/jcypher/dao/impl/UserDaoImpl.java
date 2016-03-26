package demo.neo4j.jcypher.dao.impl;

import iot.jcypher.database.IDBAccess;
import iot.jcypher.domain.IDomainAccess;
import iot.jcypher.domainquery.DomainQuery;
import iot.jcypher.domainquery.DomainQueryResult;
import iot.jcypher.domainquery.api.DomainObjectMatch;
import iot.jcypher.query.result.JcError;
import iot.jcypher.query.result.JcResultException;

import java.util.List;

import demo.neo4j.jcypher.config.Neo4jConfig;
import demo.neo4j.jcypher.dao.IUserDao;
import demo.neo4j.jcypher.domain.User;
import demo.neo4j.jcypher.util.Util;

/**
 * User Dao Implement
 * 
 * @author geosmart
 * 
 */
public class UserDaoImpl implements IUserDao {
  Neo4jConfig neo4jConfig;

  @Override
  public List<User> findUserFriends(String id) {
	IDomainAccess domainAccess = neo4jConfig.createDomainAccess();
	DomainQuery q = domainAccess.createQuery();
	// create DomainObjectMatches
	DomainObjectMatch<User> userMatch = q.createMatch(User.class);
	q.WHERE(userMatch.atttribute("id")).EQUALS(id);

	DomainObjectMatch<User> usersFriendsMatch = q.TRAVERSE_FROM(userMatch).FORTH("friends").TO(User.class);

	DomainQueryResult result = q.execute();
	List<User> users = result.resultOf(usersFriendsMatch);

	return users;
  }

  @Override
  public void saveUser(List<User> users) {
	IDomainAccess domainAccess = neo4jConfig.createDomainAccess();
	List<JcError> errors = domainAccess.store(users);
	if (!errors.isEmpty())
	  Util.printErrors(errors);
  }

  @Override
  public void saveUser(User user) {
	IDomainAccess domainAccess = neo4jConfig.createDomainAccess();
	List<JcError> errors = domainAccess.store(user);
	if (!errors.isEmpty())
	  Util.printErrors(errors);
  }

  @Override
  public void clearDataBase() {
	IDBAccess dbAccess = neo4jConfig.getDbAccess();
	List<JcError> errors = dbAccess.clearDatabase();
	if (errors.size() > 0) {
	  Util.printErrors(errors);
	  throw new JcResultException(errors);
	}

  }


  public Neo4jConfig getNeo4jConfig() {
	return neo4jConfig;
  }

  public void setNeo4jConfig(Neo4jConfig neo4jConfig) {
	this.neo4jConfig = neo4jConfig;
  }


}
