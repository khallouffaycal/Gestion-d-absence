package com.pfa1.API.service;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.DoublePointer;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_face.*;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;

import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_face.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
//Identification des visages a partir d'une image
public class OpenCVFaceRecognizerIm {
    public static ArrayList<Integer> recognize(File file) {
    	//Recuperation d'image
        Mat testImage = imread(file.getPath(), IMREAD_GRAYSCALE);
      //Modele utilisé(idEtudiant(label) + Data)
        String trainedResult = "/root/opencvtest/test.xml";
        //Les differentes methodes pour l'identification
        //FaceRecognizer faceRecognizer = FisherFaceRecognizer.create();
        // FaceRecognizer faceRecognizer = EigenFaceRecognizer.create();
        //Initiation d'un model d'identification des visages a partir du modele utilisé
        FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
        faceRecognizer.read(trainedResult);
        //Initiation d'un modele de detection des visages 
        CascadeClassifier face_cascade = new CascadeClassifier(
                "/root/opencv-4.3.0/data/lbpcascades/lbpcascade_frontalface.xml");
        Point p = new Point();
        RectVector faces = new RectVector();
        // Find the faces in the frame:
        face_cascade.detectMultiScale(testImage, faces);
        ArrayList<Integer> predicted= new ArrayList<>();
        ArrayList<Integer> predicted1= new ArrayList<>();
        for (int i = 0; i < faces.size(); i++) {
            Rect face_i = faces.get(i);

            Mat face = new Mat(testImage, face_i);
            // Now perform the prediction
            IntPointer label = new IntPointer(1);
            DoublePointer confidence = new DoublePointer(1);
            //Identification
            faceRecognizer.predict(face, label, confidence);
            int prediction = label.get(0);
            while(!predicted.contains(prediction)||(predicted.isEmpty())) {
            	predicted1.add(prediction);
            	break;
            }
            predicted=(ArrayList<Integer>) predicted1.clone();
        }
        return predicted;//Return des liste des etudiants detecté
    }
}