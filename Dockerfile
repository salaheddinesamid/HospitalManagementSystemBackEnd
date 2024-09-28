FROM openjdk:17-jdk-alpine
COPY target/application-0.0.1-SNAPSHOT.jar /
ENTRYPOINT ["java", "-jar", "/application-0.0.1-SNAPSHOT.jar"]
