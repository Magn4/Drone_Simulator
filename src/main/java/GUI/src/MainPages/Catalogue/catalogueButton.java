package GUI.src.MainPages.Catalogue;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import GUI.src.MainPages.FlightDynamics.flightDynamics;
import GUI.src.MainPages.Home.Home;

/**
 *
 * @author Nisrine 
 */
public class catalogueButton extends JButton implements ActionListener{
	
	public static JButton catalogButton;
	
	public catalogueButton(){		
		// ImageIcon icon5 = new ImageIcon("src/main/java/GUI/src/Resources/list.png");
		String imagePath = "/GUI/src/Resources/list.png";
		java.net.URL imgURL = getClass().getResource(imagePath);
		ImageIcon icon5 = new ImageIcon(imgURL);
		catalogButton = new JButton();
		catalogButton.setBounds(0, 250, 200, 50);
		catalogButton.setFocusable(false);
		catalogButton.setIcon(icon5);
		catalogButton.setText("  Drone  Catalog    ");
		catalogButton.setForeground(Color.WHITE);
		catalogButton.setHorizontalTextPosition(JButton.RIGHT);
		catalogButton.setIconTextGap(20);;
		catalogButton.addActionListener(this);
		catalogButton.setBackground(new Color(22, 72, 99));
        catalogButton.setBorderPainted(false); 

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("started button clicked ! ");
		Catalogue.main(null);


		new Thread(() -> {
	   try {
		   Thread.sleep(1000);
	   } catch (InterruptedException ex) {
		   ex.printStackTrace();
	   }

	   flightDynamics.dispose();
	   Home.dispose();

   }).start();

	}
	public JButton getCatalogButton() {
		return catalogButton;
	}


}
