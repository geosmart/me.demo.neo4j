package demo.neo4j.jcypher.dao;

import java.util.List;

import demo.neo4j.jcypher.domain.people.Person;

public interface IPopulationDao {

  void storeDomainObjects();

  void retrieveDomainInformation();

  List<Person> paginationQuery(String key, Object value);

  void clearDatabase();

}
