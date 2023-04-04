FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/foodapi.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
#FROM maven:3.8.6-openjdk-18
#WORKDIR /food-recipe
#COPY . .
#RUN mvn clean install
#CMD mvn spring-boot:run
