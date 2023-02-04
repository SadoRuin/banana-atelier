FROM openjdk:11-jdk-slim

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

COPY /back/application.yml .

EXPOSE 8099

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Dspring.config.location=./application.yml", "-jar", "app.jar"]