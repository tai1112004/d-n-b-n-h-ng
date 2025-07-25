
FROM maven:3.6.3-openjdk-17 AS build

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim

WORKDIR /app


COPY --from=build /app/target/shopping_baitalon-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENV PORT=8080


ENTRYPOINT ["java", "-jar", "app.jar"]