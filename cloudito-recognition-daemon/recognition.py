import cv2
import os
import logging
from datetime import datetime 
import numpy as np
import socket, threading
import json
from json import JSONEncoder
import mysql.connector
from mysql.connector import Error


class RecognizerThread(threading.Thread):
	
	def __init__(self,clientAddress,clientsocket,cachedDB,fullDB):
		threading.Thread.__init__(self)
		self.port = clientAddress[1]
		# Création du logger
		logging.config.fileConfig('logging.conf')
		self.logger = logging.getLogger(str(self.port)+' - ClientHandler')
		self.logger.info("Starting new recognition process")
		self.csocket = clientsocket
		self.cachedDB = cachedDB
		self.fullDB = fullDB
		self.subjects = ["", "1", "2"]
		self.mydb = mysql.connector.connect(
  			host="172.31.254.54",
  			port=8006,
  			user="cloudito",
  			passwd="1234",
  			database="cloudito"
		)

	def cacheCustomer(self, idCustomer):
		query = "INSERT INTO recognition_history(id_recognition,id_customer,date) VALUES(%s,%s,%s)"
		now = datetime.now()
		formatted_date = now.strftime('%Y-%m-%d %H:%M:%S')
		print(formatted_date)
		args = (1, idCustomer, formatted_date)
		try:
			cursor = self.mydb.cursor()
			cursor.execute(query, args)
			self.mydb.commit()

		except Error as error:
			print(error)

		finally:
			cursor.close()
			self.mydb.close()

	def run(self):
		line = ''
		face = bytearray()
		while True:
			line = self.csocket.recv(100).decode("utf-8")
			self.logger.info("Msg recv : %s", line)
			if "HELLO" in line : 
				self.csocket.send(b"HELLOACK\n")
				self.logger.info("Msg send : HELLOACK")
				self.logger.info("Downloading the picture...")
				while True :
					data = self.csocket.recv(1024)
					if len(data) < 1024 : 
						face.extend(data)
						break
					face.extend(data)
				self.logger.info("Downloading completed : %s bytes", len(face))
				decodedArrays = json.loads(face.decode("utf-8"))
				finalNumpyArray = np.asarray(decodedArrays["array"])
				self.logger.info("Msg send : FACEACK")
				self.csocket.send(b"FACEACK\n")

				confidence = 0
				face_recognizer = cv2.face.LBPHFaceRecognizer_create()

				#recognize from cacheDB
				if self.cachedDB[0] != [] :
					self.logger.info("Predicting image from cahchedDB...")
					face_recognizer.train(self.cachedDB[0], np.array(self.cachedDB[1]))
					label = face_recognizer.predict(finalNumpyArray)
					confidence = int(100*(1-(label[1]/300)))

				print(confidence)
				if confidence < 80:
					#recognize from fullDB
					self.logger.info("Confidence is < 90%, Predicting from fullDB...")
					face_recognizer.train(self.fullDB[0], np.array(self.fullDB[1]))
					label = face_recognizer.predict(finalNumpyArray)
					confidence = int(100*(1-(label[1]/300)))
				
				if confidence >= 80:
					self.logger.info("Person found : %s", label[0])
					print(confidence)
					self.csocket.send((str(label[0])+'\n').encode())
					self.logger.info("Msg send : idCustomer")
					self.cacheCustomer(label[0])
				else:
					self.logger.info("Person not found")
					print(confidence)
					self.csocket.send((str(label[0])+'\n').encode())
					self.logger.info("Msg send : 0")
			if "BYE" in line : 
				self.logger.info("Job done, closing connexion...")
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




