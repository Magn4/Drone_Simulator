package GUI.Dron.src; 

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class back extends JButton implements ActionListener{
	
	JButton back;
	
	
	back(){
		
		ImageIcon icon1 = new ImageIcon("C:/Users/Nisri/Downloads/UAS.3Semester/Object-Oriented Programming in Java/Java lernen/Dron/src/pfeil-links.png");
		back = new JButton();
		back.setBounds(0, 0, 50, 50);
		back.setIcon(icon1);
		back.addActionListener(this);
		back.setBackground(new Color(90, 120, 220));
        back.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar



	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Back Button clicked ! ");
		
	}

	public JButton getBackButton() {
		return back;
	}
	

}