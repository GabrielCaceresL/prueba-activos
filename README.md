# PruebaActivos REST API

> A simple Java project to create assets of a company 
>Developed by Gabriel Caceres Leguizamon

## Prerequisites

You will need the following things properly installed on your computer.

* [Git](http://git-scm.com/)
* [Maven](https://maven.apache.org/)
* [Docker](https://docs.docker.com/)
* [Docker-compose](https://docs.docker.com/compose/install/)

## Installation

This SpringBoot project use [Maven](https://maven.apache.org/), 
is an open-source build automation tool that is designed to be flexible enough to build almost any type of software.

* Execute: `git clone https://github.com/GabrielCaceresL/prueba-activos.git`.
* Change into the new directory `cd prueba-activos`

## Build project
* If you are in Windows, is recommended to use Git Bash to run the bash commands.

```bash
./mvn clean install 
```

## Run tests

```bash
./mvn clean test
```

## Run
- You need a Database to persist Data
- Before run application, follow the next steps:
> Deploy MariaDb:
> 1. Change into `cd docker-db`, in terminal use:
```bash
docker-compose up
```
In linux:
```bash
sudo docker-compose up

> If you already have [Postgres](https://www.postgresql.org/) installed in
your computer, you can modify the DataBase port, db username and db password in file `application.properties`,
>  * Current port is '5432'
>  * Current username is 'root'
>  * Current password is 'root'
>  - Properties to update: 
>  - jdbc:postgresql://localhost:5432/assets?createDatabaseIfNotExist=true?createDatabaseIfNotExist=true 
>  - spring.datasource.username='root'
>  - spring.datasource.password='root'

> Located into `cd src/main/resources`

---
Once you have an active database, open another terminal, go to the root folder `cd prueba-activos`, and use:

```bash
./mvn clean install
java -jar prueba-activos/asd/target/prueba-tecnica-asd-*.jar
```

* This application runs over port 8080, so you should prefix endpoints with http://localhost:8080/
## Swagger Documentation
http://ec2-3-235-41-158.compute-1.amazonaws.com:8080/swagger-ui/index.html
search "/v3/api-docs" and go to explore

> Is recommended to use [POSTMAN](https://www.postman.com/) to consume the Endpoints.
> * For local consume: (using localhost:8080)
> * Postman local Collection in `PruebaActivos localhost.postman_collection.json`
 ---
> * For remote consume: (using http://anagramsloadbalances-1195462994.us-east-2.elb.amazonaws.com)
> * Prod Postman Collection in `PruebaActivos AWS.postman_collection.json`



