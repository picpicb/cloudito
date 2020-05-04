FROM openjdk:8-jdk

VOLUME /tmp
COPY /target/*.jar app.jar
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]





### 1. Get Linux
#FROM python:3.7
#
#VOLUME /tmp
#ADD . /source
#WORKDIR /source
#
#RUN pip3 install -r recognition_daemon/requirements.txt
#RUN ls -la
#RUN pip3 list
#
#ENV APT_KEY_DONT_WARN_ON_DANGEROUS_USAGE=DontWarn
#
#RUN set -e; \
#    apt-get update; \
#    apt-get install -y --no-install-recommends \
#        software-properties-common \
#    ; \
#    apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 0xB1998361219BD9C9; \
#    apt-add-repository 'deb http://repos.azulsystems.com/debian stable main'; \
#    apt-get update; \
#    apt-get install -y --no-install-recommends \
#        zulu-8 \
#    ; \
#    apt-get clean; \
#    rm -rf /var/tmp/* /tmp/* /var/lib/apt/lists/*
#
#COPY /target/*.jar app.jar
#
#EXPOSE 8087
#CMD bash startall.sh
#
#
#
