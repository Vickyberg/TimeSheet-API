FROM maven:3.8.7 as build
COPY . .
RUN mvn package

FROM openjdk:17
COPY --from=build application/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}","-Dspring.profiles.active=staging","app.jar"]