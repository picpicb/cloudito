FROM openjdk:8-jdk

VOLUME /tmp
COPY /target/*.jar app.jar
#Trying to find the right timezone to be sync with phones TOTP
#TEST 1
RUN echo "Europe/Paris" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata
RUN date


CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]