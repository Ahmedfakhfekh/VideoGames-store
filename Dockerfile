FROM openjdk:17
COPY ./target/video-games-0.0.1-SNAPSHOT.jar video-games-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "video-games-0.0.1-SNAPSHOT.jar"]