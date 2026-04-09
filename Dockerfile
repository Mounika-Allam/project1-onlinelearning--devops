FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy any jar from target folder
COPY target/*.jar /app/cse.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app/cse.jar"]