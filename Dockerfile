FROM openjdk:17-jdk-slim

WORKDIR /usr/share/java

COPY ./build/libs/swe304-1-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
