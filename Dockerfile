FROM openjdk:17 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17
WORKDIR commerce
COPY --from=build target/*.jar commerce.jar
ENTRYPOINT ["java", "-jar", "commerce.jar"]