FROM openjdk:11-jdk-slim

ARG JAR_FILE=build/libs/*.jar

WORKDIR /app

COPY ${JAR_FILE} app.jar

COPY /back/application.yml /back

EXPOSE 8099

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Dspring.config.location=/back/application.yml", "-jar", "app.jar"]