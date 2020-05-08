import yaml

class configuration(object):
    def __init__(self):
        self.configFile = "application.yml"
        self.configData = None
        f = open(self.configFile,"r")
        self.configData = yaml.load(f.read(),Loader=yaml.FullLoader)
        f.close()

    def getApplicationHost(self):
        return self.configData["grpc"]["client"]["host"]

    def getApplicationPort(self):
        return self.configData["grpc"]["client"]["port"]