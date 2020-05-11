###### DEV : Face detection encoder to JSON #####
import socket
import cv2
import os
import sys
import logging
import logging.config
import numpy as np
import json
from json import JSONEncoder
from numpy import asarray
import mysql.connector

class NumpyArrayEncoder(JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.ndarray):
            return obj.tolist()
        return JSONEncoder.default(self, obj)


	#fonction pour détecter le visage (non utilisé sur le serveur)
def detect_face(img):
	gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
	face_cascade = cv2.CascadeClassifier('opencv-files/haarcascade_frontalface_default.xml')
	faces = face_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=5);
	if (len(faces) == 0):
		return None, None
	(x, y, w, h) = faces[0]
	return gray[y:y+w, x:x+h], faces[0]


dirs = os.listdir(".")
for f in dirs:
	if not f.endswith(".jpg"):
		continue;
	face, rect = detect_face(cv2.imread(f))
	numpyData = {"array": asarray(face)}
	encodedNumpyData = json.dumps(numpyData, cls=NumpyArrayEncoder)
	with open(f.replace(".jpg", ".json"), 'w') as outfile:
		json.dump(encodedNumpyData, outfile)




