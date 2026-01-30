# Base image with Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the built JAR into the container
COPY target/user-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 9090
# Run the JAR
CMD ["java", "-jar", "app.jar"]
