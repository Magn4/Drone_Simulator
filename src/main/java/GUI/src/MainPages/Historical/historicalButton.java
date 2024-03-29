package GUI.src.MainPages.Historical;


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

public class historicalButton extends JButton implements ActionListener{
	
	public JButton historicalButton;
	
	public historicalButton(){		
		//ImageIcon icon7 = new ImageIcon("src/main/java/GUI/src/Resources/kalender.png");
		String imagePath = "/GUI/src/Resources/kalender.png";
		java.net.URL imgURL = getClass().getResource(imagePath);
		ImageIcon icon7 = new ImageIcon(imgURL);
		historicalButton = new JButton();
		historicalButton.setBounds(0, 450, 200, 50);
		historicalButton.setFocusable(false);
		historicalButton.setIcon(icon7);
		historicalButton.setText("Historical Analysis ");
		historicalButton.setForeground(Color.WHITE);
		historicalButton.setHorizontalTextPosition(JButton.RIGHT);
		historicalButton.setIconTextGap(20);;
		historicalButton.addActionListener(this);
		historicalButton.setBackground(new Color(22, 72, 99));
        historicalButton.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("historical button clicked ! ");
		historicalAnalysis.main(null);


		new Thread(() -> {
	   try {
		   // Warte für 3 Sekunden (3000 Millisekunden)
		   Thread.sleep(1000);
	   } catch (InterruptedException ex) {
		   ex.printStackTrace();
	   }

	   flightDynamics.dispose();
	   Home.dispose();


   }).start();

	}

	public JButton getHistoricalButton() {
		return historicalButton;
	}


}


