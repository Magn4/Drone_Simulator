package GUI.src.MainPages.Refresh;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class update extends JButton implements ActionListener{
	
	JButton update;
	
	update(){
		
		ImageIcon icon3 = new ImageIcon("src/main/java/GUI/src/Resources/refresh.png");
		update = new JButton();
		update.setBounds(1450, 10, 50, 50);
		update.setIcon(icon3);
		update.addActionListener(this);
		update.setBackground(new Color(22, 72, 99));
        update.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar



	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("updated button clicked ! ");
		flightDynamics.dispose();
		flightDynamics.main(null);
		
		
	}

	public JButton getupdateButton() {
		return update;
	}
	

	
	

}
