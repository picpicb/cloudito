import cv2
import os
import logging
from datetime import datetime 
import numpy as np
import socket, threading
import json
from json import JSONEncoder


class Recognizer():
	
	def __init__(self,database):

		# Init logger
		logging.basicConfig(format='%(asctime)s - %(levelname)s - %(message)s', level=logging.DEBUG)

		self.database = database		

		# Prepare recognizer with cached faces
		self.cachedDB = self.load_cached_db()

		# Prepare recognizer with all faces
		self.fullDB = self.load_full_db()

		# Count recognized faces to reload cachedDB
		self.recognition_counter = 0


	# face detection function
	def detect_face(self, img):
	    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
	    face_cascade = cv2.CascadeClassifier('daemonconf/opencv-files/haarcascade_frontalface_default.xml')
	    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=5);
	    if (len(faces) == 0):
	        return None, None
	    (x, y, w, h) = faces[0]
	    return gray[y:y+w, x:x+h], faces[0]


	# face recognizer training from cachedDB and fullDB
	def prepare_training_data(self, data_folder_path, subjects_list):
		dirs = os.listdir(data_folder_path)
		faces = []
		labels = []
		for dir_name in dirs:
			if not dir_name.startswith("s"):
				continue;
			if dir_name.replace("s", "") in subjects_list:
				label = int(dir_name.replace("s", ""))
				subject_dir_path = data_folder_path + "/" + dir_name
				subject_images_names = os.listdir(subject_dir_path)
				for image_name in subject_images_names:
					if image_name.startswith("."):
						continue;
					image_path = subject_dir_path + "/" + image_name
					image = cv2.imread(image_path)
					face, rect = self.detect_face(image)
					if face is not None:
						faces.append(face)
						labels.append(label)
		return faces, labels

	def load_cached_db(self):
		subjects1 = [] # cached idCustomers
		logging.info("Scanning faces from cache database...")
		subjects1 = self.database.getCachedCustomers()
		print(subjects1)
		faces1, labels1 = self.prepare_training_data("training-data", subjects1)
		logging.info("Scan completed : %s faces scanned",len(faces1))
		return [faces1, labels1]

	def load_full_db(self):
		logging.info("Scanning faces from database...")
		subjects2 = self.database.getAllCustomers()
		faces2, labels2 = self.prepare_training_data("training-data", subjects2)
		logging.info("Scan completed : %s faces scanned",len(faces2))
		return [faces2,labels2]

	def recognize(self, face):
		logging.info("Starting new recognition..")
		decodedArrays = json.loads(face)
		finalNumpyArray = np.asarray(decodedArrays["array"])
		confidence = 0
		face_recognizer = cv2.face.LBPHFaceRecognizer_create()

		#recognize from cacheDB
		if self.cachedDB[0] != [] :
			logging.info("Predicting image from cahchedDB...")
			face_recognizer.train(self.cachedDB[0], np.array(self.cachedDB[1]))
			label = face_recognizer.predict(finalNumpyArray)
			confidence = int(100*(1-(label[1]/300)))
			logging.info("Confidence is %d",confidence)

		if confidence < 80:
			#recognize from fullDB
			logging.info("Confidence is < 80%, Predicting from fullDB...")
			face_recognizer.train(self.fullDB[0], np.array(self.fullDB[1]))
			label = face_recognizer.predict(finalNumpyArray)
			confidence = int(100*(1-(label[1]/300)))

		if confidence >= 80:
			logging.debug("Person found : %s Confidence %d ", label[0],confidence)
			self.database.saveRecognition(label[0])
			self.recognition_counter += 1
			if self.recognition_counter > 5:
				self.cachedDB = self.load_cached_db()
				self.recognition_counter = 0

		else:
			logging.info("Person not found, confidence is %d", confidence)


