## Assumptions about the system.
Since no information has been provided following assumptions has been made:
1. If the triangle does not include the sun, the weather is normal.
2. On local, runtime h2database is used for persistence.

## Prerequisite
Java 8 or higher.
Maven 3.3 or higher.
## Installation
From the root of the project run :
mvn clean install
## Start
From the root of the project :
mvn spring-boot:run

## Endpoints to interact with:
http://localhost:8080/clima?day=
Insert after = the day for which you want to know the weather. Days should be Integers.

##Testing
The programs contains unit test as part of the code. SimulationTest due to its time consumption on each build, but
It can be run separately as part of integration.
The program contains Jacoco plugin and coverage, branches, etc can be viewed in target/site.