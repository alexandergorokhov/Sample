# About the system.
1. The system simulates the weather calculation of the 3 planets around the sun. The objective is to predict the weather at certain point. The known information is radius of each planet from the sun and angular speed.
2. The conditions are : 
  planets are aliened between them and the sun than it will be drought.
  Planets are not aliened between them and form a triangle with sun included in the triangle than it will rain, with it highest rate when the perimeter of the triangle is on its maximum.
  Planets are not aliened between them and form a triangle with sun not included in the triangle than the weather conditions are optimum.
  The system should be able to answer:
  1. How many drought period there will be?
  2. How many rain periods and its maximum day?
  3. How many optimum weather conditions days there will be?
# Assumptions about the system.
Since no information has been provided following assumptions has been made:
1. If the triangle does not include the sun, the weather is normal.
2. On local, runtime h2database is used for persistence.

# Prerequisite
Java 8 or higher.
Maven 3.3 or higher.
# Installation
From the root of the project run :
mvn clean install.
# Testing
The programs contains unit test as part of the code. SimulationTest due to its time consumption on each build, but
It can be run separately as part of integration.
The program contains Jacoco plugin and coverage, branches, etc can be viewed in target/site.
# Start
From the root of the project :
mvn spring-boot:run
# Endpoints to interact with:
## Local
http://localhost:8080/clima?day=
Insert after "=" the day for which you want to know the weather. Days should be Integers.
## On cloud
This endpoint avalibility may change due to Cloud Foundry policies.
https://merli-app-fantastic-panther.cfapps.io/clima?day= <br />
Insert after "=" the day for which you want to know the weather.  
Days should be Integers.
# Cloud:
Cloud deployment is done for cloud foundry
To compile the project for cloud deployment from the root of the project use: mvn clean install spring-boot:repackage -P cloudfoundry
To deploy access cloudfoundry directory and login to your cf space: cf push.
Varibles of deployment can be modified in manifest.yml

