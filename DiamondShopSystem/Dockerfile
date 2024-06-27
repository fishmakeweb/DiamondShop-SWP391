FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ./target/DiamondShopSystem-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
