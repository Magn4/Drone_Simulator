
	import java.awt.Color;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.ImageIcon;
	import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

	public class catalog extends JPanel implements ActionListener{
		
		private JButton catalogB;
		private JLabel catalogL ;
		private JPanel catalogP;
		
		
		catalog(){
			
			
			catalogP = new JPanel();
			catalogP.setBounds(0, 250, 200, 70);
			catalogP.setBackground(new Color(54, 33, 89)); 
			
			
			catalogL = new JLabel();
			catalogL.setText("Drone catalog      ");
			catalogL.setForeground(Color.WHITE);
			
			
			
			ImageIcon icon5 = new ImageIcon("C:/Users/Nisri/Downloads/UAS.3Semester/Object-Oriented Programming in Java/Java lernen/Dron/src/list.png");
			catalogB = new JButton();
			catalogB.setBounds(10, 300, 50, 50);
			catalogB.setFocusable(false);

			catalogB.setIcon(icon5);
			catalogB.addActionListener(this);
			catalogB.setBackground(new Color(54, 33, 89));
	        catalogB.setBorderPainted(false); // Setze den Rand des Buttons nicht sichtbar

			
			catalogP.add(catalogB);
			catalogP.add(catalogL);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("catalog button clicked ! ");
			
		}

		public JPanel getCatalogPanel() {
			return catalogP;
		}


	}



