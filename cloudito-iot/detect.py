import cv2

img = cv2.imread('images.jpg')

print(img)

cv2.imshow('image', img)
cv2.waitKey(5000000)
cv2.destroyAllWindows()
