FROM openjdk:11.0-jdk-slim
COPY target/authenticationservice.jar authenticationservice.jar
EXPOSE 80
ENV awsClientId=dfd
ENV userPool=fdf
ENV awsSecretHash=fdf
ENV awsAccessKey=fdf
ENV awsSecretKey=fdf

CMD ["java", "-DawsAccessKey=awsAccessKey","-DawsSecretKey=awsSecretKey","-Dspring.profiles.active=cloud", "-jar","authenticationservice.jar"]