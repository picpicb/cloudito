import unittest
from main.util.ValidateVideoFormat import VideoCapture as vc


class FaceDetectionServiceTest(unittest.TestCase):
    """Unit test for face recognition"""


    def test_import(self):
        import cv2

    def test_video_capture(self):
        import cv2
        cap = cv2.VideoCapture("../main/resources/g20.mp4")
        self.assertTrue(cap.isOpened())

    def test_format(self):
        pass

    def test_is_file_failure(self):
        self.assertFalse(vc.isVideoSrcValid("../main/resources/haarcascade_frontalface_default.xml"))

    def test_valid_format_File(self):
        self.assertTrue(vc.isVideoSrcValid("../main/resources/g20.mp4"))

    def test_no_File_Exists(self):
        self.assertFalse(vc.isVideoSrcValid("../main/resources/gz20.mp4"))

    def test_valid_file(self):
        self.assertTrue(vc.isVideoCanBeOpened("../main/resources/g20.mp4"))





