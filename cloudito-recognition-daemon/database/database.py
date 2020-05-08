import mysql.connector
import requests
from requests import exceptions
import datetime
import json


class Database(object):
	def __init__(self, _config_):
		self.db = mysql.connector.connect(
			host= _config_.get_database_host(),
			port= _config_.get_database_port(),
			user= _config_.get_database_user(),
			passwd= _config_.get_database_pwd(),
			database= _config_.get_database_name()
		)
		self.url_api_recognition = _config_.get_api_url()

	def getCachedCustomers(self):
		customerList = [""]
		cursor = self.db.cursor()
		cursor.execute("SELECT distinct customer_id FROM customer_detection")
		result = cursor.fetchall()
		for x in result:
			customerList.append(str(x[0]))
		cursor.close()
		return customerList

	def getAllCustomers(self):
		customerList = [""]
		cursor = self.db.cursor()
		cursor.execute("SELECT id FROM customer")
		result = cursor.fetchall()
		for x in result:
			customerList.append(str(x[0]))
		cursor.close()
		return customerList

	def saveRecognition(self, customer_id):
		now = datetime.datetime.utcnow()
		data = {'customerId': customer_id, 'recognitionDate': now.strftime('%Y-%m-%d %H:%M:%S')}
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