FROM openjdk:8-jdk

VOLUME /tmp
COPY /target/*.jar app.jar
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]