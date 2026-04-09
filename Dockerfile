FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/onlinelearning-0.0.1-SNAPSHOT.jar /app/cse.jar
EXPOSE 8083
CMD ["java", "-jar", "/app/cse.jar"]