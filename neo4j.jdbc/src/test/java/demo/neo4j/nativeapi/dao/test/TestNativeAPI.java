package demo.neo4j.nativeapi.dao.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.tooling.GlobalGraphOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.neo4j.nativeapi.domain.UserRelationShipType;

/**
 * UserDao TestCase
 * 
 * @author geosmart
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao.cfg.xml"})
public class TestNativeAPI {
  private static final int USER_COUNT = 4;

  String graphDbPath = "F://Dev//neo4j//data//graph.db";
  GraphDatabaseFactory dbFactory;
  GraphDatabaseService graphDb;
  Label userLabel;

  @Before
  public void setup() {
	System.out.println("......setup......");
	dbFactory = new GraphDatabaseFactory();
	graphDb = dbFactory.newEmbeddedDatabase(new File(graphDbPath));
	userLabel = DynamicLabel.label("User");
  }

  @Test
  public void initUsers() {
	try (Transaction tx = graphDb.beginTx()) {
	  Node rootNode = graphDb.createNode(userLabel);
	  rootNode.setProperty("id", "rootNode");
	  rootNode.setProperty("name", "Superuser");

	  List<Node> userNodes = new ArrayList<Node>();
	  for (int i = 0; i < USER_COUNT; i++) {
		Node userNode = graphDb.createNode(userLabel);
		userNode.setProperty("name", "guest" + i);
		userNode.setProperty("id", String.format("userNode %s", i));
		userNodes.add(userNode);
	  }
	  for (Node userNode : userNodes) {
		Relationship relationship = rootNode.createRelationshipTo(userNode, UserRelationShipType.knows);
		relationship.setProperty("info", "i know you");
		relationship.setProperty("id", RandomUtils.nextInt(0, 5));
	  }
	  tx.success();
	}
	System.out.println("initUsers successfully");
  }

  @Test
  public void test_findUserFriends() {
	try (Transaction tx = graphDb.beginTx()) {
	  Node rootNode = graphDb.findNode(userLabel, "id", "rootNode");
	  Iterable<Relationship> result = rootNode.getRelationships(UserRelationShipType.knows);
	  for (Relationship relationship : result) {
		Node user = relationship.getEndNode();
		System.out.println(user.getProperty("id"));
	  }
	  tx.success();
	}
  }


  @Test
  public void clearDataBase() {
	try (Transaction tx = graphDb.beginTx()) {
	  ResourceIterable<Node> nodes = GlobalGraphOperations.at(graphDb).getAllNodes();
	  for (Node node : nodes) {
		Iterable<Relationship> relationships = node.getRelationships();
		for (Relationship relationship : relationships) {
		  relationship.delete();
		}
		node.delete();
	  }
	  tx.success();
	}
  }

  @After
  public void teardown() {
	System.out.println("......teardown.......");
  }

}
