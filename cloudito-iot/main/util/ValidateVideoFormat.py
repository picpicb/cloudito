from os import path

VALID_EXTENTIONS = ["avi", "mp4"]

class InvalidVideoSourceException(Exception):
    pass

def isVideoSrcValid(src):
    if (not path.isfile(src)):
        return False

    root, ext = path.splitext(src)

    return ext.lower() in VALID_EXTENTIONS

class VideoCapture(object):
    def __init__(self, videoSrc):
        if (not isVideoSrcValid(videoSrc)):
            raise InvalidVideoSourceException("Invalid video file.")
        self.videoSrc = videoSrc
