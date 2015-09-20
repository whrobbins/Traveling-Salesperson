import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  Button for choosing which data set to run.
 */
public class DataSetChooserButton {
    // POSSIBLE SETS TO CHOOSE
    //bier127.txt
    //mona-20k.txt
    //mona-50k.txt
    //mona-100k.txt
    //tsp5.txt
    //tsp8.txt
    //tsp100.txt
    //tsp1000.txt
    //usa13509.txt
    
    private JFrame mainFrame;
    private JPanel panel;
    private static String dataSet; // Which algorithm is chosen
    private JButton tsp5;
    private JButton tsp8;
    private JButton tsp100;
    private JButton tsp1000;
    private JButton usa13509;
    private JButton mona20k;
    private JButton mona50k;
    private JButton mona100k;
    private JButton bier127;
    
    public DataSetChooserButton() {
        dataSet = "";
        mainFrame = new JFrame("Choose a Data Set!"); // Sets up the frame, then populates buttons
        mainFrame.setSize(50, 50);
        panel = new JPanel();
        
        // Create buttons
        tsp5 = new JButton("tsp5");
        tsp8 = new JButton("tsp8");
        tsp100 = new JButton("tsp100");
        tsp1000 = new JButton("tsp1000");
        usa13509 = new JButton("usa13509");
        mona20k = new JButton("mona20k");
        mona50k = new JButton("mona50k");
        mona100k = new JButton("mona100k");
        bier127 = new JButton("bier127");
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Gets the screen size
        mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, 
            dim.height/2-mainFrame.getSize().height/2);
        
        tsp5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataSet = "tsp5.txt";
            }
        });
        
        tsp8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataSet = "tsp8.txt";
            }
        });
        
        tsp100.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataSet = "tsp100.txt";
            }
        });
        
        tsp1000.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataSet = "tsp1000.txt";
            }
        });
        
        usa13509.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataSet = "usa13509.txt";
            }
        });
        
        mona20k.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataSet = "mona-20k.txt";
            }
        });
        
        mona50k.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataSet = "mona-50k.txt";
            }
        });
        
        mona100k.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataSet = "mona-100k.txt";
            }
        });
        
        bier127.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataSet = "bier127.txt";
            }
        });
        
        // Adds the buttons and configures the panel within the frame
        panel.setLayout(new FlowLayout());
        panel.add(tsp5);
        panel.add(tsp8);
        panel.add(tsp100);
        panel.add(tsp1000);
        panel.add(usa13509);
        panel.add(mona20k);
        panel.add(mona50k);
        panel.add(mona100k);
        panel.add(bier127);
        mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
      }
    
    // Poorly designed testing method
    public static void main(String[] args) {
        DataSetChooserButton b = new DataSetChooserButton();
        while(true)
            System.out.println(dataSet);
    }
    
    // Returns which data set is chosen
    public static String getDataSet()   {
        return dataSet;
    }
    
    // Allows the driver class to hide the window once the buttons are no longer needed
    public void hide()   {
        mainFrame.setVisible(false);
    }
}