FROM maven:3.6.0-jdk-11-slim AS build

COPY trades/src /home/app/src
COPY trades/pom.xml /home/app

CMD mvn -f /home/app/pom.xml jetty:run