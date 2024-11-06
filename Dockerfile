FROM openjdk:17
EXPOSE 8089
COPY target/kaddem-0.0.1-SNAPSHOT.jar etudiant.jar
ENTRYPOINT ["java", "-jar", "etudiant.jar"]