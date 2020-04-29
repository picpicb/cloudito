import cv2
import json
import requests
from util.NumpyArrayEncoder import np


class FaceDetectionService:
    def __init__(self):
        gray_img = self.getImage()
        self.faceDetection(gray_img)

    # Detect face
    @classmethod
    def faceDetection(self, gray_img):
        faces_detected = []
        scaleFactor = 1.25
        neightborsSensibility = 5
        face_haar_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
        while len(faces_detected) == 0:
            faces_detected = face_haar_cascade.detectMultiScale(gray_img, scaleFactor=scaleFactor,
                                                                minNeighbors=neightborsSensibility)
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
            encodedNumpyData = json.dumps(face_grey, cls=np.NumpyArrayEncoder)
            self.send(encodedNumpyData)

    # Catch an image an retur his gray_scale
    @classmethod
    def getImage(cls):
        img = cv2.imread('images.jpg')
        gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        return gray_img

    # Send result
    def send(encodedNumpyData):
        requests.post('http://localhost:8087/recognize', data=encodedNumpyData)
