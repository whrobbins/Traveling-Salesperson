import java.io.*;
import java.util.*;
import java.awt.*;

public class TSPClientDriver 
{
    static Calendar calendar;
    private static long start;
    
    public static void main(String[] args) throws FileNotFoundException
    {   
        Tour tour = new Tour();
        
        // Chooses file automatically
        String whichFile = "";
        DataSetChooserButton dataChooser = new DataSetChooserButton();
        // Waits for input before continuing and opening file
        while(whichFile.equals("")) {
            try{Thread.sleep(500);} catch(Exception e) {}; 
            whichFile = dataChooser.getDataSet();
        }
        dataChooser.hide(); 
        
        //Open file for reading
        Scanner input = new Scanner(new File("dataFiles", whichFile));
        
        int w = input.nextInt(); 
        int h = input.nextInt(); 
        
        int whichAlgorithm = 0;
        AlgChooserButton chooser = new AlgChooserButton();
        while(whichAlgorithm == 0)  {
            try{Thread.sleep(500);} catch(Exception e) {}; // Waits for a button click
            whichAlgorithm = chooser.whichAlg(); 
        }
        chooser.hide(); 
        
        // Runs algorithm based on which was chosen
        if(whichAlgorithm == 1) { // 1 == Insert Nearest Neighbor
            start = calendar.getInstance().getTimeInMillis();

            while(input.hasNext())
            {
                tour.insertNearest(new Point(input.nextDouble(), input.nextDouble()));
            }
        }
        else    { // Else whichAlgorithm == 2 == Smallest Increase
            start = calendar.getInstance().getTimeInMillis();

            while(input.hasNext())
            {
                tour.insertSmallest(new Point(input.nextDouble(), input.nextDouble()));
            }
        }
        
        // NODE INTERCHAGE: waaay too time-expensive to justify the Tour's small
        //                  improvement.  Uncomment the following lines for fun
            //         for(int i = 0; i < 100000; i++)
            //             tour.interchange();
        
        long stop = calendar.getInstance().getTimeInMillis();
        double elapsedTime = (double) (stop - start) / 1000;
        
        
        System.out.println("Created Tour with " + tour.size() + " points.");
        System.out.println("Tour has distance " + tour.distance());
        System.out.println("Here is the tour in order of points visited");
        tour.print();
        
        // graph the points
        DrawingPanel panel = new DrawingPanel(w,h);
        Graphics g = panel.getGraphics();
        tour.draw(g);
        g.setColor(Color.RED);
        g.drawString(String.format("Tour with %,d",tour.size()) + " points",10,20);
        g.drawString(String.format("Tour distance: %,.2f",tour.distance()), 10, 40);
        g.drawString(String.format("Elapsed time: %4.2f seconds", elapsedTime), 10,60);

    }
}