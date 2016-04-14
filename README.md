# Neo4j Demos

# 测试环境
JDK版本：`Java7`  
Neo4j版本：`2.3.3`  

# SpringData for Neo4j Demo
项目名称：`neo4j-demo-springdata`    
1. 在领域实体POJO内以标注形式进行OGM映射（Relation、Index）；  
2. DAO层可通过继承GraphRepository模版类，重用CRUD方法；  

# JCypher Demo
Spring集成测试项目名称：`neo4j-demo-jcypher`  
官方项目：`neo4j.jcypher.samples`   
1. 集成Remote、Emberded和InMemmory三种Neo4j数据库访问形式，在程序测试和Neo4j Browserz间切换很方便；    
2. 无需在POJO中手动标注实现OGM，会自动将对象嵌套关系转换为Graph Relationship，可更专注与业务逻辑；

# Native API Demo
项目名称：`neo4j-demo-nativeapi`   
Neo4j官方自带API，需手动进行事物管理，实现较为繁琐；   

# Native API Demo
领域模型存储为Graph对象时，需通过对Node设置Property实现，足够灵活，但POJO的基础CRUD等需自行实现；     

# Cypher API Demo
类似于JDBC，通过拼接Cypher语句在ExecutionEngine中执行，结果ExecutionResult获需自行封装转换为POJO；  

# Spring JDBC Demo
项目名称：`neo4j-demo-springjdbc`  
基于Neo4j-jdbc，JdbcTenplate使用更为轻便，可用于一些OGM框架不能处理的Cypher语句的场合  
[]TODO

# PS
- SpringData Neo4j门槛低，上手快，有大量OGM的标注，后期调优需要深入了解；
- JCypher设计理念感觉很好，但开发不是很活跃，内部封装实现不能满足实际需求，感觉还有很长一段路要走，观望中；
- 暂选择NativeAPI选型，轻量可控，CyoherQL实现复杂查询，NativeAPI封装常用操作。
