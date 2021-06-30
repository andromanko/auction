FROM maven:3.6.0-jdk-11-slim AS build
COPY payment/src /home/app/src
COPY payment/pom.xml /home/app

CMD mvn -f /home/app/pom.xml jetty:run