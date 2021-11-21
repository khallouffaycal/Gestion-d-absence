package com.pfa1.API.service;
import java.io.File;
import java.util.ArrayList;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_face.*;
import org.bytedeco.opencv.opencv_objdetect.*;


import static org.bytedeco.opencv.global.opencv_imgproc.*;

//Partie de la detection des visage figurant dans le video
public class OpenCVFaceRecognizer {
	public static ArrayList<Integer> recognize(File videoFileName) throws Exception{

        OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();

        
        //Modele utilisé(idEtudiant(label) + Data)
        String trainedResult = "/root/opencvtest/test.xml";
        
        //Initiation d'un modele de detection des visages 
        CascadeClassifier face_cascade = new CascadeClassifier(
                "/root/opencv-4.3.0/data/lbpcascades/lbpcascade_frontalface.xml");
        ////Initiation d'un model d'identification des visages a partir du modele utilisé
        FaceRecognizer lbphFaceRecognizer = LBPHFaceRecognizer.create();
        lbphFaceRecognizer.read(trainedResult);

        
        FFmpegFrameGrabber grabber = null;
        try {
        	//Recuperation du video
            grabber = FFmpegFrameGrabber.createDefault(videoFileName);
            System.out.println(grabber.getPixelFormat());
            grabber.start();
            System.out.println(grabber.getFrameRate());
        } catch (Exception e) {
            System.err.println("Failed start the grabber.");
        }

        Frame videoFrame = null;
        Mat videoMat = new Mat();
        //Definition du liste des visage detection
        ArrayList<Integer> predicted= new ArrayList<>();
        predicted.add(0);
        ArrayList<Integer> predicted1= new ArrayList<>();
        while (true) {
        	//Recuperation du frame
			videoFrame = grabber.grabFrame();
			if(videoFrame==null) break;
            videoMat = converterToMat.convert(videoFrame);
            Mat videoMatGray = new Mat();
            // Convert the current frame to grayscale:
            
            cvtColor(videoMat, videoMatGray, COLOR_BGRA2GRAY);
            equalizeHist(videoMatGray, videoMatGray);

            Point p = new Point();
            RectVector faces = new RectVector();
            System.out.println("1");
            // Find the faces in the frame:
            face_cascade.detectMultiScale(videoMatGray, faces);

            
            for (int i = 0; i < faces.size(); i++) {
                Rect face_i = faces.get(i);

                Mat face = new Mat(videoMatGray, face_i);
                

                // Now perform the prediction
                IntPointer label = new IntPointer(1);
                DoublePointer confidence = new DoublePointer(1);
                //Identification
                lbphFaceRecognizer.predict(face, label, confidence);
                int prediction = label.get(0);
                //Ajout de visage s'il n'est pas dans la liste
                while(!predicted.contains(prediction)) {
                	predicted1.add(prediction);
                	break;
                }
                predicted=(ArrayList<Integer>) predicted1.clone();
            }
        }
        videoFileName.delete();
        return predicted;//Return du liste des etudiant detecte
    }

}