package me.demo.neo4j.dao;

import java.util.List;

import me.demo.neo4j.entity.AddressNode;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Neo4j dao
 * 
 * @author geosmart
 *
 */
public interface AddressNodeDao extends GraphRepository<AddressNode> {


  @Query("MATCH  (an:AddressNode) where an.ruleabbr ='DIS'   return an ")
  List<AddressNode> findAddressNodes();

}
