import mysql.connector



class Database(object):
	def __init__(self, _config_):
		self.db = mysql.connector.connect(
        	host= _config_.get_database_host(),
        	port= _config_.get_database_port(),
        	user= _config_.get_database_user(),
        	passwd= _config_.get_database_pwd(),
        	database= _config_.get_database_name()
    	)

	def getCachedCustomers(self):
		customerList = [""]
		cursor = self.db.cursor()
		cursor.execute("SELECT distinct id_customer FROM recognition_history")
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