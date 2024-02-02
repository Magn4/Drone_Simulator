package GUI.src.MainPages.Refresh;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Nisrine 
 * 
 */
public class refresh extends JButton implements ActionListener{
	
	public JButton refresh;
	
	public refresh(){
		
		// ImageIcon icon3 = new ImageIcon("src/main/java/GUI/src/Resources/refresh.png");
		String imagePath = "/GUI/src/Resources/refresh.png";
		java.net.URL imgURL = getClass().getResource(imagePath);
		ImageIcon icon3 = new ImageIcon(imgURL);
		refresh = new JButton();
		refresh.setBounds(1450, 10, 50, 50);
		refresh.setIcon(icon3);
		refresh.addActionListener(this);
		refresh.setBackground(new Color(22, 72, 99));
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
