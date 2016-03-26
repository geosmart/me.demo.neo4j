package demo.neo4j.jcypher.config;

import iot.jcypher.database.DBAccessFactory;
import iot.jcypher.database.DBProperties;
import iot.jcypher.database.DBType;
import iot.jcypher.database.IDBAccess;
import iot.jcypher.domain.DomainAccessFactory;
import iot.jcypher.domain.IDomainAccess;
import iot.jcypher.domain.IDomainAccess.DomainLabelUse;
import iot.jcypher.domain.IDomainAccessFactory;

import java.util.Properties;

public class Neo4jConfig {
  private static IDBAccess dbAccess;
  /**
   * database type
   */
  private DBType dbType;


  private String dbName;
  private String dbPath;

  private String userId = null;
  private String password = null;

  public void initDbAccess() {
	switch (dbType) {
	  case IN_MEMORY:
		initMemoryDB();
		break;
	  case EMBEDDED:
		initEmbedDB();
		break;
	  case REMOTE:
		initRemoteDB();
		break;
	  default:
		break;
	}
  }

  /**
   * init memorry database
   * 
   * @param dbPath
   */
  protected void initMemoryDB() {
	// properties for embedded access
	Properties props = new Properties();
	dbAccess = DBAccessFactory.createDBAccess(DBType.IN_MEMORY, props);
  }

  /**
   * init embedded database
   * 
   * @param dbPath
   */
  protected void initEmbedDB() {
	// properties for embedded access
	Properties props = new Properties();
	dbPath = dbPath == null ? System.getProperty("user.dir") + "/bin/db/01" : dbPath;
	System.out.println(dbPath);
	props.setProperty(DBProperties.DATABASE_DIR, dbPath);
	dbAccess = DBAccessFactory.createDBAccess(DBType.EMBEDDED, props);
  }

  /**
   * init remote database
   * 
   * @param dbPath
   */
  protected void initRemoteDB() {
	// properties for embedded access
	Properties props = new Properties();
	/** connect to remote database via REST (SERVER_ROOT_URI property is needed) */
	props.setProperty(DBProperties.SERVER_ROOT_URI, "http://127.0.0.1:7474");
	if (userId != null && password != null) {
	  dbAccess = DBAccessFactory.createDBAccess(DBType.REMOTE, props, userId, password);
	} else {
	  dbAccess = DBAccessFactory.createDBAccess(DBType.REMOTE, props);
	}
  }


  /**
   * answer a new IDomainAccess to work with a certain domain within a graph database
   * 
   * @return a new IDomainAccess
   */
  public IDomainAccess createDomainAccess() {
	DomainLabelUse domainLabel = DomainLabelUse.AUTO;
	return DomainAccessFactory.createDomainAccess(dbAccess, dbName, domainLabel);
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

  public DBType getDbType() {
	return dbType;
  }

  public void setDbType(DBType dbType) {
	this.dbType = dbType;
  }
}
