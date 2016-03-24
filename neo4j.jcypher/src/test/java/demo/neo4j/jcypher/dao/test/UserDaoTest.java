package demo.neo4j.jcypher.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.neo4j.jcypher.dao.IUserDao;

/**
 * Neo4j dao
 * 
 * @author geosmart
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:neo4j.embed.cfg.xml"})
public class UserDaoTest {

  @Autowired
  IUserDao userDao;

  @Test
  public void test_createMovieDatabaseByGraphModel() {
	userDao.createMovieDatabaseByGraphModel();
  }
}
