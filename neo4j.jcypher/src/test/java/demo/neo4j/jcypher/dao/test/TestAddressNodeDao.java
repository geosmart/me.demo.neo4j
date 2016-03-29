package demo.neo4j.jcypher.dao.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.neo4j.jcypher.config.Neo4jConfig;
import demo.neo4j.jcypher.dao.IAddressNodeDao;
import demo.neo4j.jcypher.domain.AddressNode;

/**
 * AddressNodeDao TestCase
 * 
 * @author geosmart
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao.cfg.xml"})
public class TestAddressNodeDao {
  @Autowired
  IAddressNodeDao addressNodeDao;

  @Autowired
  Neo4jConfig neo4jConfig;

  @Before
  public void setup() {
	System.out.println("......setup......");
  }


  @Test
  public void test_saveAddressNode() {
	AddressNode addressNode = new AddressNode("002", "code", "测试地址2");
	String elements[] = {"A", "B", "C", "D", "E"};
	Set<String> guids = new HashSet<String>(Arrays.asList(elements));
	addressNode.setPreAddressNodeGUIDs(guids);
	addressNodeDao.saveAddressNode(addressNode);
  }

  @Test
  public void test_getAddressNode() {
	// TODO Set can nlt convert to List Exception
	addressNodeDao.getAddressNode("002");
  }

  @After
  public void teardown() {
	System.out.println("......teardown.......");
  }

}
