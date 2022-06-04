FROM maven:3.8-openjdk-11-slim as maven
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package

FROM openjdk:11-jre-slim-buster
WORKDIR /app
COPY --from=maven target/trello-clone-*.jar app.jar
CMD ["java", "-jar", "app.jar"]
