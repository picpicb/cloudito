import socket
import cv2
import os
import sys
import logging
import numpy as np
from recognition import RecognizerThread
import json
from json import JSONEncoder
from numpy import asarray
import mysql.connector
from daemonconf.configuration import Configuration
from database.database import Database








# face detection function
def detect_face(img):
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    face_cascade = cv2.CascadeClassifier('daemonconf/opencv-files/lbpcascade_frontalface.xml')
    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=5);
    if (len(faces) == 0):
        return None, None
    (x, y, w, h) = faces[0]
    return gray[y:y+w, x:x+h], faces[0]


# face recognizer training from cachedDB and fullDB
def prepare_training_data(data_folder_path, subjects_list):
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
                face, rect = detect_face(image)
                if face is not None:
                    faces.append(face)
                    labels.append(label)
    return faces, labels


if __name__ == '__main__':

    # Configuration
    logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s', level=logging.DEBUG)
    configuration = Configuration()
    database = Database(configuration)

    logging.info("Recognizer daemon start")

    # Bases de données des noms des visages reconaissables
    subjects1 = [] # Il s'agit des visage en cache
    subjects2 = [] # Tous les visages connus

    # Préparation de la base de donnée des visages en cache
    logging.info("Scanning faces from cache database...")
    subjects1 = database.getCachedCustomers()
    print(subjects1)
    faces1, labels1 = prepare_training_data("training-data", subjects1)
    cachedDB = [faces1, labels1]
    logging.info("Scan completed : %s faces scanned",len(faces1))

    # Préparation de la base de donnée des tous les visages
    logging.info("Scanning faces from database...")
    subjects2 = database.getAllCustomers()
    print(subjects2)
    faces2, labels2 = prepare_training_data("training-data", subjects2)
    fullDB = [faces2,labels2]
    logging.info("Scan completed : %s faces scanned",len(faces2))

    # Démarrage de l'écoute du socket
    try:
        soc = socket.socket()
        soc.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        host = "localhost"
        port = 2021
        soc.bind((host, port))
        soc.listen(5)
        logging.info("Waiting client connexion on port %s",port)
        while True:
            conn, addr = soc.accept()
            newthread = RecognizerThread(addr, conn, cachedDB, fullDB)
            newthread.start()
            subjects1 = database.getCachedCustomers()
            faces1, labels1 = prepare_training_data("training-data", subjects1)
            cachedDB = [faces1, labels1]
    except socket.error as msg:
        logging.error("Couldnt connect with the socket : %s",msg)
        logging.warning("Terminating Program")
        sys.exit(1)






