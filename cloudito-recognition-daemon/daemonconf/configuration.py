import yaml


class Configuration(object):
    def __init__(self):
        self.configuration_file = "application.yml"
        self.configuration_data = None
        f = open(self.configuration_file, 'r')
        self.configuration_data = yaml.load(f.read(), Loader=yaml.FullLoader)
        f.close()

    # Database configuration
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

    # gRPC server configuration
    def get_grpcserver_port(self):
        return self.configuration_data['grpc']['server']['port']

    def get_api_url(self):
        return self.configuration_data['api']['url']


    # grpc client test config before implement (TODO : delete after implementation on client)
    def get_grpc_host(self):
        return self.configuration_data['grpc']['client']['host']
    def get_grpc_port(self):
        return self.configuration_data['grpc']['client']['port']
