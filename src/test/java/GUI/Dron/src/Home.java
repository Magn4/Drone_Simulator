package GUI.Dron.src; 
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Home {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			
			JFrame frame = new JFrame("Home");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.setSize(1500,700);
			frame.setSize(500, 500);
			
			
			
			// Create main panel :
			JPanel mainPanel = createMainPanel();
			
			// Create JScrollPane  :
			JScrollPane scrollPane = new JScrollPane(mainPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            
            // add  JScrollPane 
            frame.add(scrollPane);

            frame.setVisible(true);
            
		});
	}

	
	private static JPanel createMainPanel() {
		
		JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new java.awt.Dimension(480, 800)); 

		
		
		back backB = new back();
		move moveB = new move();
		refresh refreshB = new refresh();
		start startP = new start();
		catalog catalogP = new catalog();
		dynamics dynamicsP = new dynamics();
		historical historicalP = new historical();
		
		
		JPanel sidePanel = new JPanel() ;
		sidePanel.setBackground(new Color(90, 120, 220));
		sidePanel.setBounds(0, 80, 200, 800);
		
		
		JPanel topPanel = new JPanel() ;
		topPanel.setBackground(new Color(90, 120, 220));
		topPanel.setBounds(0, 0, 1300, 80);
		
		
		
		ImageIcon icon = new ImageIcon("C:/Users/Nisri/Downloads/UAS.3Semester/Object-Oriented Programming in Java/Java lernen/Dron/src/big-drone.png");
		
		
		// Panel of drone 1
		JLabel label1 = new JLabel("INFO : Drone 1");
		label1.setIcon(icon);
		//label.setVerticalAlignment(JLabel.TOP);
		//label.setHorizontalAlignment(JLabel.RIGHT);
		label1.setBounds(100, 50, 300, 300);
		label1.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
		label1.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon

		JPanel drone1P = new JPanel();
		drone1P.setBackground(new Color(90, 120, 220));
		drone1P.setBounds(300, 200, 200, 150);
		
		
		
		
		//Panel of drone 2
		JLabel label2 = new JLabel("INFO : Drone 2");
		label2.setIcon(icon);
		//label.setVerticalAlignment(JLabel.TOP);
		//label.setHorizontalAlignment(JLabel.RIGHT);
		label2.setBounds(100, 50, 300, 300);
		label2.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
		label2.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon

		JPanel drone2P = new JPanel();
		drone2P.setBackground(new Color(90, 120, 220));
		drone2P.setBounds(600, 200, 200, 150);


		
		
		//Panel of drone 3
		JLabel label3 = new JLabel("INFO : Drone 3");
		label3.setIcon(icon);
		//label.setVerticalAlignment(JLabel.TOP);
		//label.setHorizontalAlignment(JLabel.RIGHT);
		label3.setBounds(100, 50, 300, 300);
		label3.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
		label3.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
		
		JPanel drone3P = new JPanel();
		drone3P.setBackground(new Color(90, 120, 220));
		drone3P.setBounds(900, 200, 200, 150);
		
		
		
		
		
		//Panel of drone 4
		JLabel label4 = new JLabel("INFO : Drone 4");
		label4.setIcon(icon);
		//label.setVerticalAlignment(JLabel.TOP);
		//label.setHorizontalAlignment(JLabel.RIGHT);
		label4.setBounds(100, 50, 300, 300);
		label4.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
		label3.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
		
		JPanel drone4P = new JPanel();
		drone4P.setBackground(new Color(90, 120, 220));
		drone4P.setBounds(300, 400, 200, 150);
		
		
		
		
		//Panel of drone 5
		JLabel label5 = new JLabel("INFO : Drone 5");
		label5.setIcon(icon);
		//label.setVerticalAlignment(JLabel.TOP);
		//label.setHorizontalAlignment(JLabel.RIGHT);
		label5.setBounds(100, 50, 300, 300);
		label5.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
		label3.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
		
		JPanel drone5P = new JPanel();
		drone5P.setBackground(new Color(90, 120, 220));
		drone5P.setBounds(600, 400, 200, 150);


		
		
		
		
		//Panel of drone 6
		JLabel label6 = new JLabel("INFO : Drone 6");
		label6.setIcon(icon);
		//label.setVerticalAlignment(JLabel.TOP);
		//label.setHorizontalAlignment(JLabel.RIGHT);
		label6.setBounds(100, 50, 300, 300);
		label6.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
		label6.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
		
		JPanel drone6P = new JPanel();
		drone6P.setBackground(new Color(90, 120, 220));
		drone6P.setBounds(900, 400, 200, 150);

		
		
		
		//Panel of drone 7
		JLabel label7 = new JLabel("INFO : Drone 7");
		label7.setIcon(icon);
		//label.setVerticalAlignment(JLabel.TOP);
		//label.setHorizontalAlignment(JLabel.RIGHT);
		label7.setBounds(100, 50, 300, 300);
		label7.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
		label7.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
		
		JPanel drone7P = new JPanel();
		drone7P.setBackground(new Color(90, 120, 220));
		drone7P.setBounds(300, 600, 200, 150);
		
		
		
		
		
		
		//Panel of drone 8
		JLabel label8 = new JLabel("INFO : Drone 8");
		label8.setIcon(icon);
		//label.setVerticalAlignment(JLabel.TOP);
		//label.setHorizontalAlignment(JLabel.RIGHT);
		label8.setBounds(100, 50, 300, 300);
		label8.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
		label8.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
		
		JPanel drone8P = new JPanel();
		drone8P.setBackground(new Color(90, 120, 220));
		drone8P.setBounds(600, 600, 200, 150);


		
		
		
		
		//Panel of drone 9
		JLabel label9 = new JLabel("INFO : Drone 9");
		label9.setIcon(icon);
		//label.setVerticalAlignment(JLabel.TOP);
		//label.setHorizontalAlignment(JLabel.RIGHT);
		label9.setBounds(100, 50, 300, 300);
		label9.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
		label9.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
		
		JPanel drone9P = new JPanel();
		drone9P.setBackground(new Color(90, 120, 220));
		drone9P.setBounds(900, 900, 200, 150);

		

		

		
		
		
		
		
		
		
		
		
		

		mainPanel.add(backB.getBackButton());
		mainPanel.add(moveB.getMoveButton());
		mainPanel.add(refreshB.getRefreshButton());
		mainPanel.add(startP.getStartPanel());
		mainPanel.add(catalogP.getCatalogPanel());
		mainPanel.add(dynamicsP.getDynamicsPanel());
		mainPanel.add(historicalP.getHistoricalPanel());
		mainPanel.add(topPanel);
		mainPanel.add(sidePanel);
		drone1P.add(label1);
		mainPanel.add(drone1P);
		drone2P.add(label2);
		mainPanel.add(drone2P);
		drone3P.add(label3);
		mainPanel.add(drone3P);
		drone4P.add(label4);
		mainPanel.add(drone4P);
		drone5P.add(label5);
		mainPanel.add(drone5P);
		drone6P.add(label6);
		mainPanel.add(drone6P);
		drone7P.add(label7);
		mainPanel.add(drone7P);
		drone8P.add(label8);
		mainPanel.add(drone8P);
		drone9P.add(label9);
		mainPanel.add(drone9P);


		return mainPanel ;

		
	}

}
