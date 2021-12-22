package lbph;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TrainingData {
    public ArrayList<String> labels;
    public ArrayList<BufferedImage> imageArray;
    public ArrayList<ArrayList<Integer>> histogram;

    public TrainingData(BufferedImage img,String str,ArrayList<Integer> arr){
        this.labels.add(str);
        this.imageArray.add(img);
        this.histogram.add(arr);


    }
    public TrainingData(ArrayList<BufferedImage> imageArrayList, ArrayList<String> labels, ArrayList<ArrayList<Integer>> hist){
        this.histogram=hist;
        this.labels=labels;
        this.imageArray=imageArrayList;
    }
}
