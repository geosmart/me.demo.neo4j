package demo.neo4j.jcypher.dao.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.neo4j.jcypher.config.Neo4jConfig;
import demo.neo4j.jcypher.dao.IPopulationDao;
import demo.neo4j.jcypher.domain.people.Person;

/**
 * PopulationDao TestCase
 * 
 * @author geosmart
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao.cfg.xml"})
public class TestPopulationDao {
  private static final int USER_COUNT = 4;

  @Autowired
  IPopulationDao populationDao;

  @Autowired
  Neo4jConfig neo4jConfig;

  @Before
  public void setup() {
	System.out.println("......setup......");
  }

  @Test
  public void test_storeDomainObjects() {
	populationDao.storeDomainObjects();
  }


  @Test
  public void test_clearDatabase() {
	populationDao.clearDatabase();
  }

  @Test
  public void test_paginationQuery() {
	List<Person> result = populationDao.paginationQuery("firstName", "Angelina");
	for (Person person : result) {
	  System.out.println(person.getLastName() + " " + person.getFirstName());
	}
  }

  @Test
  public void test_retrieveDomainInformation() {
	populationDao.retrieveDomainInformation();
  }

  @After
  public void teardown() {
	System.out.println("......teardown.......");
  }

}
