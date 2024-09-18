# Use an official OpenJDK image as a base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /ooad

# Copy the JAR file into the container
COPY target/ooad-0.0.1-SNAPSHOT.jar ooad-0.0.1-SNAPSHOT.jar

# Expose the port that the Spring Boot application will run on (e.g., 4000)
EXPOSE 4000

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "ooad-0.0.1-SNAPSHOT.jar"]
