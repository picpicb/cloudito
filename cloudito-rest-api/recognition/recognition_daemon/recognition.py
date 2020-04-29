import cv2
import os
import logging
import numpy as np
import socket, threading
import json
from json import JSONEncoder


class RecognizerThread(threading.Thread):
	
	def __init__(self,clientAddress,clientsocket,faces,labels):
		threading.Thread.__init__(self)
		# Création du logger
		logging.config.fileConfig('logging.conf')
		self.logger = logging.getLogger('RecognizerDaemon')
		self.port = clientAddress[1]
		self.logger.info("Client %s Starting new recognition process",self.port)
		self.csocket = clientsocket
		self.faces = faces
		self.labels = labels
		self.subjects = ["", "Macron", "Chirac"]


	def run(self):
		line = ''
		face = bytearray()
		while True:
			line = self.csocket.recv(100).decode("utf-8")
			self.logger.info("Client %s Msg recv : %s",self.port, line)
			if "HELLO" in line : 
				self.csocket.send(b"HELLOACK\n")
				self.logger.info("Client %s Msg send : HELLOACK",self.port)
				self.logger.info("Client %s Downloading the picture...",self.port)
				while True :
					data = self.csocket.recv(1024)
					if len(data) < 1024 : 
						face.extend(data)
						break
					face.extend(data)
				self.logger.info("Client %s Downloading completed : %s bytes",self.port,len(face))
				decodedArrays = json.loads(face.decode("utf-8"))
				finalNumpyArray = np.asarray(decodedArrays["array"])
				face_recognizer = cv2.face.LBPHFaceRecognizer_create()
				face_recognizer.train(self.faces, np.array(self.labels))
				self.logger.info("Client %s Msg send : FACEACK",self.port)
				self.csocket.send(b"FACEACK\n")
				self.logger.info("Client %s Predicting image...",self.port)
				label = face_recognizer.predict(finalNumpyArray)
				self.logger.info("Client %s Person found : %s",self.port,self.subjects[label[0]])
				self.csocket.send((self.subjects[label[0]]+'\n').encode())
				self.logger.info("Client %s Msg send : person name",self.port)
			if "BYE" in line : 
				self.logger.info("Client %s Job done, closing connexion...",self.port)
				self.csocket.close()
				break


		
	#fonction pour détecter le visage (non utilisé sur le serveur)
	def detect_face(img):
		gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
		face_cascade = cv2.CascadeClassifier('opencv-files/lbpcascade_frontalface.xml')
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




