# Microservice for Health Measurements using Hexagonal Architecture


[![Build Status](https://travis-ci.com/gabrielsmartins/health-service.svg?branch=master)](https://travis-ci.com/gabrielsmartins/health-service)

This Project is an Example Implementation of a Hexagonal Architecture using the following technologies:

- JDK 11 
- Gradle
- Lombok
- Spring WebFlux
- Spring Kafka
- Spring R2BC
- PostgreSQL Driver
- Docker

## Summary

- [Running Application](#running-application)  
- [Using Tools](#using-tools)
- [Usage](#usage)
- [Tutorial](#tutorial)

<a name="running-application"/>

### Running Application

1.Run Docker Services:

```bash
docker-compose up -d
```

2.Run Application:

```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```

<a name="using-tools"/>

### Using Tools

1.For Jaeger go to browser and navigate: http://localhost:16686/

2.For Kafka Control Center UI go to browser and navigate: http://localhost:9021/

3.For Postgres use PgAdmin4 with URL: http://localhost:5050 and configure Connection using host `postgres`

<a name="usage"/>

### Usage

1. Create a Person as following example:

```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{ "first_name" : "John", "last_name" : "Wick", "date_of_birthday": "1973-03-03", "gender": "M" }' \
  http://localhost:8080/health-v1/persons
```

2.Store a measurement using [Conduktor Tool](https://www.conduktor.io/download/) 

<a name="tutorial"/>

### Tutorial
See the article on Medium: [Spring Boot Project using Kafka and R2BC](https://gasmartins.medium.com/spring-boot-project-using-kafka-and-r2bc-part-i-a460627d308c)

