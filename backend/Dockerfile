FROM eclipse-temurin:21.0.2_13-jdk-jammy AS builder

WORKDIR /opt/app
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY gradle ./gradle
COPY src ./src

RUN ./gradlew dependencies --no-daemon
RUN ./gradlew bootJar --no-daemon -x test

FROM eclipse-temurin:21.0.2_13-jre-jammy AS final

WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
