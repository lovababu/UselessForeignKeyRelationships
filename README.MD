## Demonstrates the useless of Foreign Key relationships on DB level, while using ORM technology.
 
## TechStack.
  1. Spring Core/ORM.
  2. Hibernate Framework.
  3. Oracle Datastore
  4. Lombok api.
  5. Gradle build and dependency management.
  
## Problem Statement.
  1. As a basic oracle user you may know that Foreign key relationships between the tables very expensive. 
     This might be reason people are looking for NoSQL databases now a days.
  2. So can't we avoid these foreign key relationships on DB level, yes you can.
  3. How to load the child object as soon as paren get loaded? 
     ORM framework like Hibernate can address this problem by specifying relations on Entity clas level.
  
## Pre-requisites.
  1. Java 7 or above.
  2. Gradle 2.3 or above.
    
## Configurations.  
  1. Run the below sql script in your database, prior to run the tests.  
     ``create table account(account_id NUMBER(3), display_name VARCHAR2(15));``  
       
     ``alter table account ADD CONSTRAINT Account_id_pk PRIMARY KEY (account_id);``
       
     ``create table account_user(user_id NUMBER(3), user_name VARCHAR2(15), account_id NUMBER(3));``  
       
     ``alter table account_user ADD CONSTRAINT user_id_pk PRIMARY KEY (user_id);``  
  2. Open db-config.properties and change the database configuration to match with your environment.
   
## How to import?
  Eclipse IDE.
     1. Clone/download as zip.
     2. run ``> gradle eclipse ``  This will create eclipse ide related files, now you can import as eclipse project.
  
## How to Run?
  Added Test class called AccountTest.java, Run as Junit test.