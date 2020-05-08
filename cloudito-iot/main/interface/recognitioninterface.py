import grpc
import logging
import recognize_pb2
import recognize_pb2_grpc

class RecognitionInterface(object):

	def __init__(self, _config_):
		self.host= _config_.get_grpc_host()
		self.port= _config_.get_grpc_port()
		url = self.host + ":" + str(self.port)
		print(url)
		self.channel = grpc.insecure_channel(url)
		self.stub = recognize_pb2_grpc.RecognizeStub(self.channel)
		logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s', level=logging.DEBUG)
		logging.info("gRPC interface connected to %s",url)

	def pushFace(self, face):
		# create a valid face request message
		message = recognize_pb2.Face(value=face)
		logging.info("gRPC interface pushed a message")
		# make the call, ignore the response (push)
		response = self.stub.Recognize(message)

