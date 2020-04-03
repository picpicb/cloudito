import cv2
import numpy as np
import json
from json import JSONEncoder
import requests

class NumpyArrayEncoder(JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.ndarray):
            return obj.tolist()
        return JSONEncoder.default(self, obj)


img = cv2.imread('images.jpg')
gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
face_haar_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')

faces_detected = []
scaleFactor = 1.25
neightborsSensibility = 5
while len(faces_detected) == 0:
    faces_detected = face_haar_cascade.detectMultiScale(gray_img, scaleFactor=scaleFactor, minNeighbors=neightborsSensibility)
    if scaleFactor == 1.01:
        break
    scaleFactor = scaleFactor - 0.01
    if neightborsSensibility == 0:
        neightborsSensibility = 5
    else:
        neightborsSensibility = neightborsSensibility - 1

if len(faces_detected) == 0:
    print("Nothing detected.")

for face in faces_detected:
    (x, y, w, h) = face
    face_grey = gray_img[y:y + h, x:x + h]
    encodedNumpyData = json.dumps(face_grey, cls=NumpyArrayEncoder)  # use dump() to write array into file
    requests.post('http://localhost:8087/recognize',data=encodedNumpyData)

# Disabled (soon enable)
'''
i = 0
outputList = []
for face in faces_detected:
    (x, y, w, h) = face
    face_grey = gray_img[y:y + h, x:x + h]
    numpyData = {"i": face_grey}
    encodedNumpyData = json.dumps(numpyData, cls=NumpyArrayEncoder)  # use dump() to write array into file
    outputList.append(encodedNumpyData)
    i = i + 1
'''