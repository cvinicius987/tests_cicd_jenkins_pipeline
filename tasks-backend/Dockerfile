FROM tomcat:8.5.50-jdk8-openjdk

ARG WAR_FILE
ARG CONTEXT

COPY ${WAR_FILE}  /usr/local/tomcat/webapps/${CONTEXT}.war

#FrontEnd
#docker build --build-arg WAR_FILE=frontend/target/tasks.war --build-arg CONTEXT=tasks -t test-front .