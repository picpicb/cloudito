import unittest
from unittest.mock import patch
from numpy import asarray
import numpy as np
import json
from json import JSONEncoder
from daemonconf.configuration import Configuration
from recognizer.recognizer import Recognizer
from database.database import Database


class NumpyArrayEncoder(JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.ndarray):
            return obj.tolist()
        return JSONEncoder.default(self, obj)


# Test Recognizer module
class RecognizerTest(unittest.TestCase):
	

# Customer n°1 has 9 pictures stored. The recognizer must detect 9 faces from him
	@patch('database.database.Database.getCachedCustomers')
	def test_detect_custo1(self, getCachedCustomers_mock):
		getCachedCustomers_mock.return_value = ["","1"]

		configuration = Configuration()
		database = Database(configuration)
		recognizer = Recognizer(database)
		db = recognizer.load_cached_db()
		#customer n°1 has 9 pictures 
		self.assertEqual(len(db[0]), 9)

# Customer n°2 has 8 pictures stored. The recognizer must detect 8 faces from him
	@patch('database.database.Database.getCachedCustomers')
	def test_detect_custo2(self, getCachedCustomers_mock):
		getCachedCustomers_mock.return_value = ["","2"]

		configuration = Configuration()
		database = Database(configuration)
		recognizer = Recognizer(database)
		db = recognizer.load_cached_db()
		#customer n°2 has 8 pictures 
		self.assertEqual(len(db[0]), 8)


# If we use a known client picture, it must recognize the face which belongs to it
	@patch('database.database.Database.getCachedCustomers')
	def test_recognize_custo3(self, getCachedCustomers_mock):
		getCachedCustomers_mock.return_value = ["","3"]

		configuration = Configuration()
		database = Database(configuration)
		recognizer = Recognizer(database)
		db = recognizer.load_cached_db()
		#converting the face to json as we expect to receive from client request
		numpyData = {"array": asarray(db[0][0])}
		encodedNumpyData = json.dumps(numpyData, cls=NumpyArrayEncoder)
		
		self.assertEqual(recognizer.recognize(encodedNumpyData), 3)


if __name__ == '__main__':
    unittest.main()