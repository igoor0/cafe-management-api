FROM gradle:jdk17 AS build
COPY --chown=gradle:gradle ../.. /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test

FROM eclipse-temurin:17-jdk
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/0.0.1-SNAPSHOT.jar"]
