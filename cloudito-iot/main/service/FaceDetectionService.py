import cv2
import logging
import json
from main.configuration.configuration import configuration
from main.interface.recognitioninterface import RecognitionInterface
from main.util.ValidateVideoFormat import VideoCapture as vc


class FaceDetectionService:
    def __init__(self):
        logging.info("Starting iot")
        self.config = configuration()
        self.recognitionInterface = RecognitionInterface(self.config)
        logging.info("Begin traitment")
        inputPath = '../resources/g20.mp4'
        self.getInput(inputPath)
        self.beginTreatment(inputPath)
        logging.info("Traitment finished")
        exit()

    def faceDetection(self, gray_img):
        logging.info("[NEW] New instance of face detection")
        scaleFactor = 1.25
        neightborsSensibility = 5
        face_haar_cascade = cv2.CascadeClassifier('../resources/haarcascade_frontalface_default.xml')

        faces_detected = face_haar_cascade.detectMultiScale(gray_img, scaleFactor=scaleFactor,
                                                            minNeighbors=neightborsSensibility)
        if len(faces_detected) == 0:
            logging.info("Nothing detected.")
        for face in faces_detected:
            (x, y, w, h) = face
            face_grey = gray_img[y:y + h, x:x + h]
            encodedNumpyData = json.dumps(face_grey.tolist())
            self.recognitionInterface.pushFace(encodedNumpyData)
        logging.info("[END] Face detection")

    def getInput(self, filePath):
        if not vc.isVideoSrcValid(filePath):
            logging.error("Invalid format.")
            exit()
        if not vc.isVideoCanBeOpened(filePath):
            logging.error("File corrupted.")
            exit()
        else:
            return True

    def beginTreatment(self, inputFile):
        capture = cv2.VideoCapture(inputFile)
        success, image = capture.read()
        fps = capture.get(cv2.CAP_PROP_FPS)
        multiplier = fps * self.config.get_roundedCapture()

        while success:
            frameId = int(round(capture.get(1)))
            success, image = capture.read()
            if frameId % multiplier == 0:
                gray_img = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
                self.faceDetection(gray_img)


if __name__ == '__main__':
    FaceDetectionService()
