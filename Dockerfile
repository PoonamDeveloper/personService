FROM openjdk:8-jdk-alpine
MAINTAINER PoonamAgarwal
COPY target/personService-0.0.1.jar  /personService-0.0.1.jar
ENTRYPOINT ["java","-jar","/personService-0.0.1.jar"]