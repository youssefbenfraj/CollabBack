FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build app/target/espritColabBackEnd.1.0.jar espritCollabBackEnd.1.0.jar
ENTRYPOINT ["java","-jar","/app/espritCollabBackEnd.1.0.jar"]
