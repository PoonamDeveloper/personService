personService Application provides REST API interface for 
CRUD Operations on Person Object. Done by Poonam  Agarwal.


---------------About Development:----------------

It uses:
1. Spring Boot libraries.
2. REST and JPA to provide REST Repository to access CRUD Operations.
3. Junit for some unit tests
4. Maven to build the project.
5. Java 8.
6. In memory H2 database.
7. Used Eclipse for development and Spring Boot Tools for project generation.
8. Docker

----------------How to run using docker image:---------------
1 : Setup docker environment. Get docker and docker-compose.
On ubuntu use command:
   $ sudo apt update -q
   $ sudo apt install -q -y --no-install-recommends docker.io docker-compose
2 : Pull the docker image from docker hub.
    docker pull poonamthedeveloper/personservice
3 : Run the docker image
    docker run -p8082:8081 --name personService1 poonamthedeveloper/personservice

4 : To run multiple instances of the same service ,
change the port number of the host machine from 8082 to a different port(e.g. 8083) and use the command: 
   docker run -d -p8083:8081 --name personService2 poonam_personservice:latest
5 : Test the application REST API:
The applications runs on the port 8081 by default and the REST API is accessible at:
   http://localhost:8082/people


----------------Pre-Requisite for building project.----------------
1. Assumes Java 1.8 and Maven 3.6.* will be present on the system. 
2. JAVA_HOME and M2_HOME environment variables will be set.


----------------How to build and package a jar.----------------
1. Download or get the peronService project from GitHub. 
  git clone https://github.com/PoonamDeveloper/personService.git
2. Go to the folder personService.
  cd personService
3. Use the command "mvn clean package". It will build the jar file and run the tests by default.
   Alternatively, you could use the command "mvn clean package -DskipTests=true" to skip the tests.



----------------How to run the application using maven:----------------
1. Go to the folder personService. Use the command "mvn spring-boot:run"
The applications runs on the port 8081 by default and the REST API is accessible at:
http://localhost:8081/people



----------------How to build the docker image.----------------
1. cd personService
2. docker build --tag=poonam_personservice:latest .
3. docker tag poonam_personservice:latest poonamthedeveloper/personservice:latest
4. docker push poonamthedeveloper/personservice:latest


