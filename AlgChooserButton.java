import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  Button for choosing which algorithm to run.
 */
public class AlgChooserButton {
    private JFrame mainFrame;
    private JButton nearestNeighborButton, smallestIncreaseButton;
    private JPanel panel;
    private static int whichAlg; // Which algorithm is chosen.TODO: change this to an Enum 
                            // 1 is insert nearest
                            // 2 is insert smallest
    
    public AlgChooserButton() {
        whichAlg = 0;
        mainFrame = new JFrame("Choose an Algorithm!");
        mainFrame.setSize(50, 50);
        nearestNeighborButton = new JButton("Nearest Neighbor");
        smallestIncreaseButton = new JButton("Smallest Increase");
        panel = new JPanel();
        nearestNeighborButton.setMnemonic(KeyEvent.VK_I); //Set ShortCut Keys
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Gets the screen size
        // Uses the Dimension we just made to center the jframe.
        mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, 
            dim.height/2-mainFrame.getSize().height/2);
        
        nearestNeighborButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                whichAlg = 1;
            }
        });
        
        smallestIncreaseButton.setMnemonic(KeyEvent.VK_I);
        
        smallestIncreaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                whichAlg = 2;
            }
        });
        
        panel.setLayout(new FlowLayout());
        panel.add(nearestNeighborButton);
        panel.add(smallestIncreaseButton);
        mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    // manual tester
    public static void main(String[] args) {
        AlgChooserButton application = new AlgChooserButton();
        while(true)
            System.out.println(whichAlg);
    }
    
    // Returns which algorithm is chosen
    public static int whichAlg()   {
        return whichAlg;
    }
    
    // Allows the driver class to hide the window once the buttons are no longer needed
    public void hide()   {
        mainFrame.setVisible(false);
    }
}