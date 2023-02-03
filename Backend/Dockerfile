FROM openjdk:11-jdk-slim

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8099

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.name=application", "--spring.config.location=file:/back/"]