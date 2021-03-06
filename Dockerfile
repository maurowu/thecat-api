FROM adoptopenjdk/openjdk11:latest

WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /target/thecat-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "thecat-0.0.1-SNAPSHOT.jar"]