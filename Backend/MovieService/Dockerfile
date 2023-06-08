FROM openjdk:17
EXPOSE 8081
ADD target/movie-service.jar movie-service.jar
ENTRYPOINT ["java","-jar","/movie-service.jar"]