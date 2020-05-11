import mysql.connector
import requests
from requests import exceptions
import datetime
import json


class Database(object):
	def __init__(self, _config_):
		self.config = _config_
		self.url_api_recognition = _config_.get_api_url()

	def openConnexion(self):
		return mysql.connector.connect(
			host= self.config.get_database_host(),
			port= self.config.get_database_port(),
			user= self.config.get_database_user(),
			passwd= self.config.get_database_pwd(),
			database= self.config.get_database_name()
		)

	def getCachedCustomers(self):
		db = self.openConnexion()
		customerList = [""]
		cursor = db.cursor()
		cursor.execute("SELECT distinct customer_id FROM customer_detection")
		result = cursor.fetchall()
		for x in result:
			customerList.append(str(x[0]))
		cursor.close()
		db.close()
		return customerList

	def getAllCustomers(self):
		db = self.openConnexion()
		customerList = [""]
		cursor = db.cursor()
		cursor.execute("SELECT id FROM customer")
		result = cursor.fetchall()
		for x in result:
			customerList.append(str(x[0]))
		cursor.close()
		db.close()
		return customerList

	def saveRecognition(self, customer_id):
		now = datetime.datetime.utcnow()
		data = {'customerId': customer_id, 'recognitionDate': now.strftime('%Y-%m-%dT%H:%M:%S.%f')[:-3]+"Z"}
		headers = {'Content-type': 'application/json'}
		print(json.dumps(data))
		try:
			r = requests.post(self.url_api_recognition + '/recognitions', data=json.dumps(data), headers=headers)
		except requests.exceptions.HTTPError:
			return requests.exceptions.ConnectionError
		except requests.exceptions.ConnectionError:
			return requests.exceptions.ConnectionError
		except requests.exceptions.Timeout:
			return requests.exceptions.Timeout
		except requests.exceptions.RequestException:
			return requests.exceptions.RequestException