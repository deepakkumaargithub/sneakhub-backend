FROM openjdk:17-alpine
EXPOSE 8181
COPY target/sneakhub-backend.jar sneakhub-backend.jar
ENTRYPOINT ["java","-jar","/sneakhub-backend.jar"]