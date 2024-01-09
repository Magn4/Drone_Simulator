import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class refresh extends JButton implements ActionListener{
	
	JButton refresh;
	
	refresh(){
		
		ImageIcon icon3 = new ImageIcon("C:/Users/Nisri/Downloads/UAS.3Semester/Object-Oriented Programming in Java/Java lernen/Dron/src/refresh.png");
		refresh = new JButton();
		refresh.setBounds(120, 0, 50, 50);
		refresh.setIcon(icon3);
		refresh.addActionListener(this);
		refresh.setBackground(new Color(90, 120, 220));
        refresh.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar



	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("refreshed button clicked ! ");
		
	}

	public JButton getRefreshButton() {
		return refresh;
	}
	

	
	

}
