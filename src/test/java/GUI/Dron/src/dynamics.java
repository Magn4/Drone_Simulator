import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class dynamics extends JPanel implements ActionListener{
	
	private JButton dynamicsB;
	private JLabel dynamicsL;
	private JPanel dynamicsP;
	
	dynamics(){
		
		dynamicsP = new JPanel();
		dynamicsP.setBounds(0, 350, 200, 70);
		dynamicsP.setBackground(new Color(54, 33, 89));

		
		
		dynamicsL = new JLabel();
		dynamicsL.setText("Flight dynamics    ");
		dynamicsL.setForeground(Color.WHITE);

		
		ImageIcon icon4 = new ImageIcon("C:/Users/Nisri/Downloads/UAS.3Semester/Object-Oriented Programming in Java/Java lernen/Dron/src/drone.png");
		dynamicsB = new JButton();
		//dynamics.setBounds(0, 0, 0, 0);
		dynamicsB.setFocusable(false);

		dynamicsB.setIcon(icon4);
		dynamicsB.addActionListener(this);
		dynamicsB.setBackground(new Color(54, 33, 89));
        dynamicsB.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar

		
		dynamicsP.add(dynamicsB);
		dynamicsP.add(dynamicsL);
		//dynamicsP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("dynamics button clicked ! ");
		
	}

	public JPanel getDynamicsPanel() {
		return dynamicsP;
	}


}

