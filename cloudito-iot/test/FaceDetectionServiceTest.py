import unittest

from service import FaceDetectionService


class FaceDetectionServiceTest(unittest.TestCase):
    """Unit test for face recognition"""


    def test_getImage(self):
        image = FaceDetectionService.getImage()
        self.assertTrue(image, not None)

    #  def test_send(self):

    # def face_detection(self):
