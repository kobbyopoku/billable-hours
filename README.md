# billable-rate-system

## Description

A Kotlin REST API service for a law firm in Ghana to generate invoices for thier clients based on rates of employee lawyers and thier hourly rate

## Problem

Assignment 1: Billable Hours
 
You work for one of the top law firms in Ghana. To be as efficient as possible, your firm uses a billable rate system. This means every lawyer, depending on their grade has a billable rate and for any project they work on, they must send in the total number of hours they’ve worked on so that the finance team can create invoices for clients to pay. The lawyers send their timetable in the following format:

 
The finance team has requested your help to be effective with revenue collection. 


## Setup

### Database
1. Pull MySQL docker image
```shell
docker pull mysql/mysql-server:latest
```

2. Create MySQL Docker container from image
```shell
docker run --name=[container_name] -d mysql/mysql-server:latest
```

3. Run mysql container
```shell
docker exec -it [container_name] mysql -uroot -p
```

4. Finally, create the database
```shell
CREATE DATABASE `billablehours`
```


### Backend REST API service

1. Clone this repo unsing
```shell
git clone https://github.com/kobbyopoku/billable-hours.git
```

2. Biuld a docker image using command
```shell
sudo env "PATH=$PATH" mvn -Dlane=release -Dspring.profiles.active=release install dockerfile:build
```

3. Extact IMAGE_ID from build container using command
```shell
sudo docker run -d --name=billable-rate-service  -i --net=host --memory=512m --expose=8000 -t [IMAGE_ID] /bin/bash
```

## API Documentaion

Link to swagger ui documentation after running service
[Click here](http://localhost:8000/swagger-ui.html#/)

```shell
(http://localhost:8000/swagger-ui.html#/)
```
