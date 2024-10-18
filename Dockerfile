FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /workspace

ENV MAVEN_OPTS="-Xmx512m -XX:MaxMetaspaceSize=128m"

COPY pom.xml ./
COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim 
WORKDIR /app

COPY --from=build /workspace/target/*.jar /app

RUN mkdir -p /opt/hoptool/logs && touch /opt/hoptool/logs/invoiceme.log && RUN chmod -R 777 /opt/hoptool/logs/auth.log

EXPOSE 8877

USER 185
CMD ["java", "-jar", "/app/hoptool-invoicem-1.0.0-SNAPSHOT.jar"]
