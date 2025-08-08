# ---------- Step 1: Build stage ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy Maven files first for dependency caching
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn clean package -DskipTests

# ---------- Step 2: Run stage ----------
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Spring Boot default is 8080)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]