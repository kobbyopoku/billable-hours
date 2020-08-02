# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
WORKDIR /opt/docker-jars
EXPOSE 8000
COPY target//billable-rate-service.jar /billable-rate-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/billable-rate-service.jar", "--spring.profiles.active=release", "--logging.file=billable-rate-service.log"]