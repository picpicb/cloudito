import unittest
from main.util.ValidateVideoFormat import VideoCapture as vc


class FaceDetectionServiceTest(unittest.TestCase):
    """Unit test for face recognition"""


    def test_import(self):
        import cv2

    def test_video_capture(self):
        import cv2
        cap = cv2.VideoCapture("../test.mp4")
        self.assertTrue(cap.isOpened())

    def test_format(self):
        pass

    def test_is_file_failure(self):
        self.assertFalse(vc.isVideoSrcValid("../test.mp4"))

    def test_valid_File(self):
        self.assertTrue(vc.isVideoSrcValid("../test.mp4"))

    def test_no_File_Exists(self):
        self.assertFalse(vc.isVideoSrcValid("../testtest.mp4"))






