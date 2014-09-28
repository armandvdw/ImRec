package main;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * This Class is used to get the training images in the correct format, ready for training.
 *
 */
public class TrainingPreparation {

    public static final String POSDAT = "positives.dat";
    public static final String NEGDAT = "negatives.dat";

    public TrainingPreparation(){
    }

    public TrainingPreparation(String pos, String neg, String trainingDirectory){
        this.getPosImages(pos, trainingDirectory);
        this.getNegImages(neg, trainingDirectory);
    }

    public ArrayList<File> getImagesInDirectory(String dirName){
        File directory  =   new File(dirName);
        ArrayList<File> imageFiles = new ArrayList<File>();
        if (directory.isDirectory()){
            for (File file : directory.listFiles()) {
                if (this.isImage(file.getName())) {
                    imageFiles.add(file);
                }
            }
        }
        return imageFiles;
    }

    public boolean fileExists(String file){
        File f = new File(file);
        return f.exists();
    }

    public boolean isImage(String fileName){
        String extension = FilenameUtils.getExtension(fileName);
        Boolean isImage = false;
        switch (extension){
        case "jpg": isImage = true;
        case "png": isImage = true;
        case "bmp": isImage = true;
            break;
        default: isImage = false;
        }
        return isImage;
    }

    public void writeToFile(ArrayList<File> list,String outputLocation, String posNeg){
        String fileName = "error.txt";
        if (posNeg.equals("pos")){
            fileName = "/" + POSDAT;
        }else if (posNeg.equals("neg")){
            fileName = "/" + NEGDAT;
        }
        try {
            PrintWriter writer =  new PrintWriter(outputLocation + fileName, "UTF-8");
            for(File file : list){
                writer.println(file.getAbsolutePath());
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public void getPosImages(String dirName, String trainDir){
        ArrayList<File> posList = this.getImagesInDirectory(dirName);
        writeToFile(posList, trainDir, "pos");
    }

    public void getNegImages(String dirName, String trainDir){
        ArrayList<File> negList = this.getImagesInDirectory(dirName);
        writeToFile(negList, trainDir, "neg");
    }

    public static void main(String args[]){
        String posDirectory = "C:/temp/posDrop";
        String trainingDirectory = "C:/temp/trainDir";
        String negDirectory = "C:/temp/negDrop";
        new TrainingPreparation(posDirectory, negDirectory, trainingDirectory);

    }
}
