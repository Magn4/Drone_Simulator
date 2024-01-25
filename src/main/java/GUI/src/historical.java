package GUI.src;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class historical extends JPanel implements ActionListener{
	
	private JButton historicalB;
	private JLabel historicalL;
	private JPanel historicalP;
	
	historical(){
		
		historicalP = new JPanel();
		historicalP.setBounds(0, 450, 200, 70);
		historicalP.setBackground(new Color(54, 33, 89));

		
		
		historicalL = new JLabel();
		historicalL.setText("Historical analysis");
		historicalL.setForeground(Color.WHITE);

		
		ImageIcon icon4 = new ImageIcon("src/main/java/GUI/src/Resources/kalender.png");
		historicalB = new JButton();
		//historical.setBounds(0, 0, 0, 0);
		historicalB.setFocusable(false);

		historicalB.setIcon(icon4);
		historicalB.addActionListener(this);
		historicalB.setBackground(new Color(54, 33, 89));
        historicalB.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar


		
		historicalP.add(historicalB);
		historicalP.add(historicalL);
		//historicalP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("historicaled button clicked ! ");
		
	}

	public JPanel getHistoricalPanel() {
		return historicalP;
	}


}


