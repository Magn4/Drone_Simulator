package GUI.Dron.src; 

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class start extends JPanel implements ActionListener{
	
	private JButton startB;
	private JLabel startL;
	private JPanel startP;
	
	start(){
		
		startP = new JPanel();
		startP.setBounds(0, 150, 200, 70);
		startP.setBackground(new Color(90, 120, 220));

		
		
		startL = new JLabel();
		startL.setText("Home               ");
		startL.setForeground(Color.WHITE);

		
		ImageIcon icon4 = new ImageIcon("C:/Users/Nisri/Downloads/UAS.3Semester/Object-Oriented Programming in Java/Java lernen/Dron/src/home.png");
		startB = new JButton();
		//startB.setBounds(50, 50, 50, 50);
		startB.setFocusable(false);
		startB.setIcon(icon4);
		startB.addActionListener(this);
		startB.setBackground(new Color(90, 120, 220));
        startB.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar


		
		
		startP.add(startB);
		startP.add(startL);
		//startP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("started button clicked ! ");
		
	}

	public JPanel getStartPanel() {
		return startP;
	}


}




	
	

	
	


