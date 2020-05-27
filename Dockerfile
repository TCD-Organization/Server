FROM maven:3.6.3-jdk-11

WORKDIR /jee-server

COPY . .

RUN mvn package

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/jee-server/target/server-last.jar"]

