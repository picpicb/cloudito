###### DEV : Face detection encoder to JSON #####
import socket
import cv2
import os
import sys
import logging
import logging.config
import numpy as np
from recognition import RecognizerThread
import json
from json import JSONEncoder
from numpy import asarray
import mysql.connector

class NumpyArrayEncoder(JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.ndarray):
            return obj.tolist()
        return JSONEncoder.default(self, obj)

face, rect = detect_face(cv2.imread("training-data/4.jpg"))
numpyData = {"array": asarray(face)}
encodedNumpyData = json.dumps(numpyData, cls=NumpyArrayEncoder)
with open('macron100.json', 'w') as outfile:
    json.dump(encodedNumpyData, outfile)


with open("macron100.json") as json_file:
    decodedArrays = json.loads(json_file.read())
   
    finalNumpyArray = np.asarray(decodedArrays['array'])
    print("NumPy Array")
    print(finalNumpyArray)
