# Usa un'immagine di base di Java
FROM openjdk:17-jdk-slim

# Imposta la directory di lavoro
WORKDIR /app

# Copia il JAR generato
COPY target/BigPig-Api-0.0.1-SNAPSHOT.jar app.jar

# Esponi la porta su cui l'app ascolta
EXPOSE 8080

# Comando per avviare l'app
CMD ["java", "-jar", "app.jar"]