package main;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

    class DetectObject {
        public void run() {
            System.out.println("\nRunning DetectFaceDemo");
            String cascadeFile1 = "C:/Dev/Imrec/ImrecVar/Training/trainedClassifier/cascade.xml";
            String cascadeFile2 = "C:/Users/TheOnlyMonkey/Desktop/ObjectMarker/data/cascade.xml";
            String testImage = "C:/Users/TheOnlyMonkey/Desktop/mtn.jpg";
            // Create a face detector from the cascade file in the resources
            // directory.
            CascadeClassifier faceDetector = new CascadeClassifier(cascadeFile2);

            Mat image = Highgui.imread(testImage);

            // Detect faces in the image.
            // MatOfRect is a special container class for Rect.
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(image, faceDetections);

            System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

            // Draw a bounding box around each face.
            for (Rect rect : faceDetections.toArray()) {
                Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
            }

            // Save the visualized detection.
            String filename = "C:/Users/TheOnlyMonkey/Desktop/mtn.jpg";
            System.out.println(String.format("Writing %s", filename));
            Highgui.imwrite(filename, image);
        }
    }

    public class ImageDetection {
        public static void main(String[] args) {
            System.out.println("Hello, OpenCV");

            // Load the native library.
            System.loadLibrary("opencv_java249");
            new DetectObject().run();
        }
    }
