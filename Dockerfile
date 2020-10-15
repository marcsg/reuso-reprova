FROM openjdk:11-jre-slim

COPY ./target/*.jar ./reprova.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker" ,"./reprova.jar"]

EXPOSE 8080