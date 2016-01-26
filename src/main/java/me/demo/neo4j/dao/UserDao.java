package me.demo.neo4j.dao;

import java.util.List;

import me.demo.neo4j.entity.User;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Neo4j dao
 * 
 * @author geosmart
 *
 */
public interface UserDao extends GraphRepository<User> {

  User findByLogin(String login);

  @Query("START root=node:User(login = 'root') MATCH root-[:knows]->friends RETURN friends")
  List<User> findFriendsOfRoot();

}
