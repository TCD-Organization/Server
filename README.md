# TCD JEE-Server

## Overview
This server is a JEE Spring boot project
Authored by : CHAMPAUD Alexandre, TERRAL Hugo, DELMER Pierre

## Requirements
java 11+
maven 3.x+

## Running with docker-compose

### Starting the whole project
To run the whole project with docker-compose, please execute the following from the root directory:

```
docker-compose up -d
```

Note: This project will launch a RabbitMQ Broker, a Mongo Server, a Mongo-Express Web interface and a Spring Boot server.
To change the ports and custom variables, please add a .env file at the root of folder specifying the variables to override, or export them directly :
```
TCD_MONGO_HOST=127.0.0.1
TCD_MONGO_PORT=27017
...
```

### Starting specific parts of the project
To run specific parts of the project with docker-compose, please execute the following from the root directory:
```
docker-compose up -d [Options: jee-server, mongo, rabbitmq, mongo-express]
``` 


## Running with Docker

To run the server on a Docker container, please execute the following from the root directory:

```bash
# building the image
docker build -t tcd-jee-server .

# starting up a container
docker run -p 8080:8080 tcd-jee-server
```

