package GUI.src.MainPages;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class move extends JButton implements ActionListener{
	
	JButton move ;
	
	
	move(){
		
		ImageIcon icon2 = new ImageIcon("src/main/java/GUI/src/Resources/pfeil-rechts.png");
		move = new JButton();
		move.setBounds(51, 0, 50, 50);
		move.setIcon(icon2);
		move.addActionListener(this);
		move.setBackground(new Color(54, 33, 89));
        move.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar



		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("move on button clicked ! ");
		
	}


	public JButton getMoveButton() {
		return move ;
	}
	
	
	
	

}
