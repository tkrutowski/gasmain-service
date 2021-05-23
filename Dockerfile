FROM adoptopenjdk/openjdk11:jdk-11.0.2.7-alpine-slim
ADD java.security /opt/java/openjdk/conf/security
ADD target/maingas-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8091
CMD java -jar maingas-service-0.0.1-SNAPSHOT.jar
