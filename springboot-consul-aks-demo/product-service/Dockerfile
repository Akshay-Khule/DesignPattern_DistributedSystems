# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set argument for JAR name
ARG JAR_FILE=target/*.jar

# Copy jar to container
COPY ${JAR_FILE} app.jar

# Run application
ENTRYPOINT ["java", "-jar", "/app.jar"]
