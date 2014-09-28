package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: TheOnlyMonkey
 * Date: 9/2/14
 * Time: 11:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImageTraining {
    String posDat;
    String negDat;
    String trainDir;

    public static final String VECFILE = "sample.vec";

    public ImageTraining(){
    }
    public ImageTraining(String posDat,String negDat, String trainDir){
        this.trainDir = trainDir + "/";
        this.posDat = this.trainDir + TrainingPreparation.POSDAT;
        this.negDat = this.trainDir + TrainingPreparation.NEGDAT;

    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }
     public String opencvCreateSamples(String imageInfo){
          if (imageInfo.isEmpty())return "Error, file provided does not exist";
          CvComm cv = new CvComm();
          Command com = new Command("opencv_createsamples");
          com.addParam(cv.height.setVal("25"));
          com.addParam(cv.width.setVal("25"));
          com.addParam(cv.vector.setVal(this.trainDir + ImageTraining.VECFILE));
          if(imageInfo.contains(".dat")){
              com.addParam(cv.info.setVal(imageInfo));
          }else{
              com.addParam(cv.image.setVal(imageInfo));
              com.addParam(cv.numSamples.setVal("500"));
          }
         com.addParam(cv.show.setVal("4.0"));
         return this.executeCommand(com.cmd);
      }



    class ComParam{
        String param;
        String value;
        public ComParam(String param, String value){
            this.param = param;
            this.value = value;
        }
        public String toString(){
            return " " + this.param + " " +  this.value;
        }
        public ComParam setVal(String val){
            this.value = val;
            return this;
        }
    }
    class Command{
        String cmd;
        public Command(String command){
            this.cmd = command;
        }
        public Command addParam(ComParam param){
            if (param != null){
                this.cmd  =  this.cmd + param.toString();
                return this;
            }
             return null;
        }
    }
    class CvComm{
        ComParam vector = new ComParam("-vec", "");
        ComParam info = new ComParam("-info", "");
        ComParam image = new ComParam("-img", "");
        ComParam bacground = new ComParam("-bg", "");
        ComParam numSamples = new ComParam("-num", "");
        ComParam bgColor = new ComParam("-bgcolor", "");
        ComParam show = new ComParam("-show", "");
        ComParam invert = new ComParam("-inv", "");
        ComParam maxidev = new ComParam("-maxidev", "");
        ComParam maxxangle = new ComParam("-maxxangle", "");
        ComParam maxyangle = new ComParam("-maxyangle", "");
        ComParam maxzangle =  new ComParam("-maxzangle0", "");
        ComParam width = new ComParam("-w", "");
        ComParam height = new ComParam("-h", "");
    }

    public static void main(String []args){
        //Temporary hard coded values.
        String posDirectory = "C:/temp/posDrop";
        String trainDir = "C:/temp/trainDir";
        String negDirectory = "C:/temp/negDrop";
        // TODO: if the file to be trained is a positive file, we can pass the img through to the createSamples method.
        String img = posDirectory + "/mtn1.jpg";
        TrainingPreparation prep = new TrainingPreparation(posDirectory, negDirectory, trainDir);
        ImageTraining train = new ImageTraining(posDirectory, negDirectory,trainDir);

        //TODO: for the time being we pass through the positves.dat file until we need image training.
        String output = train.opencvCreateSamples(img);
        System.out.println(output);
    }

}
