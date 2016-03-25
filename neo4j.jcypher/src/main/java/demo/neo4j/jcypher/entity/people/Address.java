/************************************************************************
 * Copyright (c) 2014 IoT-Solutions e.U.
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

package demo.neo4j.jcypher.entity.people;

public class Address implements PointOfContact {

	private String street;
	private int number;
	private Area area;
	
	public Address() {
		super();
	}
	public Address(String street, int number) {
		super();
		this.street = street;
		this.number = number;
	}
	public String getStreet() {
		return street;
	}
	public int getNumber() {
		return number;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	
}
