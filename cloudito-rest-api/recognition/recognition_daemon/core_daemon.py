import socket
import cv2
import os
import sys
import logging
import logging.config
import numpy as np
from recognition import RecognizerThread
import json
from json import JSONEncoder
from numpy import asarray

# fonction qui rogne l'image autour du visage
def detect_face(img):
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    face_cascade = cv2.CascadeClassifier('opencv-files/lbpcascade_frontalface.xml')
    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=5);
    if (len(faces) == 0):
        return None, None
    (x, y, w, h) = faces[0]
    return gray[y:y+w, x:x+h], faces[0]

# fonction qui analyse les images de la BDD
def prepare_training_data(data_folder_path):
    dirs = os.listdir(data_folder_path)
    faces = []
    labels = []
    for dir_name in dirs:
        if not dir_name.startswith("s"):
            continue;
        label = int(dir_name.replace("s", ""))
        subject_dir_path = data_folder_path + "/" + dir_name
        subject_images_names = os.listdir(subject_dir_path)
        for image_name in subject_images_names:
            if image_name.startswith("."):
                continue;
            image_path = subject_dir_path + "/" + image_name
            image = cv2.imread(image_path)
            face, rect = detect_face(image)
            if face is not None:
                faces.append(face)
                labels.append(label)
    return faces, labels


# Base de données des noms des visages reconaissables
subjects = ["", "Macron", "Chirac"]

# Création du logger
logging.config.fileConfig('logging.conf')
logger = logging.getLogger('RecognizerDaemon')

# Préparation de la base de donnée des visages
logger.info("Recognizer daemon start")
logger.info("Scanning faces from database...")
faces, labels = prepare_training_data("training-data")
logger.info("Scan completed : %s faces scanned",len(faces))

# Démarrage de l'écoute du socket
try:
    soc = socket.socket()
    soc.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    host = "localhost"
    port = 2021
    soc.bind((host, port))
    soc.listen(5)
    logger.info("Waiting client connexion on port %s",port)
    while True:
        conn, addr = soc.accept()
        newthread = RecognizerThread(addr, conn, faces, labels)
        newthread.start()
except socket.error as msg:
    logger.error("Couldnt connect with the socket : %s",msg)
    logger.warning("Terminating Program")
    sys.exit(1)






