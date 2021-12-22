package lbph;

import Histogram.Histogram;
import Metric.Metric;
import lbp.Lbp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Lbph {

    public static Params pr;
    public static TrainingData td;
    public Exception FileNotFoundException;

    public void init(){
        pr=new Params(1,8,8,8);
    }

    public void init(Params params){ this.pr=params; }

    public TrainingData getTrainingData(){
        return td;
    }

    Exception  checkSize(ArrayList<BufferedImage> imageArrayList){
        for(int index=0;index<imageArrayList.size();index++){
            if(imageArrayList.get(index)==null){
                return FileNotFoundException;
            }
        }
        return null;

    }

    public boolean trainData(ArrayList<BufferedImage> imageArrayList,ArrayList<String> labels){
        td=null;
        if(imageArrayList.size()==0||labels.size()==0){
            return false;
        }
        if(imageArrayList.size()!=labels.size()){
            return false;
        }
        if(checkSize(imageArrayList)!=null){
            return false;
        }
        ArrayList<ArrayList<Integer>> histogram=new ArrayList<ArrayList<Integer>>();

        for(int index=0;index<imageArrayList.size();index++){
            ArrayList<ArrayList<Integer>> pixels=Lbp.Calculate(imageArrayList.get(index),pr.radius,pr.neighbours);
            ArrayList<Integer> hist=Histogram.Calculate(pixels,pr.gridX,pr.gridY);
            histogram.add(hist);

        }
        td=new TrainingData(imageArrayList,labels,histogram);
        return true;

    }

    public static String[] predict(String filepath){
        BufferedImage img = null;

        try
        {
            img = ImageIO.read(new File(filepath)); // eventually C:\\ImageTest\\pic2.jpg
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if(td==null){
            return null;
        }
        ArrayList<ArrayList<Integer>> pixels= Lbp.Calculate(img,pr.radius,pr.neighbours);

        ArrayList<Integer> hist= Histogram.Calculate(pixels,pr.gridX,pr.gridY);

        int minDistance= Histogram.Compare(hist,td.histogram.get(0), Metric.EuclideanDistance);

        int minIndex=0;
        int dist=Integer.MAX_VALUE;
//        int minIndex=Integer.MAX_VALUE;
        for(int index=0;index<td.histogram.size();index++){
            minDistance=Histogram.Compare(hist,td.histogram.get(index),Metric.EuclideanDistance);
            System.out.println("distanceeee: ------"+minDistance);

            if(dist>minDistance){
                dist=minDistance;
                minIndex=index;
            }
        }

        String[] out=new String[2];
        out[0]=td.labels.get(minIndex);
        out[1]=Integer.toString(dist);
        return out;
    }
}
