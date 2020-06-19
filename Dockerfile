FROM maven:3.6.3-jdk-11

WORKDIR /jee-server

COPY . .

RUN mvn package

<<<<<<< HEAD
EXPOSE 9000
=======
EXPOSE 8080
>>>>>>> 93de3eb1b54ed6273fc937cc7df5a6cbb63d9aa5

ENTRYPOINT ["java", "-jar", "/jee-server/target/server-last.jar"]

