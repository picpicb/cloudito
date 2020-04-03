import cv2
import numpy as np
import json
from json import JSONEncoder


class NumpyArrayEncoder(JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.ndarray):
            return obj.tolist()
        return JSONEncoder.default(self, obj)

img = cv2.imread('images.jpg')
gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
face_haar_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
faces_detected = face_haar_cascade.detectMultiScale(gray_img, scaleFactor=1.32, minNeighbors=5)

i = 0
list = []
for face in faces_detected:
    (x, y, w, h) = face
    face_grey = gray_img[y:y + h, x:x + h]
    numpyData = {"array": face_grey}
    encodedNumpyData = json.dumps(numpyData, cls=NumpyArrayEncoder)  # use dump() to write array into file
    list.append(encodedNumpyData)
    i = i + 1

np.savez_compressed("result", a=list)

