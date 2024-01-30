package GUI.src.MainPages;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;   
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Formatter.Drones.DroneType;
import API.Fetcher.APIFetcher;
import Formatter.JsonFormatter;
import API.Fetcher.URL_Maker;


import java.util.List;


public class Home {

	private static JFrame frame;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			
			frame = new JFrame("Home");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.setSize(1500,700);
			frame.setSize(1500, 1500);
			
			
			
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
        mainPanel.setPreferredSize(new java.awt.Dimension(480, 2000)); 

		
		
		back backButton = new back();
		move moveButton = new move();
		refresh refreshButton = new refresh();
		start startButton = new start();
		catalog catalogButton = new catalog();
		dynamics dynamicsButton = new dynamics();
		historical historicalButton = new historical();
		
		
		JPanel sidePanel = new JPanel() ;
		sidePanel.setBackground(new Color(54, 33, 89));
		sidePanel.setBounds(0, 80, 200, 2000);
		
		
		JPanel topPanel = new JPanel() ;
		topPanel.setBackground(new Color(54, 33, 89));
		topPanel.setBounds(0, 0, 2000, 80);
		
		
		ImageIcon icon = new ImageIcon("src/test/java/GUI/Dron/src/big-drone.png");
		

		String URL = URL_Maker.getUrlExtension("Drones", 0, 25);
		String result1 = APIFetcher.FetchAPI(URL);
		
		List<DroneType> droneType1List = JsonFormatter.ReadDroneList(1, result1);
		// List<Drone> droneType2List = JsonFormatter.ReadDroneList(2, result2);
		// List<DroneDynamics> droneType3List = JsonFormatter.ReadDroneList(3, result3);



		int n = 25;
		int m = n % 3;
		n = n/3;

		System.out.println("This is n:" + n);

		int y = 200;
		int j = 1;
		for(int i=1 ; i<= n; i++) {
			int x = 300;
			
			for(int z=0 ; z<=2 ; z++) {
				JLabel label = new JLabel("<html>ID: " + "\n" + droneType1List.get(j-1).getId() + 
				"<br>" +
				"<br>Weight : " +  droneType1List.get(j-1).getWeight() +
				"<br>" +
				"<br>Manufacturer: " +  droneType1List.get(j-1).getManufacturer() +
				"<br>" +
				"<br>Max Speed: " +  droneType1List.get(j-1).getMax_speed() + "</html>"
				);
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
					JLabel label = new JLabel("<html>ID: " + "\n" + droneType1List.get(j-1).getId() + 
					"<br>" +
					"<br>Weight : " +  droneType1List.get(j-1).getWeight() +
					"<br>" +
					"<br>Manufacturer: " +  droneType1List.get(j-1).getManufacturer() +
					"<br>" +
					"<br>Max Speed: " +  droneType1List.get(j-1).getMax_speed() + "</html>"
					);
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
	
		mainPanel.add(backButton.getBackButton());
		mainPanel.add(moveButton.getMoveButton());
		mainPanel.add(refreshButton.getRefreshButton());
		mainPanel.add(startButton.getStartButton());
		mainPanel.add(catalogButton.getCatalogButton());
		mainPanel.add(dynamicsButton.getdynamicsButton());
		mainPanel.add(historicalButton.gethistoricalButton());
		mainPanel.add(topPanel);
		mainPanel.add(sidePanel);
		
		return mainPanel ;
		
	}


	public static void dispose() {
		if(frame != null){
			frame.dispose();
		}
	}

}

