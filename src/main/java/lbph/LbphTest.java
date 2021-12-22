package lbph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class LbphTest {

    public static void main(String[] args) {
        testPredict();
        String[] out=Lbph.predict("/Users/karthik/Documents/faceDetect/src/main/resources/4.png");
        for(String i:out){
            System.out.println(i);
        }
    }

    public static BufferedImage loadImage(String filename){
        try {
            File input_file = new File(filename);
            BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            img = ImageIO.read(input_file);
            return img;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;

    }

    public static void testPredict(){

        Params params=new Params(1,8,8,8);

        Lbph lbph=new Lbph();
        lbph.init(params);
        ArrayList<String> paths=new ArrayList<String>();
        paths.add("/Users/karthik/Documents/faceDetect/src/main/resources/1.png");
        paths.add("/Users/karthik/Documents/faceDetect/src/main/resources/2.png");
        paths.add("/Users/karthik/Documents/faceDetect/src/main/resources/3.png");

        ArrayList<String>labels=new ArrayList<String>();
        labels.add("face");
        labels.add("non-face");
        labels.add("face");

        ArrayList<BufferedImage> imageArrayList= new ArrayList<BufferedImage>();
        for(int i=0;i<paths.size();i++){
            imageArrayList.add(loadImage(paths.get(i)));

        }
        boolean check=lbph.trainData(imageArrayList,labels);
        if(check){
            System.out.println("Trained successfully!!");
        }
        else {
            System.out.println("Not trained!!");
        }


    }

}
