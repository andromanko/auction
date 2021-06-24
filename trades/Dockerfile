FROM maven:3.6.0-jdk-11-slim AS build
COPY api /home/app/api
COPY entity /home/app/entity
COPY rest /home/app/rest
COPY service /home/app/service
COPY pom.xml /home/app
CMD mvn -f /home/app/pom.xml jetty:run