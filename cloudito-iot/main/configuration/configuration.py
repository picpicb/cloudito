import yaml


class configuration(object):
    def __init__(self):
        self.configFile = "../../application.yml"
        self.configData = None
        f = open(self.configFile, "r")
        self.configData = yaml.load(f.read(), Loader=yaml.FullLoader)
        f.close()

    def get_grpc_host(self):
        return self.configData["grpc"]["client"]["host"]

    def get_grpc_port(self):
        return self.configData["grpc"]["client"]["port"]

    def get_roundedCapture(self):
        return self.configData["input"]["roundedCapture"]