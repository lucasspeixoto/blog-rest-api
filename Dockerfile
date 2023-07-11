FROM openjdk:17-jdk-slim

LABEL mentainer="lspeixotodev@gmail.com"

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app/app.jar

ENTRYPOINT ["java","-jar","/app.jar"]