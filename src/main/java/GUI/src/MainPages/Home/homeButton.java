package GUI.src.MainPages.Home;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import GUI.src.MainPages.FlightDynamics.flightDynamics;


public class homeButton extends JButton implements ActionListener{
	
	private JButton startButton;
	
	public homeButton(){		
		// ImageIcon icon4 = new ImageIcon("src/main/java/GUI/src/Resources/home.png");
		String imagePath = "/GUI/src/Resources/home.png";
		java.net.URL imgURL = getClass().getResource(imagePath);
		ImageIcon icon4 = new ImageIcon(imgURL);
		startButton = new JButton();
		startButton.setBounds(0, 150, 200, 50);
		startButton.setFocusable(false);
		startButton.setIcon(icon4);
		startButton.setText("  Drone Dashboard ");
		startButton.setForeground(Color.WHITE);
		startButton.setHorizontalTextPosition(JButton.RIGHT);
		startButton.setIconTextGap(20);;
		startButton.addActionListener(this);
		startButton.setBackground(new Color(22, 72, 99));
        startButton.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("started button clicked ! ");
		Home.main();

		new Thread(() -> extracted()).start();

	}

	/**
	 * 
	 */
	private void extracted() {
		try {
			   // Warte für 3 Sekunden (3000 Millisekunden)
			   Thread.sleep(1000);
		   } catch (InterruptedException ex) {
			   ex.printStackTrace();
		   }
		flightDynamics.dispose();
	}

	public JButton getStartButton() {
		return startButton;
	}


}




	
	

	
	


