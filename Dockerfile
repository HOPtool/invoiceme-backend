# Stage 1: Build the application using Maven
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory
WORKDIR /workspace

# Copy the Maven project files
COPY . ./

# Package the application, skipping tests for faster build
RUN mvn clean package -DskipTests

# Stage 2: Create the final image using OpenJDK
FROM openjdk:17-jdk-slim AS final

# Set the working directory
WORKDIR /app

# Copy the packaged jar from the build stage to the final image
COPY --from=build /workspace/target/*.jar /app/hoptool-invoicem.jar

# Expose the application port (change if necessary)
EXPOSE 8877

# Command to run the application
CMD ["java", "-jar", "/app/hoptool-invoicem.jar"]
