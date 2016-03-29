package me.demo.neo4j.dao.test;

import java.util.List;

import me.demo.neo4j.dao.AddressNodeDao;
import me.demo.neo4j.entity.AddressNode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * UserDao TestCase
 * 
 * @author geosmart
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:neo4j.rest.cfg.xml"})
public class TestAddressNodeDao {

  @Autowired
  AddressNodeDao addressNodeDao;

  @Before
  public void setup() {
	System.out.println("......setup......");
  }


  @Test
  public void test_findAddressNode() {
	List<AddressNode> result = addressNodeDao.findAddressNodes();
	System.out.println(result.size());
  }


  @After
  public void teardown() {
	System.out.println("......teardown.......");
  }

}
