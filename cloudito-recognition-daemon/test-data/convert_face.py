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
	face_cascade = cv2.CascadeClassifier('opencv-files/haarcascade_frontalface_alt.xml')
	faces = face_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=5);
	if (len(faces) == 0):
		return None, None
	(x, y, w, h) = faces[0]
	return gray[y:y+w, x:x+h], faces[0]


def predict(test_img):
	img = test_img.copy()
	face, rect = detect_face(img)
	label = face_recognizer.predict(face)
	label_text = subjects[label[0]]
	return img

face, rect = detect_face(cv2.imread("macrom.jpg"))
numpyData = {"array": asarray(face)}
encodedNumpyData = json.dumps(numpyData, cls=NumpyArrayEncoder)
with open('macron1.json', 'w') as outfile:
    json.dump(encodedNumpyData, outfile)


with open("macron1.json") as json_file:
    decodedArrays = json.loads(json_file.read())
   
    finalNumpyArray = np.asarray(decodedArrays['array'])
    print("NumPy Array")
    print(finalNumpyArray)


