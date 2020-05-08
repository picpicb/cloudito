import cv2
import json
import main.util.NumpyArrayEncoder as np
import main.interface.RecoInterface as recoInterface
from main.configuration.configuration import configuration
from main.util.NumpyArrayEncoder import np
from main.interface.recognitioninterface import RecognitionInterface

class FaceDetectionService:

    def run(self):
        config = configuration()
        self.recognitionInterface = RecognitionInterface(config)
        print("get image")
        gray_img = self.getImage()
        print("start detection")
        self.faceDetection(gray_img)

    # Detect face
    @classmethod
    def faceDetection(self, gray_img):
        faces_detected = []
        scaleFactor = 1.25
        neightborsSensibility = 5
        face_haar_cascade = cv2.CascadeClassifier('resources/haarcascade_frontalface_default.xml')
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
            self.recognitionInterface.pushFace(encodedNumpyData)

    # Catch an image an return his gray_scale [Mocked]
    @classmethod
    def getImage(cls):
        try:
            img = cv2.imread('resources/merkel.jpg')
        except FileNotFoundError:
            print("Image not found")
            raise
        gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        return gray_img

    # Send result to the interface
    def send(self, encodedNumpyData):
        recoInterface.RecognitionInterface.send(encodedNumpyData)
