FROM maven:3.9.8-amazoncorretto-21 AS build

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM tomcat:9.0.95-jdk21-temurin

WORKDIR /usr/local/tomcat
COPY --from=build /home/app/target/TaskHunter-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
