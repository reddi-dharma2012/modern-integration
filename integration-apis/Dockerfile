# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Dharma <dharma@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8087 available to the world outside this container
EXPOSE 8087

# The application's jar file
ARG JAR_FILE=target/integration-api-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} integration-api.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/integration-api.jar"]

