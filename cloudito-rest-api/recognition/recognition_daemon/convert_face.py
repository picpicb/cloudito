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
