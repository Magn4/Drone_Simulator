package Code.GUI.Demo;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout; 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SimpleGUI implements ActionListener {

    int count = 0;
    private JLabel label;
    private JPanel panel;
    private JFrame frame;
    public SimpleGUI() {

        frame = new JFrame();
        
        JButton button = new JButton("Click me!");
        button.addActionListener(this);
        label = new JLabel("Number of Clicks: 0");
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack(); 
        frame.setVisible(true);

    }


        public static void main(String[] args) {
        new SimpleGUI();

        }


        public void actionPerformed(ActionEvent e) {
            count++;
            label.setText("Number of clicks: " + count);
        }   
}