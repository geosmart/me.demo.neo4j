package demo.neo4j.jcypher.dao.impl;

import iot.jcypher.domain.IDomainAccess;
import iot.jcypher.query.result.JcError;

import java.util.List;

import demo.neo4j.jcypher.config.Neo4jConfig;
import demo.neo4j.jcypher.dao.IAddressNodeDao;
import demo.neo4j.jcypher.domain.AddressNode;
import demo.neo4j.jcypher.util.Util;

/**
 * User Dao Implement
 * 
 * @author geosmart
 * 
 */
public class AddressNodeDaoImpl implements IAddressNodeDao {
  Neo4jConfig neo4jConfig;


  public Neo4jConfig getNeo4jConfig() {
	return neo4jConfig;
  }

  public void setNeo4jConfig(Neo4jConfig neo4jConfig) {
	this.neo4jConfig = neo4jConfig;
  }

  @Override
  public void saveAddressNode(AddressNode addressNode) {
	IDomainAccess domainAccess = neo4jConfig.createDomainAccess();
	List<JcError> errors = domainAccess.store(addressNode);
	if (!errors.isEmpty())
	  Util.printErrors(errors);
  }

  @Override
  public void getAddressNode(String id) {
	// TODO Auto-generated method stub

  }

}
