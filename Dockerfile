FROM maven:3.8.0-corretto-11 as build

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM tomcat:9.0.65-jdk11-corretto

WORKDIR /usr/local/tomcat
COPY --from=build home/app/target/TaskHunter-1.0-SNAPSHOT /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
