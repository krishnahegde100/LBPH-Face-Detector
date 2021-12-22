package lbp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public  class Lbp {

    public static String getBinaryString(int threshold,int value){
        if(value>threshold){
            return Integer.toString(1);
        }
        else
            return Integer.toString(0);
    }

    public static int[] getImageSize(Image img){
        int[] out=new int[2];
        if(img==null){
            return out;
        }
        out[0]=img.getHeight(null);
        out[1]=img.getWidth(null);

        return out;

    }

    public static ArrayList<ArrayList<Integer>> GetPixels(BufferedImage img){
        ArrayList<ArrayList<Integer>> pixels=new ArrayList<ArrayList<Integer>>();

        int[] arr=getImageSize(img);

        for(int x=0;x < arr[1];x++){
            ArrayList<Integer> row=new ArrayList<Integer>();
            for (int y=0;y<arr[0];y++){
                //get rgb values from the image
                int p = img.getRGB(x,y);
                //get single value for each pixel
                int pixel=getPixelARGB(p);
                row.add(pixel);

            }
            pixels.add(row);
        }
        return pixels;
    }

    public static int getPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        //get one value->from rgb
        int sum= (int) (alpha + red*(.299) + green*(.587) + blue*(.114));
        return sum;
    }

    public static ArrayList<ArrayList<Integer>> Calculate(BufferedImage img,int rad,int neigh){
        //pixel 2d arraylist
        ArrayList<ArrayList<Integer>> lbpPixels=new ArrayList<ArrayList<Integer>>();
        if(img==null){
            return null;
        }
        if(rad<=0||neigh<=0){
            return null;
        }

        ArrayList<ArrayList<Integer>> pixels= GetPixels(img);
        int[] out=getImageSize(img);

        for(int x=1;x<out[1]-1;x++){
            ArrayList<Integer> curRow=new ArrayList<Integer>();
            for(int y=0;y<out[0]-1;y++){
                int threshold=pixels.get(x).get(y);
                String binResult="";

                //traverse to create decimal values
                for(int tempX=x-1;tempX<=x+1;tempX++){
                    for (int tempY=y-1;tempY<=y+1;tempY++){
                        if(tempX!=x||tempY!=y){
                            //set value to 1 or 0 based on threshold
                            binResult+=getBinaryString(threshold,pixels.get(x).get(y));
                        }
                    }
                }
                int decimalValue = Integer.parseInt(binResult, 2);
                curRow.add(decimalValue);
            }
            lbpPixels.add(curRow);
        }
        return lbpPixels;
    }
}
