package GUI.src;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class back extends JButton implements ActionListener{
	
	JButton back;
	
	
	back(){
		
		ImageIcon icon1 = new ImageIcon("src/test/java/GUI/Dron/src/pfeil-links.png");
		back = new JButton();
		back.setBounds(0, 0, 50, 50);
		back.setIcon(icon1);
		back.addActionListener(this);
		back.setBackground(new Color(54, 33, 89));
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
