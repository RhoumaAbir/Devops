FROM openjdk:17
EXPOSE 8089
COPY target/kaddem-0.0.1-SNAPSHOT.jar equipe.jar
ENTRYPOINT ["java", "-jar", "equipe.jar"]