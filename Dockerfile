FROM openjdk:17-jdk

WORKDIR app/

COPY target/application-0.0.1-SNAPSHOT.jar .


ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-jar", "/app/app.jar"]