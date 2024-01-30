package GUI.src.MainPages;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;


public class dynamics extends JButton implements ActionListener{
	
	private JButton dynamicsButton;
	
	dynamics(){		
		ImageIcon icon6 = new ImageIcon("src/main/java/GUI/src/Resources/drone.png");
		dynamicsButton = new JButton();
		dynamicsButton.setBounds(0, 350, 200, 50);

		dynamicsButton.setFocusable(false);
		dynamicsButton.setIcon(icon6);
		dynamicsButton.setText("  Flight Dynamics   ");
		dynamicsButton.setForeground(Color.WHITE);
		dynamicsButton.setHorizontalTextPosition(JButton.RIGHT);
		dynamicsButton.setIconTextGap(20);;
		dynamicsButton.addActionListener(this);
		dynamicsButton.setBackground(new Color(54, 33, 89));
        dynamicsButton.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("started button clicked ! ");
		flightDynamics.main(null);


		new Thread(() -> {
	   try {
		   // Warte für 3 Sekunden (3000 Millisekunden)
		   Thread.sleep(1000);
	   } catch (InterruptedException ex) {
		   ex.printStackTrace();
	   }

	   Home.dispose();


   }).start();

	}

	public JButton getdynamicsButton() {
		return dynamicsButton;
	}


}
