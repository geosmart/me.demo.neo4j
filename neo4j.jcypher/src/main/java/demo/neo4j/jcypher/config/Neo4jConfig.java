package demo.neo4j.jcypher.config;

import iot.jcypher.database.DBAccessFactory;
import iot.jcypher.database.DBProperties;
import iot.jcypher.database.DBType;
import iot.jcypher.database.IDBAccess;
import iot.jcypher.domain.DomainAccessFactory;
import iot.jcypher.domain.IDomainAccess;
import iot.jcypher.domain.IDomainAccessFactory;

import java.util.Properties;

public class Neo4jConfig {
  private static IDBAccess dbAccess;
  private String dbName;
  private String dbPath;

  private String userId = null;
  private String password = null;


  /**
   * init embedded database
   * 
   * @param dbPath
   */
  @SuppressWarnings("unused")
  private void initEmbedDB() {
	// properties for embedded access
	Properties props = new Properties();
	dbPath = dbPath == null ? System.getProperty("user.dir") + "/db/01" : System.getProperty("user.dir") + dbPath;
	System.out.println(dbPath);
	props.setProperty(DBProperties.DATABASE_DIR, dbPath);
	dbAccess = DBAccessFactory.createDBAccess(DBType.IN_MEMORY, props);
  }

  /**
   * @param dbPath
   */
  private void init() {
	// a domain needs a unique name within a graph database
	// dbName = "PEOPLE-DOMAIN";

	// properties for remote access and for embedded access
	// (not needed for in memory access)
	Properties props = new Properties();

	// properties for embedded access
	dbPath = dbPath == null ? System.getProperty("user.dir") + "/db/01" : dbPath;
	props.setProperty(DBProperties.DATABASE_DIR, dbPath);
	// no properties needed for in memory access

	// properties for remote access
	props.setProperty(DBProperties.SERVER_ROOT_URI, "http://localhost:7474");
	if (userId != null && password != null) {
	  dbAccess = DBAccessFactory.createDBAccess(DBType.REMOTE, props, userId, password);
	} else {
	  // dbAccess = DBAccessFactory.createDBAccess(DBType.REMOTE, props);
	  // dbAccess = DBAccessFactory.createDBAccess(DBType.EMBEDDED, props);
	  dbAccess = DBAccessFactory.createDBAccess(DBType.IN_MEMORY, props);
	}
  }


  /**
   * answer a new IDomainAccess to work with a certain domain within a graph database
   * 
   * @return a new IDomainAccess
   */
  public IDomainAccess createDomainAccess() {
	return DomainAccessFactory.createDomainAccess(dbAccess, dbName);
  }

  /**
   * answer a new IDomainAccess to work with a certain domain within a graph database. <br/>
   * The returned IDomainAccess instance is thread-safe
   * 
   * @return a new IDomainAccess
   */
  public IDomainAccess createThreadSafeDomainAccess() {
	return IDomainAccessFactory.INSTANCE_SYNCHRONIZED.createDomainAccess(dbAccess, dbName);
  }


  public String getUserId() {
	return userId;
  }

  public void setUserId(String userId) {
	this.userId = userId;
  }

  public String getPassword() {
	return password;
  }

  public void setPassword(String password) {
	this.password = password;
  }

  public String getDbName() {
	return dbName;
  }

  public void setDbName(String dbName) {
	this.dbName = dbName;
  }

  public String getDbPath() {
	return dbPath;
  }

  public void setDbPath(String dbPath) {
	this.dbPath = dbPath;
  }

  public IDBAccess getDbAccess() {
	return dbAccess;
  }
}
