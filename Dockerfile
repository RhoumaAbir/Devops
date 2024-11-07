FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/kaddem-0.0.1-SNAPSHOT.jar /app/kaddem.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/app/kaddem.jar"]
