import unittest


class OpenCVTest(unittest.TestCase):
    """ Test unitaire standard """

    def test_import(self):
        import cv2

    def test_video_capture(self):

        import cv2
        cap = cv2.VideoCapture("test.mp4")
        self.assertTrue(cap.isOpened())