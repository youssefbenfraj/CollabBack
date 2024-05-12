FROM maven:3.2.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build app/target/espritColabBackEnd.1.0.jar espritCollabBackEnd.1.0.jar
ENTRYPOINT ["java","-jar","/app/espritCollabBackEnd.1.0.jar"]
