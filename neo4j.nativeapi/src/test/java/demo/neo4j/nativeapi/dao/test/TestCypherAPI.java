package demo.neo4j.nativeapi.dao.test;

import java.io.File;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.logging.NullLogProvider;
import org.neo4j.tooling.GlobalGraphOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * UserDao TestCase
 * 
 * @author geosmart
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao.cfg.xml"})
public class TestCypherAPI {
  private static final int USER_COUNT = 4;

  String graphDbPath = "F://Dev//neo4j//data//graph.db";
  GraphDatabaseFactory dbFactory;
  GraphDatabaseService graphDb;

  @Before
  public void setup() {
	System.out.println("......setup......");
	dbFactory = new GraphDatabaseFactory();
	graphDb = dbFactory.newEmbeddedDatabase(new File(graphDbPath));
  }

  @Test
  public void initUsers() {
	try (Transaction tx = graphDb.beginTx()) {
	  ExecutionEngine engine = new ExecutionEngine(graphDb, NullLogProvider.getInstance());

	  StringBuilder sb = new StringBuilder();
	  long start = System.currentTimeMillis();
	  String rootCypher = "CREATE (root:User { type: 'admin', name: 'root'})";
	  System.out.println(rootCypher);
	  engine.execute(rootCypher);

	  for (int i = 0; i < USER_COUNT; i++) {
		String userCypher = String.format("CREATE (user%d:User {type: 'guest',  name: '%s'})", i, "guest" + i, "user" + i);
		System.out.println(userCypher);
		engine.execute(userCypher, new HashMap<String, Object>());
	  }
	  // create root user relation to all guest users
	  String relationCypher = "MATCH (root{type:'admin' }),(guest{type:'guest'}) CREATE  (root)-[r:knows]->(guest) RETURN r";
	  System.out.println(relationCypher);
	  engine.execute(relationCypher, new HashMap<String, Object>());
	  long total = System.currentTimeMillis() - start;
	  System.out.println(total);
	  System.out.println(sb.toString());
	  tx.success();
	}
	System.out.println("initUsers successfully");
  }

  @Test
  public void test_findUserFriends() {
	try (Transaction tx = graphDb.beginTx()) {
	  ExecutionEngine engine = new ExecutionEngine(graphDb, NullLogProvider.getInstance());

	  long start = System.currentTimeMillis();
	  ExecutionResult result = engine.execute("MATCH (root{ type:'admin' })-->(user) RETURN user");

	  System.out.println(result.dumpToString());
	  System.out.println(result.queryStatistics().toString());

	  long total = System.currentTimeMillis() - start;
	  System.out.println("spendMillis:" + total);
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
