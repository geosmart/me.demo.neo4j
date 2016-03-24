JCypher Samples
===============

Provides samples for [**JCypher**](http://wolfgang-schuetzelhofer.github.io/jcypher/).
## Documentation and Getting Started
For documentation and on how to get started please have a look at the wiki:
https://github.com/Wolfgang-Schuetzelhofer/jcypher_samples/wiki

## License & Copyright

Copyright (c) 2014-2016 IoT-Solutions e.U.

License:
								Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/
 
  
The JCypher Samples project was introduced together with release 2.0.0 of JCypher. 
The core sample is the 'PEOPLE-DOMAIN' sample. You can find it in package iot.jcypher.samples.domain.people. It was introduced to demonstrate how complex business domains are mapped to graph databases (Neo4J) by using JCypher. The sample is intended to grow step by step as new features are implemented in JCypher. The class to start with is iot.jcypher.samples.domain.people.PeopleDomain. 
Find a class diagramm of the PEOPLE-DOMAIN here. 
Another class iot.jcypher.samples.domain.people.GenericPeopleDomain contains sample code to work with the Generic Domain Model which was introduced with JCypher release 3.0.0. 
Class iot.jcypher.samples.domain.people.Population is used to populate the sample model. You have to edit iot.jcypher.samples.domain.people.graph_access.Config to configure your database access. 
Two more sample classes are ShakespeareGraph and MovieDatabase. They provide some basic insight into using JCypher-Query-DSL. They are found in package iot.jcypher.samples.basic.

Getting Started

You can either clone the repository from the master(trunk) or from a release tag. Or you can download a .zip file containing the sources from the master(trunk) or from the releases page. The samples project is being build with maven and contains the dependency to JCypher in its pom.xml file.

Note: JCypher is released to maven central, so you don't have to manually download JCypher. 
Just add the dependency:

<dependency>
  <groupId>net.iot-solutions.graphdb</groupId>
   <artifactId>jcypher</artifactId>
   <version>3.2.0</version>
</dependency>