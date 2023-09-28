FROM bitnami/java:17.0.7-7
COPY target/*.jar app.jar

# Lancer le script de démarrage
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080