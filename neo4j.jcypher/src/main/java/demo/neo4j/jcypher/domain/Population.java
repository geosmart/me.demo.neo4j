/************************************************************************
 * Copyright (c) 2014-2015 IoT-Solutions e.U.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ************************************************************************/

package demo.neo4j.jcypher.domain;

import java.util.ArrayList;
import java.util.List;

import demo.neo4j.jcypher.domain.people.Address;
import demo.neo4j.jcypher.domain.people.Area;
import demo.neo4j.jcypher.domain.people.AreaType;
import demo.neo4j.jcypher.domain.people.Company;
import demo.neo4j.jcypher.domain.people.EContact;
import demo.neo4j.jcypher.domain.people.Gender;
import demo.neo4j.jcypher.domain.people.Person;
import demo.neo4j.jcypher.domain.people.EContact.EContactType;

public class Population {

	private Area earth;
	private Area northAmerica;
	private Area usa;
	private Area california;
	private Area sanFrancisco;
	private Area europe;
	private Area germany;
	private Area munich;
	private Area newYork;
	private Area newYorkCity;
	private Area austria;
	private Area vienna;
	private Area vienna_01;
	private Area vienna_17;
	
	public Population() {
		super();
	}

	/**
	 * create the population,
	 * @return a list of root objects of the created object graph.
	 */
	public List<Object> createPopulation() {
		List<Object> domainObjects = new ArrayList<Object>();
		
		createPlaces();

		createSmithFamily(domainObjects);
		createBerghammers(domainObjects);
		createMore(domainObjects);

		createCompanies(domainObjects);
		
		return domainObjects;
	}
	
	public Person createBillCollins() {
		if (this.earth == null)
			createPlaces();
		Address collins_address = new Address("Union Square", 2);
		collins_address.setArea(sanFrancisco);
		
		Person bill_collins = new Person("Bill", "Collins", Gender.MALE, "gray");
		bill_collins.getPointsOfContact().add(collins_address);
		
		return bill_collins;
	}
	
	private void createPlaces() {
		earth = new Area(null, "Earth", AreaType.PLANET);

		northAmerica = new Area(null, "North America", AreaType.CONTINENT);
		northAmerica.setPartOf(earth);

		usa = new Area("1", "USA", AreaType.COUNTRY);
		usa.setPartOf(northAmerica);

		california = new Area(null, "California", AreaType.STATE);
		california.setPartOf(usa);

		sanFrancisco = new Area(null, "San Francisco", AreaType.CITY);
		sanFrancisco.setPartOf(california);

		europe = new Area(null, "Europe", AreaType.CONTINENT);
		europe.setPartOf(earth);
		germany = new Area("2", "Germany", AreaType.COUNTRY);
		germany.setPartOf(europe);
		munich = new Area(null, "Munich", AreaType.CITY);
		munich.setPartOf(germany);

		newYork = new Area(null, "New York", AreaType.STATE);
		newYork.setPartOf(usa);
		newYorkCity = new Area(null, "New York City", AreaType.CITY);
		newYorkCity.setPartOf(newYork);

		austria = new Area(null, "Austria", AreaType.COUNTRY);
		austria.setPartOf(europe);
		vienna = new Area("1", "Vienna", AreaType.CITY);
		vienna.setPartOf(austria);
		vienna_17 = new Area("1170", "Hernals", AreaType.URBAN_DISTRICT);
		vienna_17.setPartOf(vienna);
		vienna_01 = new Area("1010", "Innere Stadt", AreaType.URBAN_DISTRICT);
		vienna_01.setPartOf(vienna);
	}
	
	private void createSmithFamily(List<Object> domainObjects) {
		Address smith_address = new Address("Market Street", 20);
		smith_address.setArea(sanFrancisco);
		Address smith_address_2 = new Address("Schweden Platz", 32);
		smith_address_2.setArea(vienna_01);
		EContact jsmith_eContact = new EContact(EContactType.EMAIL, "j.smith@email.smith");
		
		Person john_smith = new Person("John", "Smith", Gender.MALE, "brown");
		john_smith.getPointsOfContact().add(smith_address);
		john_smith.getPointsOfContact().add(smith_address_2);
		john_smith.getPointsOfContact().add(jsmith_eContact);
		Person caroline_smith = new Person("Caroline", "Smith", Gender.FEMALE, "green");
		caroline_smith.getPointsOfContact().add(smith_address);
		Person angie_smith = new Person("Angelina", "Smith", Gender.FEMALE, "blue");
		angie_smith.getPointsOfContact().add(smith_address);
		angie_smith.setMother(caroline_smith);
		angie_smith.setFather(john_smith);
		Person jery_smith = new Person("Jeremy", "Smith", Gender.MALE, "brown");
		jery_smith.getPointsOfContact().add(smith_address);
		jery_smith.setMother(caroline_smith);
		jery_smith.setFather(john_smith);
		
		domainObjects.add(john_smith);
		domainObjects.add(caroline_smith);
		domainObjects.add(angie_smith);
		domainObjects.add(jery_smith);
	}

