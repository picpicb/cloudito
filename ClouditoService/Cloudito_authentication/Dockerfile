FROM openjdk:8-jdk

VOLUME /tmp
COPY /target/*.jar app.jar
RUN sudo echo "Europe/Paris" > /etc/timezone
RUN sudo dpkg-reconfigure -f noninteractive tzdata
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]