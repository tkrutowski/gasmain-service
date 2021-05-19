FROM adoptopenjdk/openjdk11:jdk-11.0.2.7-alpine-slim
ADD java.security /opt/java/openjdk/conf/security
ADD target/gasconnection-0.0.1.jar .
EXPOSE 8090
CMD java -jar gasconnection-0.0.1.jar
