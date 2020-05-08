import unittest

import requests
from requests import exceptions

from main.interface import RecoInterface as recoInterface


class RecognitionInterfaceTest(unittest.TestCase):

    def test_sendSomethingWrong(self):
        self.assertEqual(requests.exceptions.RequestException, recoInterface.RecognitionInterface.send("test"))

# def test_sendOpenConnection(self):
#     self.assertEqual(requests.status_code == 200, recoInterface.RecognitionInterface.send("test"))
