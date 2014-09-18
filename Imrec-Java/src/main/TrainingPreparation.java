package main;

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

    public TrainingPreparation(String directory, String writeDir, String posNeg){
        ArrayList<File> imageList = this.getImagesInDirectory(directory);
        this.writeToFile(imageList, writeDir, posNeg);
    }
    public TrainingPreparation(){
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

    public boolean isImage(String fileName){
        //TODO: Add 'n enum to check for different image extensions
        return true;
    }

    public void writeToFile(ArrayList<File> list,String outputLocation, String posNeg){
        String fileName = "error.txt";
        if (posNeg.equals("pos")){
            fileName = "/positives.dat";
        }else if (posNeg.equals("neg")){
            fileName = "/negatives.dat";
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

    public static void main(String args[]){
        String directory = "C:/Dev";
        TrainingPreparation prep = new TrainingPreparation();
    }
}
