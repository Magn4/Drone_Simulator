package GUI.src;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class start extends JPanel implements ActionListener{
	
	private JButton startB;
	private JLabel startL;
	private JPanel startP;
	
	start(){
		
		startP = new JPanel();
		startP.setBounds(0, 150, 200, 50);
		startP.setBackground(new Color(54, 33, 89));

		
		
		startL = new JLabel();
		startL.setText("Home               ");
		startL.setForeground(Color.WHITE);

		
		ImageIcon icon4 = new ImageIcon("src/main/java/GUI/src/Resources/home.png");
		startB = new JButton();
		//startB.setBounds(50, 50, 50, 50);
		startB.setFocusable(false);
		startB.setIcon(icon4);
		startB.addActionListener(this);
		startB.setBackground(new Color(54, 33, 89));
        startB.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar


		
		
		startP.add(startB);
		startP.add(startL);
		//startP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("started button clicked ! ");
		
	}

	public JPanel getStartPanel() {
		return startP;
	}


}




	
	

	
	


