package main;

import org.bytedeco.javacv.CanvasFrame;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

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
            //Highgui.imwrite(filename, image);
            CanvasFrame fr =  new CanvasFrame("Frame");
            fr.showImage(this.toBufferedImage(image));
        }

    public Image toBufferedImage(Mat m){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;

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
