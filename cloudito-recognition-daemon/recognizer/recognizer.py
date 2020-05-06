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
		
		subjects1 = [] # cached idCustomers
		subjects2 = [] # all idCustomers

		# Prepare recognizer with cached faces
		logging.info("Scanning faces from cache database...")
		subjects1 = self.database.getCachedCustomers()
		print(subjects1)
		faces1, labels1 = self.prepare_training_data("training-data", subjects1)
		cachedDB = [faces1, labels1]
		logging.info("Scan completed : %s faces scanned",len(faces1))

		# Prepare recognizer with all faces
		logging.info("Scanning faces from database...")
		subjects2 = database.getAllCustomers()
		print(subjects2)
		faces2, labels2 = self.prepare_training_data("training-data", subjects2)
		fullDB = [faces2,labels2]
		logging.info("Scan completed : %s faces scanned",len(faces2))



	# face detection function
	def detect_face(self, img):
	    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
	    face_cascade = cv2.CascadeClassifier('daemonconf/opencv-files/lbpcascade_frontalface.xml')
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

