package demo.neo4j.jcypher.dao.impl;

import iot.jcypher.database.IDBAccess;
import iot.jcypher.domain.DomainInformation;
import iot.jcypher.domain.DomainInformation.DomainObjectType;
import iot.jcypher.domain.IDomainAccess;
import iot.jcypher.domainquery.DomainQuery;
import iot.jcypher.domainquery.DomainQueryResult;
import iot.jcypher.domainquery.api.DomainObjectMatch;
import iot.jcypher.query.result.JcError;
import iot.jcypher.query.result.JcResultException;

import java.util.List;

import demo.neo4j.jcypher.config.Neo4jConfig;
import demo.neo4j.jcypher.dao.IPopulationDao;
import demo.neo4j.jcypher.domain.Population;
import demo.neo4j.jcypher.domain.people.Person;
import demo.neo4j.jcypher.util.Util;

/**
 * User Dao Implement
 * 
 * @author geosmart
 * 
 */
public class PopulationDaoImpl implements IPopulationDao {
  Neo4jConfig neo4jConfig;

  /**
   * demonstrates how to store and retrieve domain objects.
   */
  @Override
  public void storeDomainObjects() {
	List<JcError> errors;

	// A utility class which creates a sample population.
	Population domainPopulator = new Population();

	// Create the population,
	// return a list of root objects of the created object graph.
	List<Object> createdDomainObjects = domainPopulator.createPopulation();

	// Initially clear the database.
	// Note: On how to create an IDBAccess (access to a graph database)
	// and an IDomainAccess (access to a domain within a graph database)
	// including a thread-safe IDomainAccess,
	// hava a look at the 'Config class' in the 'graph_access' subpackage.
	IDBAccess dbAccess = neo4jConfig.getDbAccess();
	errors = dbAccess.clearDatabase();
	if (errors.size() > 0) {
	  Util.printErrors(errors);
	  throw new JcResultException(errors);
	}

	// Store the graph of domain objects in the graph database.
	// Starting with the root objects, the entire object graph is stored in the graph database.
	IDomainAccess domainAccess = neo4jConfig.createDomainAccess();

	errors = domainAccess.store(createdDomainObjects);
	if (errors.size() > 0) {
	  Util.printErrors(errors);
	  throw new JcResultException(errors);
	}
  }

  /**
   * demonstrates how to store and retrieve domain objects.
   */
  @Override
  public void clearDatabase() {
	IDBAccess dbAccess = neo4jConfig.getDbAccess();
	dbAccess.clearDatabase();

  }

  /**
   * Sorting Result Sets + Pagination
   */
  @Override
  public List<Person> paginationQuery(String key, Object value) {
	IDomainAccess domainAccess = neo4jConfig.createDomainAccess();
	// create a DomainQuery object
	DomainQuery q = domainAccess.createQuery();
	// create DomainObjectMatches
	DomainObjectMatch<Person> personsMatch = q.createMatch(Person.class);
	// Specify Pagination (offset + count)
	personsMatch.setPage(1, 5);
	// where filter
	q.WHERE(personsMatch.atttribute(key)).EQUALS(value);
	// First: All persons are sorted by their last name (ascending)
	q.ORDER(personsMatch).BY("lastName");
	// Second: Having the same last name, persons are sorted by their first name (descending)
	q.ORDER(personsMatch).BY("firstName").DESCENDING();

	DomainQueryResult result = q.execute();
	List<Person> sortedPersons = result.resultOf(personsMatch);
	return sortedPersons;
  }

  /**
   * demonstrates how to retrieve domain information.
   */
  @Override
  public void retrieveDomainInformation() {

	// Answer the names of available domains.
	List<String> available = DomainInformation.availableDomains(neo4jConfig.getDbAccess());
	System.out.println(String.format("jType %s", available));

	// Create a DomainInformation object for a certain domain.
	DomainInformation di = DomainInformation.forDomain(neo4jConfig.getDbAccess(), neo4jConfig.getDbName());

	// Answer a list of DomainObjectTypes stored in the domain graph
	List<DomainObjectType> types = di.getDomainObjectTypes();

	DomainObjectType type = types.get(0);

	// You can ask a DomainObjectType for its type name
	// i.e. the fully qualified name of the java type
	String typeName = type.getTypeName();
	System.out.println(String.format("typeName %s", typeName));

	// You can ask a DomainObjectType for the
	// label of nodes to which domain objects
	// of that type are mapped
	String label = type.getNodeLabel();
	System.out.println(String.format("label %s", label));

	// You can retrieve the java type (Class)
	// from a DomainObjectType.
	// Note: this may raise a ClassNotFoundException
	Class<?> jType = type.getType();
	System.out.println(String.format("jType %s", jType.getName()));
  }

  public Neo4jConfig getNeo4jConfig() {
	return neo4jConfig;
  }

  public void setNeo4jConfig(Neo4jConfig neo4jConfig) {
	this.neo4jConfig = neo4jConfig;
  }


}
