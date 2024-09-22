I developed this service some time ago with the purpose of creating a Garage Management System. 
Recently, I updated the service to use Spring Boot 3.2.1 and incorporated some new features.

## Functionalities:
1) The types of vehicles that can enter the garage are Car and Motorbike. <br/>
2) Locate vehicles in a multi-storey garage.
3) Role-based authentication, e.g., USER and MANAGER roles.

## How to use after cloning the repository:
Java 17 or higher and Docker must be installed on the system.
Open a command prompt.
Navigate to the project directory:
cd [path to project]

## To start the project:
docker-compose up -d

# To stop the project:
docker-compose down or docker-compose down --rmi all

## Profiles:
The project supports two profiles, and the commands to use them are as follows:

1) H2 database (active by default):
mvn clean package -P h2db
2) PostgreSQL database:
mvn clean package -P postgresdb

Note: Docker must be running. After executing docker-compose up -d, you can run the above command.

## Accessing Swagger UI:

To access Swagger UI, go to:
http://[hostname]:[portnumber]/[api-context]/swagger-ui.html

## Learning Outcomes:
Java
Spring Web
Spring Security
Spring Data
Swagger-UI/OpenAPI
Docker
