FROM openjdk:11
COPY target/pagenumberreducer-1.0.1.jar /backend.jar
CMD ["java", "-jar", "/backend.jar"]
