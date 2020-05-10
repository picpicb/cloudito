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

    def test_get_image(self):
        import cv2
        img = cv2.imread('../main/resources/merkel.jpg')
        gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        self.assertTrue(gray_img.any())

    def test_is_file_failure(self):
        self.assertFalse(vc.isVideoSrcValid(""))

    def test_valid_File(self):
        self.assertTrue(vc.isVideoSrcValid("../test.mp4"))

    def test_no_File_Exists(self):
        self.assertFalse(vc.isVideoSrcValid("../testtest.mp4"))