	private void createBerghammers(List<Object> domainObjects) {
		Address berghammer_address = new Address("Hochstrasse", 4);
		berghammer_address.setArea(munich);
		
		Person hans_berghammer = new Person("Hans", "Berghammer", Gender.MALE, "blue");
		hans_berghammer.getPointsOfContact().add(berghammer_address);
		Person gerda_berghammer = new Person("Gerda", "Berghammer", Gender.FEMALE, "green");
		gerda_berghammer.getPointsOfContact().add(berghammer_address);
		Person christa_berhammer = new Person("Christa", "Berghammer", Gender.FEMALE, "brown");
		christa_berhammer.getPointsOfContact().add(berghammer_address);
		christa_berhammer.setMother(gerda_berghammer);
		christa_berhammer.setFather(hans_berghammer);
		
		Person hannah_berhammer = new Person("Hannah", "Berghammer", Gender.FEMALE, "blue");
		hannah_berhammer.getPointsOfContact().add(gerda_berghammer.getPointsOfContact().get(0));
		hannah_berhammer.setMother(gerda_berghammer);
		hannah_berhammer.setFather(hans_berghammer);
		
		Person max_berhammer = new Person("Max", "Berghammer", Gender.MALE, "green");
		max_berhammer.getPointsOfContact().add(gerda_berghammer.getPointsOfContact().get(0));
		max_berhammer.setMother(gerda_berghammer);
		max_berhammer.setFather(hans_berghammer);
		
		Person fritz_berghammer = new Person("Fritz", "Berghammer", Gender.MALE, "brown");
		fritz_berghammer.getPointsOfContact().add(gerda_berghammer.getPointsOfContact().get(0));
		fritz_berghammer.setMother(gerda_berghammer);
		
		domainObjects.add(hans_berghammer);
		domainObjects.add(gerda_berghammer);
		domainObjects.add(christa_berhammer);
		domainObjects.add(hannah_berhammer);
		domainObjects.add(max_berhammer);
		domainObjects.add(fritz_berghammer);
	}
	
	private void createMore(List<Object> domainObjects) {
		Address watson_address = new Address("Broadway", 53);
		watson_address.setArea(newYorkCity);
		Person jim_watson = new Person("Jim", "Watson", Gender.MALE, "brown");
		jim_watson.getPointsOfContact().add(watson_address);
		
		Address clark_address = new Address("Pearl Street", 124);
		clark_address.setArea(newYorkCity);
		Person angie_clark = new Person("Angelina", "Clark", Gender.FEMALE, "blue");
		angie_clark.getPointsOfContact().add(clark_address);
		
		Address maier_address = new Address("Lackner Gasse", 12);
		maier_address.setArea(vienna_17);
		Person herbert_maier = new Person("Herbert", "Maier", Gender.MALE, "green");
		herbert_maier.getPointsOfContact().add(maier_address);
		
		Person fritz_berghammer = (Person) domainObjects.get(domainObjects.size() - 1);
		fritz_berghammer.setFather(herbert_maier);
		
		Person sarah_maier = new Person("Sarah", "Maier", Gender.FEMALE, "blue");
		sarah_maier.getPointsOfContact().add(maier_address);
		
		Address clark_kent_address = new Address("Kearny Street", 34);
		clark_kent_address.setArea(sanFrancisco);
		
		Person clark_kent = new Person("Clark", "Kent", Gender.MALE, "black");
		clark_kent.getPointsOfContact().add(clark_kent_address);
		
		domainObjects.add(jim_watson);
		domainObjects.add(angie_clark);
		domainObjects.add(herbert_maier);
		domainObjects.add(sarah_maier);
		domainObjects.add(clark_kent);
	}
	
	private void createCompanies(List<Object> domainObjects) {
		Address globCom_address = new Address("Kearny Street", 29);
		globCom_address.setArea(sanFrancisco);
		
		Company globCom = new Company();
		globCom.setName("Global Company");
		globCom.getPointsOfContact().add(globCom_address);
		
		Address mTecCom_address = new Address("Schiller Strasse", 15);
		mTecCom_address.setArea(munich);
		
		Company mTecCom = new Company();
		mTecCom.setName("MunichTec Limited");
		mTecCom.getPointsOfContact().add(mTecCom_address);
		
		domainObjects.add(globCom);
		domainObjects.add(mTecCom);
	}
}
