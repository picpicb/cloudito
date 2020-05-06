import socket
import cv2
import os
import sys
import logging
import numpy as np
from recognition import RecognizerThread
import json
from json import JSONEncoder
from numpy import asarray
import mysql.connector
from daemonconf.configuration import Configuration
from database.database import Database
import grpc
from concurrent import futures
import recognize_pb2
import recognize_pb2_grpc
import time
from recognizer.recognizer import Recognizer



class RecognizeServicer(recognize_pb2_grpc.RecognizeServicer):

    def Recognize(self, request, context):
        response = recognize_pb2.Empty()
        print(request.value)
        return response
    


if __name__ == '__main__':

    # Configuration
    logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s', level=logging.DEBUG)
    configuration = Configuration()
    logging.info("Recognizer daemon initializing...")
    database = Database(configuration)
    recognizer = Recognizer(database)
    port = str(configuration.get_grpcserver_port())


    server = grpc.server(futures.ThreadPoolExecutor(max_workers=50))
    recognize_pb2_grpc.add_RecognizeServicer_to_server(RecognizeServicer(),server)

    logging.info('Daemon started. Listening on port %s.',port)
    server.add_insecure_port('[::]:'+port )
    server.start()

    # since server.start() will not block,
    # a sleep-loop is added to keep alive
    try:
        while True:
            time.sleep(86400)
    except KeyboardInterrupt:
        server.stop(0)





