package Histogram ;
import MathLib.MathLib;
import Metric.Metric;
import java.util.*;


public class Histogram
{
    public static ArrayList<Integer> Calculate(ArrayList<ArrayList<Integer>> pixels, int gridX, int gridY)
    {
        ArrayList<Integer> hist = new ArrayList<Integer>();
        int rows,cols ;
        int gridWidth, gridHeight ;
        int gX,gY ;
        int startPosX, startPosY , endPosX, endPosY ;

        int[] regionHistogram;
        int x,y ;
        // Check the pixels 'matrix'
        if(pixels.size()== 0)
        {
            System.out.println("The pixels slice passed to the GetHistogram function is empty") ;
            return hist ;
        }

        // Get the 'matrix' dimensions
        rows = pixels.size();
        cols = pixels.get(0).size() ;

        // Check the grid (X and Y)
        if(gridX <= 0 || gridX >= cols)
        {
            System.out.println("Invalid grid X passed to the GetHistogram function") ;
            return hist;
        }
        if(gridY <= 0 || gridX >= rows)
        {
            System.out.println("Invalid grid Y passed to the GetHistogram function");
            return hist;
        }

        // Get the size (width and height) of each region
        gridWidth = cols / gridX ;
        gridHeight = rows / gridY ;

        // Calculates the histogram of each grid
        for(gX = 0; gX < gridX; gX++)
        {
            for(gY = 0; gY < gridY; gY++)
            {
                // Create a slice with empty 256 positions
                regionHistogram = new int[256] ;

                // Define the start and end positions for the following loop
                startPosX = gX * gridWidth;
                startPosY = gY * gridHeight;
                endPosX = (gX + 1) * gridWidth;
                endPosY = (gY + 1) * gridHeight;

                // Make sure that no pixel has been leave at the end
                if(gX == gridX-1)
                {
                    endPosX = cols ;
                }
                if(gY == gridY-1)
                {
                    endPosY = rows ;
                }

                // Creates the histogram for the current region
                for(x = startPosX; x < endPosX; x++)
                {
                    for(y = startPosY; y < endPosY; y++)
                    {
                        // Make sure we are trying to access a valid position
                        if(x < pixels.size())
                        {
                            if(y < pixels.get(x).size())
                            {
                                if((pixels.get(x).get(y)) < regionHistogram.length)
                                {
                                    regionHistogram[pixels.get(x).get(y)] += 1 ;
                                }
                            }
                        }
                    }
                }
                // Concatenate two slices
                for(int i=0;i<regionHistogram.length;i++){
                    hist.add(regionHistogram[i]);
                }

            }
        }

        return hist ;
    }
    public static int Compare(ArrayList<Integer> hist1, ArrayList<Integer> hist2, String selectedMetric)
    {


        if (Metric.ChiSquare.equals(selectedMetric)) {
            return MathLib.ChiSquare(hist1, hist2);
        } else if (Metric.EuclideanDistance.equals(selectedMetric)) {
            return MathLib.EuclideanDistance(hist1, hist2);
        } else if (Metric.NormalizedEuclideanDistance.equals(selectedMetric)) {
            return MathLib.NormalizedEuclideanDistance(hist1, hist2);
        } else if (Metric.AbsoluteValue.equals(selectedMetric)) {
            return MathLib.AbsoluteValue(hist1, hist2);
        }
        System.out.println("Invalid metric selected to compare the histograms") ;
        return 0 ;
    }



}
