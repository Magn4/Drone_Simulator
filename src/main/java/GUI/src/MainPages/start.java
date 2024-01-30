package GUI.src.MainPages;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;


public class start extends JButton implements ActionListener{
	
	private JButton startButton;
	
	start(){		
		ImageIcon icon4 = new ImageIcon("src/main/java/GUI/src/Resources/home.png");
		startButton = new JButton();
		startButton.setBounds(0, 150, 200, 50);
		startButton.setFocusable(false);
		startButton.setIcon(icon4);
		startButton.setText("  Drone Dashboard ");
		startButton.setForeground(Color.WHITE);
		startButton.setHorizontalTextPosition(JButton.RIGHT);
		startButton.setIconTextGap(20);;
		startButton.addActionListener(this);
		startButton.setBackground(new Color(54, 33, 89));
        startButton.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("started button clicked ! ");
		Home.main(null);


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



	
	

	
	


