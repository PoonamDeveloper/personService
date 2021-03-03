# personService

Application provides REST API interface provided
for CRUD Operations on Person Object. Done by Poonam  Agarwal.

It uses:
1. Spring Boot libraries.
2. REST and JPA to provide REST Repository to access CRUD Operations.
3. Junit for some unit tests
4. Maven to build the project.
5. Java 8.
6. In memory H2 database.
7. Used Eclipse for development and Spring Boot Tools for project generation.

Pre-Requisite for building project.
1. Assumes Java 1.8 and Maven 3.6.* will be present on the system. May be replaced with docker system later on.
2. JAVA_HOME and M2_HOME environment variables will be set.

How to build and package.
1. Download or get the peronService project from GitHub. Go to the folder personService.
2. Use the command "mvn clean package". It will build the jar file and run the tests by default.

How to run:
1. Go to the folder personService. Use the command "mvn spring-boot:run"

The applications runs on the port 8081 by default and the REST API is accessible at:
http://localhost:8081/people