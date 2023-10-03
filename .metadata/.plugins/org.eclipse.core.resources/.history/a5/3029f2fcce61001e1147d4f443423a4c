# define base docker image
FROM openjdk:11
COPY target/sb_docker_app.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","sb_docker_app.jar"]
