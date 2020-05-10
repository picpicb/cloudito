from os import path

VALID_EXTENTIONS = ["avi", "mp4"]

class VideoCapture(object):

    def isVideoSrcValid(src):
        if (not path.isfile(src)):
            return False

        filenameTokens = path.splitext(src)

        if (len(filenameTokens) < 1):
            return False

        if (not filenameTokens[1][1:].lower() in VALID_EXTENTIONS):
            return False

        return True

    def __init__(self, videoSrc):
        if (not self.isVideoSrcValid(videoSrc)):
            raise print("Invalid video file.")
        self.videoSrc = videoSrc
