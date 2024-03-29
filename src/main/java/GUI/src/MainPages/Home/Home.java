package GUI.src.MainPages.Home;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;   
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import API.Fetcher.APIFetcher;
import API.Fetcher.FileWriterUtil;
import API.Fetcher.URL_Maker;
import API.Formatter.JsonFormatter;
import API.Formatter.countFinder;
import API.Formatter.Drones.DroneType;
import GUI.src.MainPages.Refresh.refresh;
import GUI.src.MainPages.aLogin.Login;
import GUI.src.MainPages.Catalogue.catalogueButton;
import GUI.src.MainPages.FlightDynamics.flightDynamicsButton;
import GUI.src.MainPages.Historical.historicalButton;
import GUI.src.MainPages.FlightDynamics.flightDynamics;

import java.util.List;


/**
 *
 * @author Nisrine 
 * The GUI
 * @author Mohamed
 * Implementation of The API
 */

public class Home {

	private static JFrame frame;
	private static String Token = Login.getToken();
	// This retrieves the number of drones for a more Dynamic Use.
	private static int count = countFinder.getCount("Drones");
	private static int count1 = countFinder.getCount("Drone Types");

	
	public static void main() {
		SwingUtilities.invokeLater(() -> {
			
			frame = new JFrame("Home");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1920, 1080);
		
			
			
			// Create main panel :
			JPanel mainPanel = createMainPanel();
			
			// Create JScrollPane  :
			JScrollPane scrollPane = new JScrollPane(mainPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            
            // add  JScrollPane 
            frame.add(scrollPane);

            frame.setVisible(true);
            
		});
	}

	
	private static JPanel createMainPanel() {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new java.awt.Dimension(1500, 2000)); 

		
	
		refresh refreshButton = new refresh();
		homeButton startButton = new homeButton();
		catalogueButton catalogButton = new catalogueButton();
		flightDynamicsButton dynamicsButton = new flightDynamicsButton();
		historicalButton historicalButton = new historicalButton();

		
		
		
		JPanel sidePanel = new JPanel() ;
		sidePanel.setBackground(new Color(22, 72, 99));
		sidePanel.setBounds(0, 80, 220, 2000);
		
		
		JPanel topPanel = new JPanel() ;
		topPanel.setBackground(new Color(22, 72, 99));
		topPanel.setBounds(0, 0, 2000, 100);
		
		
		ImageIcon icon = new ImageIcon("src/test/java/GUI/Dron/src/big-drone.png");
		

		// String Token = "Token 6ffe7e815e07b6ede78cade7617454eeb944d168";
		
		String URL = URL_Maker.getUrlExtension("Drone Type", 0, count);
		APIFetcher apiFetcher = new APIFetcher();

		String result = apiFetcher.FetchAPI(URL, Token);
		int responseCode = apiFetcher.getResponseCode();
        System.out.println(responseCode);

        String responseCodeString = String.valueOf(responseCode);
        FileWriterUtil.writeToFile(responseCodeString, "response.md");
		
		List<DroneType> droneType1List = JsonFormatter.ReadDroneList(1, result);
		// List<Drone> droneType2List = JsonFormatter.ReadDroneList(2, result2);
		// List<DroneDynamics> droneType3List = JsonFormatter.ReadDroneList(3, result3);



		int n = count1;
		int m = n % 3;
		n = n/3;

		int y = 200;
		int j = 1;
		for(int i=1 ; i<= n; i++) {
			int x = 300;
			
			for(int z=0 ; z<=2 ; z++) {

				// The HTML implementation was made with the help of AI.

				JLabel label = new JLabel("<html>ID: " + "\n" + droneType1List.get(j-1).getId() + 
				"<br>" +
				"<br>Weight : " +  droneType1List.get(j-1).getManufacturer() +
				"<br>" +
				"<br>Manufacturer: " +  droneType1List.get(j-1).getBattery_capacity() +
				"<br>" +
				"<br>Power Efficiency :" + String.format("%.2f", batteryToSpeedRatio) + 
				"</html>"
				);

				label.setForeground(Color.WHITE);
				
				label.setIcon(icon);
				
				label.setBounds(x, y, 300, 300);			
				label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
				label.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
				
				JPanel droneP = new JPanel();
				
				droneP.setBackground(new Color(66, 125, 157));
				//droneP.setBackground(new Color(86, 145, 177));
				
				droneP.setBounds(x , y , 300, 150);
				droneP.add(label);
				mainPanel.add(droneP);
				x += 400;
				j++;
				}
				x = 300;
				y+= 200;

			}
			if (m != 0) {
				int x = 300;
				for(int z=0 ; z<m ; z++) {

					// The HTML implementation was made with the help of AI.

					JLabel label = new JLabel("<html>ID: " + "\n" + droneType1List.get(j-1).getId() + 
					"<br>" +
					"<br>Weight : " +  droneType1List.get(j-1).getManufacturer() +
					"<br>" +
					"<br>Manufacturer: " +  droneType1List.get(j-1).getBattery_capacity() +
					"<br>" +
					"<br>Power Efficiency :" + String.format("%.2f", batteryToSpeedRatio) + 
					"</html>"
					);
					label.setForeground(Color.WHITE);
					//label.setFont(new Font("Arial", Font.PLAIN, 16));
	
					label.setIcon(icon);
				
					label.setBounds(x, y, 300, 300);			
					label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
					label.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon
					
					JPanel droneP = new JPanel();
	
					droneP.setBackground(new Color(66, 125, 157));
				
					droneP.setBounds(x , y , 300, 150);
					droneP.add(label);
					mainPanel.add(droneP);
					x += 400;
					j++;
					}
		}
	
		
		mainPanel.add(refreshButton.getRefreshButton());
		mainPanel.add(startButton.getStartButton());
		mainPanel.add(catalogButton.getCatalogButton());
		mainPanel.add(dynamicsButton.getdynamicsButton());
		mainPanel.add(historicalButton.getHistoricalButton());
		mainPanel.add(topPanel);
		mainPanel.add(sidePanel);
		//mainPanel.add(refreshButton.getRefreshButton());
		
		return mainPanel ;
		
	}

	public static void refreshPage() {
        System.out.println("Refreshing Home page...");
		Home.main();
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



	public static void dispose() {
		if(frame != null){
			frame.dispose();
		}
	}

}

