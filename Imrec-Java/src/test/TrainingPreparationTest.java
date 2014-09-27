package test;

import main.TrainingPreparation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrainingPreparationTest {
    @Before
    public void setup(){

    }
    @After
    public void closing(){
    }

    @Test
    public void testTraining(){
        String testDirectory;
    }
    @Test
    public void testGetImages(){
        String directory = "C:/Dev/Imrec/ImrecVar/tests";
        TrainingPreparation prep = new TrainingPreparation();
        assertTrue(prep.getImagesInDirectory(directory).size() > 0);
        //I have 10 images in this folder
        assertTrue(prep.getImagesInDirectory(directory).size() == 10);
    }
    @Test
    public void testWriteToFile(){
        String posNeg =  "neg";
        String directory = "C:/Dev/Imrec/ImrecVar/tests";
        String imageLocation = "C:/Dev/Imrec/ImrecVar";
        TrainingPreparation prep = new TrainingPreparation();
        ArrayList<File> list = prep.getImagesInDirectory(directory);
        prep.writeToFile(list,imageLocation, posNeg);
    }

    @Test
    public void testGetPosImagesData(){
        String posDirectory = "C:/temp/posDrop";
        String trainingDirectory = "C:/temp/trainDir";
        TrainingPreparation prep = new TrainingPreparation();
        prep.getPosImages(posDirectory, trainingDirectory);
        assertTrue(prep.fileExists(trainingDirectory + "/" + TrainingPreparation.POSDAT));
    }

    @Test
    public void testGetNegImagesData(){
        String negDirectory = "C:/temp/negDrop";
        String trainingDirectory = "C:/temp/trainDir";
        TrainingPreparation prep = new TrainingPreparation();
        prep.getNegImages(negDirectory, trainingDirectory);
        assertTrue(prep.fileExists(trainingDirectory + "/" + TrainingPreparation.NEGDAT));
    }
    @Test
    public void testIsImage(){
        String a = "c:/temp/image.jpg";
        String b = "c:/temp/image.txt";
        TrainingPreparation prep = new TrainingPreparation();
        assertTrue(prep.isImage(a));
        assertFalse(prep.isImage(b));
    }
}
