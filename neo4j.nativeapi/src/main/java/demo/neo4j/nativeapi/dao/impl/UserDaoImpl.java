package demo.neo4j.nativeapi.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;

import demo.neo4j.nativeapi.dao.IUserDao;
import demo.neo4j.nativeapi.domain.User;
import demo.neo4j.nativeapi.domain.UserRelationShipType;
import demo.neo4j.nativeapi.util.JsonUtil;

/**
 * User Dao Implement
 * 
 * @author geosmart
 * 
 */
@Repository
public class UserDaoImpl implements IUserDao {
  @Autowired
  GraphDatabaseService graphDBService;

  Label label = DynamicLabel.label(User.class.getSimpleName());

  @Override
  public User findUser(String id) {
	User user = null;
	try (Transaction tx = graphDBService.beginTx()) {
	  Node node = graphDBService.findNode(label, "id", id);
	  Map<String, Object> properties = node.getAllProperties();
	  user = JsonUtil.ConvertMap2POJO(properties, new TypeReference<User>() {});
	  tx.success();
	}
	return user;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public void createUser(List<User> users) {
	try (Transaction tx = graphDBService.beginTx()) {
	  for (User user : users) {
		Node node = graphDBService.createNode(label);
		Map<String, Object> map = JsonUtil.convertEntityObj2MapWithoutNull(user, false);
		for (String prop : map.keySet()) {
		  Object value = map.get(prop);
		  if (value != null) {
			if (value instanceof Collection) {
			  if (((Collection) value).size() > 0) {
				// XXX SET Map and other Type Handler
				node.setProperty(prop, ((List) value).toArray());
			  }
			} else {
			  node.setProperty(prop, value);
			}
		  }
		}
	  }
	  tx.success();
	}
	System.out.println("initUsers successfully");
  }

  @Override
  public void createUser(User user) {
	try (Transaction tx = graphDBService.beginTx()) {
	  Node node = graphDBService.createNode(label);
	  Map<String, Object> map = JsonUtil.convertEntityObj2Map(user, false);
	  for (String prop : map.keySet()) {
		node.setProperty(prop, map.get(prop));
	  }
	  tx.success();
	}
  }

  @Override
  public void createRelationships() {
	// createRelationships by friendId
	try (Transaction tx = graphDBService.beginTx()) {
	  ResourceIterator<Node> nodes = graphDBService.findNodes(label);
	  while (nodes.hasNext()) {
		Node node = nodes.next();
		Object friendId = node.getProperty("friendId");
		System.out.println(node.getProperty("id") + "  -  " + friendId);
		if (friendId != null) {
		  Node friendNode = graphDBService.findNode(label, "id", friendId);
		  Relationship relationship = node.createRelationshipTo(friendNode, UserRelationShipType.KNOWS);
		  relationship.setProperty("info", "i know you");
		}
	  }
	  tx.success();
	}
  }

  @Override
  public List<User> findRelationUsers(String id) {
	List<User> users = new ArrayList<User>();
	try (Transaction tx = graphDBService.beginTx()) {
	  Node userNode = graphDBService.findNode(label, "id", id);
	  TraversalDescription td =
		  graphDBService.traversalDescription().breadthFirst().relationships(UserRelationShipType.KNOWS).evaluator(Evaluators.atDepth(1));
	  Traverser friendsTraverser = td.traverse(userNode);

	  printTraverserPath(userNode, friendsTraverser);

	  ResourceIterator<Node> nodes = td.traverse(userNode).nodes().iterator();
	  while (nodes.hasNext()) {
		Node node = nodes.next();
		Map<String, Object> properties = node.getAllProperties();
		User user = JsonUtil.ConvertMap2POJO(properties, new TypeReference<User>() {});
		users.add(user);
	  }
	  tx.success();
	}
	return users;
  }

  /**
   * @param userNode
   * @param friendsTraverser
   */
  private void printTraverserPath(Node userNode, Traverser friendsTraverser) {
	int numberOfFriends = 0;
	String output = userNode.getProperty("name") + "'s friends:\n";
	for (Path friendPath : friendsTraverser) {
	  output += "At depth " + friendPath.length() + " => " + friendPath.endNode().getProperty("name") + "\n";
	  numberOfFriends++;
	}
	output += "Number of friends found: " + numberOfFriends + "\n";
	System.out.println(output);
  }

  @Override
  public void clearDatabase() {
	try (Transaction tx = graphDBService.beginTx()) {
	  ResourceIterator<Node> nodes = graphDBService.findNodes(label);
	  while (nodes.hasNext()) {
		Node node = nodes.next();
		Iterable<Relationship> relationships = node.getRelationships();
		for (Relationship relationship : relationships) {
		  relationship.delete();
		}
		node.delete();
	  }
	  tx.success();
	}
  }

  public GraphDatabaseService getGraphDb() {
	return graphDBService;
  }

  public void setGraphDb(GraphDatabaseService graphDb) {
	this.graphDBService = graphDb;
  }



}
