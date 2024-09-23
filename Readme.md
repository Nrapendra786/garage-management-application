I developed this service some time ago with the purpose of creating a Garage Management System. 
Recently, I updated the service to use Spring Boot 3.2.1 and incorporated some new features.

## Functionalities:
1) The types of vehicles that can enter the garage are Car and Motorbike. <br/>
2) Locate vehicles in a multi-storey garage.
3) Role-based authentication, e.g., USER and MANAGER roles.

## How to use:
1) Java 17 or higher Java version and Docker must be installed on the system. In case docker is not available then use profile h2db, which is configured by default <br/>
2) Docker must be running in the system. <br/>
3) Open a command prompt. <br/>
4) Navigate to the project directory like this: cd [path to project]
5) Type following command: git clone [https://github.com/Nrapendra786/garage-management-application.git](https://github.com/Nrapendra786/garage-management-application.git)

## Command to start the project:
   docker-compose up -d

## Command to stop the project:
   docker-compose down 

## Profiles:
This project supports two profiles, and the commands to use them are as follows:
  1) H2 database (active by default):
     mvn clean package -P h2db
  2) PostgreSQL database:
     mvn clean package -P postgresdb

Note: Docker must be running. After executing docker-compose up -d, you can run the above command mentioned in step 2.

## Accessing Swagger UI:

To access Swagger UI, go to:
http://[hostname]:[portnumber]/[api-context]/swagger-ui.html

## Learning Outcomes:
1) Java <br/>
2) Spring Web <br/>
3) Spring Security <br/>
4) Spring Data <br/>
5) Swagger-UI/OpenAPI <br/>
6) Docker/Docker-Compose <br/>
7) Maven Profiles <br/>
8) H2/Postgres SQL <br/>
