FROM openjdk:11.0-jdk-slim
COPY target/stockservice.jar stockservice.jar
EXPOSE 80
ENV datasource.url=abc
ENV datasource.username=abc
ENV datasource.password=abc
ENV spring.profiles.active=abc
ENV awsAccessKey=key
ENV awsSecretKey=sec

CMD ["java","-DawsAccessKey=awsAccessKey","-DawsSecretKey=awsSecretKey",  "-Ddatasource.url=datasource.url","-Ddatasource.username=datasource.username","-Dspring.profiles.active=docker", "-jar","stockservice.jar"]