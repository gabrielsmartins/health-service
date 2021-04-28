FROM openjdk:11-jdk
VOLUME /app
COPY build/libs/*.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.active.profile=local", "health-service.jar"]