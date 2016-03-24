package demo.neo4j.jcypher.dao.impl;

import iot.jcypher.database.DBAccessFactory;
import iot.jcypher.database.DBProperties;
import iot.jcypher.database.DBType;
import iot.jcypher.database.IDBAccess;
import iot.jcypher.graph.GrNode;
import iot.jcypher.graph.GrRelation;
import iot.jcypher.graph.Graph;
import iot.jcypher.query.result.JcError;

import java.util.List;
import java.util.Properties;

import demo.neo4j.jcypher.config.Neo4jConfig;
import demo.neo4j.jcypher.dao.IUserDao;
import demo.neo4j.jcypher.entity.User;

/**
 * Neo4j dao
 * 
 * @author geosmart
 * 
 */
public class UserDaoImpl implements IUserDao {
  Neo4jConfig neo4jConfig;

  /**
   * initialize connection to a Neo4j database
   * 
   * @return
   */
  private static IDBAccess initDBConnection() {
	// properties for embedded access
	Properties props = new Properties();
	String dbPath = System.getProperty("user.dir") + "//bin//db//NEO4J_DBS//01";
	System.out.println(dbPath);
	props.setProperty(DBProperties.DATABASE_DIR, dbPath);
	return DBAccessFactory.createDBAccess(DBType.IN_MEMORY, props);
  }

  /**
   * Create the movie database
   */
  @Override
  public void createMovieDatabaseByGraphModel() {
	Graph graph = Graph.create(initDBConnection());

	GrNode matrix1 = graph.createNode();
	matrix1.addLabel("Movie");
	matrix1.addProperty("title", "The Matrix");
	matrix1.addProperty("year", "1999-03-31");

	GrNode matrix2 = graph.createNode();
	matrix2.addLabel("Movie");
	matrix2.addProperty("title", "The Matrix Reloaded");
	matrix2.addProperty("year", "2003-05-07");

	GrNode matrix3 = graph.createNode();
	matrix3.addLabel("Movie");
	matrix3.addProperty("title", "The Matrix Revolutions");
	matrix3.addProperty("year", "2003-10-27");

	GrNode keanu = graph.createNode();
	keanu.addLabel("Actor");
	keanu.addProperty("name", "Keanu Reeves");
	keanu.addProperty("rating", 8.5);
	keanu.addProperty("numbers", new int[] {1, 2, 3});

	GrNode laurence = graph.createNode();
	laurence.addLabel("Actor");
	laurence.addProperty("name", "Laurence Fishburne");
	laurence.addProperty("rating", 7);

	GrNode carrieanne = graph.createNode();
	carrieanne.addLabel("Actor");
	carrieanne.addProperty("name", "Carrie-Anne Moss");
	carrieanne.addProperty("rating", 8.3);

	GrRelation rel = graph.createRelation("ACTS_IN", keanu, matrix1);
	rel.addProperty("role", "Neo");
	rel = graph.createRelation("ACTS_IN", keanu, matrix2);
	rel.addProperty("role", "Neo");
	rel = graph.createRelation("ACTS_IN", keanu, matrix3);
	rel.addProperty("role", "Neo");

	rel = graph.createRelation("ACTS_IN", laurence, matrix1);
	rel.addProperty("role", "Morpheus");
	rel = graph.createRelation("ACTS_IN", laurence, matrix2);
	rel.addProperty("role", "Morpheus");
	rel = graph.createRelation("ACTS_IN", laurence, matrix3);
	rel.addProperty("role", "Morpheus");

	rel = graph.createRelation("ACTS_IN", carrieanne, matrix1);
	rel.addProperty("role", "Trinity");
	rel = graph.createRelation("ACTS_IN", carrieanne, matrix2);
	rel.addProperty("role", "Trinity");
	rel = graph.createRelation("ACTS_IN", carrieanne, matrix3);
	rel.addProperty("role", "Trinity");

	List<JcError> errors = graph.store();
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

  @Override
  public User findByLogin(String login) {
	// TODO Auto-generated method stub
	return null;
  }

  @Override
  public List<User> findFriendsOfRoot() {
	// TODO Auto-generated method stub
	return null;
  }


}
