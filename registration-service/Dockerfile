# Build stage
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copy Maven wrapper and pom files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY common-libs/pom.xml common-libs/
COPY registration-service/pom.xml registration-service/
COPY registration-processor/pom.xml registration-processor/

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline -B -pl common-libs,registration-service -am

# Copy source code
COPY common-libs/src common-libs/src
COPY registration-service/src registration-service/src

# Build the application
RUN ./mvnw clean package -DskipTests -pl common-libs,registration-service -am

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built JAR (exclude original non-executable jar)
COPY --from=builder /app/registration-service/target/registration-service-*.jar app.jar

# Create non-root user
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
