FROM openjdk:11-jdk-slim
COPY target/trello-clone-0.0.1-SNAPSHOT.jar trello-clone-1.0.0.jar
ENTRYPOINT ["java","-jar","/trello-clone-1.0.0.jar"]