import socket
import cv2
import os
import numpy as np
from recognition import RecognizerThread
import json
from json import JSONEncoder
from numpy import asarray


subjects = ["", "Macron", "Chirac"]

def detect_face(img):
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    face_cascade = cv2.CascadeClassifier('opencv-files/lbpcascade_frontalface.xml')
    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=5);
    if (len(faces) == 0):
        return None, None
    (x, y, w, h) = faces[0]
    return gray[y:y+w, x:x+h], faces[0]

#fonction qui analyse les images de la BDD
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


#Préparation de la base de donnée des visages
print("Preparing data...")
faces, labels = prepare_training_data("training-data")
print("Data prepared")
print("Total faces: ", len(faces))
print("Total labels: ", len(labels))

#Démarrage de l'écoute du socket
soc = socket.socket()
host = "localhost"
port = 2021
soc.bind((host, port))
soc.listen(5)
print("Waiting local client connexion on port ",port)
while True:
    conn, addr = soc.accept()
    newthread = RecognizerThread(addr, conn, faces, labels)
    newthread.start()




###### DEV : Face detection encoder to JSON #####
# class NumpyArrayEncoder(JSONEncoder):
#     def default(self, obj):
#         if isinstance(obj, np.ndarray):
#             return obj.tolist()
#         return JSONEncoder.default(self, obj)

# face, rect = detect_face(cv2.imread("test-data/test1.jpg"))
# numpyData = {"array": asarray(face)}
# encodedNumpyData = json.dumps(numpyData, cls=NumpyArrayEncoder)
# with open('sample.json', 'w') as outfile:
#     json.dump(encodedNumpyData, outfile)


# with open("sample.json") as json_file:
#     decodedArrays = json.loads(json_file.read())
   
#     finalNumpyArray = np.asarray(decodedArrays['array'])
#     print("NumPy Array")
#     print(finalNumpyArray)

