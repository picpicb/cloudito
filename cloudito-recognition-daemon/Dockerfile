### 1. Get Linux
FROM python:3.7

VOLUME /tmp
ADD . /source
WORKDIR /source

RUN pip3 install -r requirements.txt
RUN ls -la
RUN pip3 install opencv-contrib-python
RUN pip3 list

EXPOSE 50051
CMD python3 -m recognitiondaemon