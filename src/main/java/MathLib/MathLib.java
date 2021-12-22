package MathLib;

import java.util.ArrayList;
import java.lang.Math;


 public class MathLib{

    public  static int  checkHistograms(ArrayList<Integer> hist1, ArrayList<Integer>hist2) {
        if (hist1.size() == 0 || hist2.size() == 0 ){
            return 0;
        }
        if (hist1.size() != hist2.size()) {
            return 0;
        }
        return 1;
    }

    public  static int  ChiSquare(ArrayList<Integer> hist1, ArrayList<Integer>hist2) {

        // Check the histogram sizes
        int err =  checkHistograms(hist1, hist2);
        if ( err == 0){
            return 0;
        }

        int index;
        int sum = 0;
        int numerator=1;
        int denominator=1;
        for (index = 0; index < hist1.size()-1; index++ ){
            numerator = (int)Math.pow(hist1.get(index) - hist2.get(index), 2);
            denominator = hist1.get(index);
            sum += numerator / denominator;
        }
        return sum;
    }

    public static int EuclideanDistance(ArrayList<Integer> hist1, ArrayList<Integer>hist2) {

        // Check the histogram sizes
        int err = checkHistograms(hist1, hist2);
        if ( err == 0){
            return 0;
        }

        int index;
        int sum=0;
        for (index = 0; index < hist1.size(); index++ ){
            sum += Math.pow(hist1.get(index) - hist2.get(index), 2);
        }
        return (int) Math.sqrt(sum);
    }

    public static int NormalizedEuclideanDistance(ArrayList<Integer> hist1, ArrayList<Integer>hist2) {

        // Check the histogram sizes
        int err = checkHistograms(hist1, hist2);
        if ( err == 0){
            return 0;
        }

        int index;
        int sum=0;
        int n=hist1.size();
        for (index = 0; index < hist1.size(); index++ ){
            sum += Math.pow(hist1.get(index) - hist2.get(index), 2) / n;
        }
        return (int) Math.sqrt(sum);
    }

    public static int AbsoluteValue(ArrayList<Integer> hist1, ArrayList<Integer>hist2) {

        // Check the histogram sizes
        int err = checkHistograms(hist1, hist2);
        if ( err == 0){
            return 0;
        }

        int index;
        int sum=0;
        for (index = 0; index < hist1.size(); index++  ){
            sum += Math.abs(hist1.get(index) - hist2.get(index));
        }
        return sum;
    }
}
