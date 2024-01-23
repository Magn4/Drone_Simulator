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

import Formatter.Drones.DroneType1;
import Formatter.Drones.DroneType2;

import java.util.ArrayList;
import java.util.List;

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
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            
            // add  JScrollPane 
            frame.add(scrollPane);

            frame.setVisible(true);
            
		});
	}

	
	private static JPanel createMainPanel() {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new java.awt.Dimension(480, 3000)); 

		
		
		back backB = new back();
		move moveB = new move();
		refresh refreshB = new refresh();
		start startP = new start();
		catalog catalogP = new catalog();
		dynamics dynamicsP = new dynamics();
		historical historicalP = new historical();
		
		
		JPanel sidePanel = new JPanel() ;
		sidePanel.setBackground(new Color(54, 33, 89));
		sidePanel.setBounds(0, 80, 200, 1600);
		
		
		JPanel topPanel = new JPanel() ;
		topPanel.setBackground(new Color(54, 33, 89));
		topPanel.setBounds(0, 0, 1300, 80);
		
		
		ImageIcon icon = new ImageIcon("src/test/java/GUI/Dron/src/big-drone.png");
		
		
		int n = 32;
		int m = n % 3;
		n = n/3;
		System.out.println("This is n:" + n);
		int y = 200;
		int j = 1;
		for(int i=1 ; i<= n; i++) {
			int x = 300;
			
			for(int z=0 ; z<=2 ; z++) {
				JLabel label = new JLabel("INFO : Drone " + j);
				label.setForeground(Color.WHITE);

				label.setIcon(icon);
			
				label.setBounds(x, y, 300, 300);			
				label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
				label.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
				
				JPanel droneP = new JPanel();

				droneP.setBackground(new Color(79, 58, 114));
			
				droneP.setBounds(x , y , 250, 150);
				droneP.add(label);
				mainPanel.add(droneP);
				x += 300;
				j++;
				}
				x = 300;
				y+= 200;
				

				
			}
			if (m != 0) {
				int x = 300;
				for(int z=0 ; z<m ; z++) {
					JLabel label = new JLabel("INFO : Drone " + j);
					label.setForeground(Color.WHITE);
	
					label.setIcon(icon);
				
					label.setBounds(x, y, 300, 300);			
					label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
					label.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
					
					JPanel droneP = new JPanel();
	
					droneP.setBackground(new Color(79, 58, 114));
				
					droneP.setBounds(x , y , 250, 150);
					droneP.add(label);
					mainPanel.add(droneP);
					x += 300;
					j++;
					}
		}
	
		mainPanel.add(backB.getBackButton());
		mainPanel.add(moveB.getMoveButton());
		mainPanel.add(refreshB.getRefreshButton());
		mainPanel.add(startP.getStartPanel());
		mainPanel.add(catalogP.getCatalogPanel());
		mainPanel.add(dynamicsP.getDynamicsPanel());
		mainPanel.add(historicalP.getHistoricalPanel());
		mainPanel.add(topPanel);
		mainPanel.add(sidePanel);
		
		return mainPanel ;

		
	}

}

