# Stage 1: Build the Java project
FROM gradle:8.6-jdk17-alpine AS builder
WORKDIR /app
COPY . /app/.
RUN gradle clean build -x test

# Stage 2: Create the final image with the JAR file
FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "app.jar"]