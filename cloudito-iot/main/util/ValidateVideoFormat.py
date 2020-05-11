from os import path
import cv2

VALID_EXTENTIONS = ["avi", "mp4"]


class VideoCapture(object):

    def isVideoSrcValid(src):
        if not path.isfile(src):
            return False
        filenameTokens = path.splitext(src)
        if len(filenameTokens) < 1:
            return False
        if not filenameTokens[1][1:].lower() in VALID_EXTENTIONS:
            return False
        return True

    def isVideoCanBeOpened(filePath):
        capture = cv2.VideoCapture(filePath)
        if not capture.isOpened():
            return False
        else:
            return True

    def __init__(self, videoSrc):
        if not self.isVideoSrcValid(videoSrc):
            raise print("Invalid video file.")
        if not self.isVideoCanBeOpened(videoSrc):
            raise print("File corrupted.")
        self.videoSrc = videoSrc
