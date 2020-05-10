from os import path

VALID_EXTENTIONS = ["avi", "mp4"]

class VideoCapture(object):

    def isVideoSrcValid(src):
        if (not path.isfile(src)):
            return False

        root, ext = path.splitext(src)
        return ext.lower() in VALID_EXTENTIONS

    def __init__(self, videoSrc):
        if (not self.isVideoSrcValid(videoSrc)):
            raise print("Invalid video file.")
        self.videoSrc = videoSrc
