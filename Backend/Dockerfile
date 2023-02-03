FROM openjdk:11-jdk-slim

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8099

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Dspring.config.location=optional:/back/application.yml", "-jar", "app.jar"]