FROM eclipse-temurin:17-jdk-jammy as build-image
LABEL authors="bete-mrt"

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY ./src/main/ ./src/main/
RUN ./mvnw clean package


FROM eclipse-temurin:17-jre-jammy

COPY --from=build-image /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
