import cv2
import glob
import os

def facechop(image,imgname):
    facedata = "haarcascade.xml"
    cascade = cv2.CascadeClassifier(facedata)

    img = cv2.imread(image)

    minisize = (img.shape[1],img.shape[0])
    miniframe = cv2.resize(img, minisize)

    faces = cascade.detectMultiScale(miniframe)

    for f in faces:
        x, y, w, h = [ v for v in f ]
        cv2.rectangle(img, (x,y), (x+w,y+h), (255,255,255))

        sub_face = img[y:y+h, x:x+w]
        face_file_name = "/Users/karthik/Desktop/faces/" + imgname + "_" + str(y) + ".png"
        # print(face_file_name)
        cv2.imwrite(face_file_name, sub_face)
        if os.path.getsize(face_file_name) < 20 * 1024:
            os.remove(face_file_name)

    # cv2.imshow(image, img)
    # cv2.waitKey(0)
    cv2.destroyAllWindows()

    return

# if __name__  '__main__':
path = "/Users/karthik/Desktop/testimages"
images = glob.glob(path + "/*")
for imgpath in images[0:]:
    imagename = os.path.basename(imgpath)
    imgname = os.path.splitext(imagename)[0]
    facechop(imgpath, imgname)
    print(imgname,"exported")
