FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY common-libs/pom.xml common-libs/
COPY registration-service/pom.xml registration-service/
COPY registration-processor/pom.xml registration-processor/

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline -B -pl common-libs,registration-processor -am

COPY common-libs/src common-libs/src
COPY registration-processor/src registration-processor/src

RUN ./mvnw clean package -DskipTests -pl common-libs,registration-processor -am

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/registration-processor/target/registration-processor-*.jar app.jar

RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

USER appuser

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
