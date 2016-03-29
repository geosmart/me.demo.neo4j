package demo.neo4j.jcypher.dao;

import demo.neo4j.jcypher.domain.AddressNode;

/**
 * User Dao
 * 
 * @author geosmart
 * 
 */
public interface IAddressNodeDao {

  void saveAddressNode(AddressNode addressNode);


  void getAddressNode(String id);
}
