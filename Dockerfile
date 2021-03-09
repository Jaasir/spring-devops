FROM openjdk:8-jdk-alpine
EXPOSE 8089
ARG JAR_FILE=target/spring-devops-1.0.jar
ADD ${JAR_FILE} spring-devops.jar
ENTRYPOINT ["java","-jar","/spring-devops.jar"]