# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/kaddem-0.0.1-SNAPSHOT.jar /app/kaddem.jar

# Expose the application port
EXPOSE 8085

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/kaddem.jar"]
