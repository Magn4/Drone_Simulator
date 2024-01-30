package GUI.src.MainPages;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;


public class catalog extends JButton implements ActionListener{
	
	private JButton catalogButton;
	
	catalog(){		
		ImageIcon icon5 = new ImageIcon("src/main/java/GUI/src/Resources/list.png");
		catalogButton = new JButton();
		catalogButton.setBounds(0, 250, 200, 50);
		catalogButton.setFocusable(false);
		catalogButton.setIcon(icon5);
		catalogButton.setText("  Drone  Catalog    ");
		catalogButton.setForeground(Color.WHITE);
		catalogButton.setHorizontalTextPosition(JButton.RIGHT);
		catalogButton.setIconTextGap(20);;
		catalogButton.addActionListener(this);
		catalogButton.setBackground(new Color(54, 33, 89));
        catalogButton.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("started button clicked ! ");
		Catalogue.main(null);


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

	public JButton getCatalogButton() {
		return catalogButton;
	}


}


			
			
			
			
			
			
			
			
			
			
			
			
