# Use Maven with OpenJDK for the build stage
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /workspace

# Set Maven options
ENV MAVEN_OPTS="-Xmx512m -XX:MaxMetaspaceSize=128m"

# Copy the Maven project files
COPY pom.xml ./
COPY src ./src

# Package the application, skipping tests
RUN mvn package -DskipTests

# Use OpenJDK slim for the runtime stage
FROM openjdk:17-jdk-slim 
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /workspace/target/*.jar /app/

# Create logs directory and initialize log files
RUN mkdir -p /opt/hoptool/logs && \
    touch /opt/hoptool/logs/invoiceme.log && \
    chmod -R 777 /opt/hoptool/logs/invoiceme.log

# Expose the application port
EXPOSE 8877

# Set the user for running the application
USER 185

# Command to run the application
CMD ["java", "-jar", "/app/hoptool-invoicem-1.0.0-SNAPSHOT.jar"]
