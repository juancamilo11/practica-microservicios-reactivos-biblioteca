FROM openjdk:11.0.12
EXPOSE 8080
ADD target/microservice-invoicing.jar microservice-invoicing.jar
ENTRYPOINT ["java", "-jar", "/microservice-invoicing.jar"]