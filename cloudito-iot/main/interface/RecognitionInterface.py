import requests
from requests import exceptions


class RecognitionInterface:

    def send(encodedNumpyData):
        try:
            r = requests.post('http://localhost:8087/recognize', data=encodedNumpyData)
            r.raise_for_status()
        except requests.exceptions.HTTPError:
            return requests.exceptions.ConnectionError
        except requests.exceptions.ConnectionError:
            return requests.exceptions.ConnectionError
        except requests.exceptions.Timeout:
            return requests.exceptions.Timeout
        except requests.exceptions.RequestException:
            return requests.exceptions.RequestException