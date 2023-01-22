FROM openjdk:latest
COPY  ./target/profilemanagement-0.0.1-SNAPSHOT.jar profile_service.jar
CMD ["java","-jar","/profile_service.jar"]