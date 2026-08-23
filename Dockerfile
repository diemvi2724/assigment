# Stage 1: Build application
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy dependency definitions and pre-fetch dependencies
COPY pom.xml .
COPY app/pom.xml app/
RUN mvn -B dependency:go-offline -f pom.xml

# Copy source code and build package
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Runtime environment
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Add non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy built artifact
COPY --from=builder /app/app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
