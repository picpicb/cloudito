import yaml


class Configuration(object):
    def __init__(self):
        self.configuration_file = "application.yml"
        self.configuration_data = None
        f = open(self.configuration_file, 'r')
        self.configuration_data = yaml.load(f.read(), Loader=yaml.FullLoader)
        f.close()


    def get_database_host(self):
        return self.configuration_data['data']['mysqldb']['host']
    def get_database_name(self):
        return self.configuration_data['data']['mysqldb']['database']
    def get_database_port(self):
        return self.configuration_data['data']['mysqldb']['port']
    def get_database_user(self):
        return self.configuration_data['data']['mysqldb']['user']
    def get_database_pwd(self):
        return self.configuration_data['data']['mysqldb']['pwd']
