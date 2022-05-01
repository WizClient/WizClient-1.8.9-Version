package test;


import java.awt. *;
import javax.swing. *;
 
public class Testing extends JFrame {
Container c; // container of this frame  
JLabel TextLabel; // Label that should appear in the frame 
public Testing() {// constructor
 c = getContentPane(); // determine container
 c.setLayout (new FlowLayout ()); // set layout
 // Create the label object with the transfer of the label text
TextLabel=new JLabel("Testing java font class");
 TextLabel.setFont(new Font("Comic Sans MS",Font.BOLD+Font.ITALIC,40));
TextLabel.setForeground(Color.red);
// Add the label to the frame
 c.add (TextLabel);
}
 public static void main (String [] args) {
 Testing window = new Testing();
 window.setTitle ("Java Font Class");
 window.setSize (700,200);
 window.setVisible (true);
 window.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
}
}