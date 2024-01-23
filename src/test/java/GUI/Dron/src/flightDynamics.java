import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;   
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;



public class flightDynamics {
	
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
        mainPanel.setPreferredSize(new java.awt.Dimension(480, 5000)); 

		
		
		back backB = new back();
		move moveB = new move();
		refresh refreshB = new refresh();
		start startP = new start();
		catalog catalogP = new catalog();
		dynamics dynamicsP = new dynamics();
		historical historicalP = new historical();
		
		
		JPanel sidePanel = new JPanel() ;
		sidePanel.setBackground(new Color(54, 33, 89));
		sidePanel.setBounds(0, 80, 200, 5000);
		
		
		JPanel topPanel = new JPanel() ;
		topPanel.setBackground(new Color(54, 33, 89));
		topPanel.setBounds(0, 0, 1300, 80);
		
		// Panel von tabelle :
		/*JLabel label8 = new JLabel("INFO : Drone 8");
		label8.setBounds(100, 50, 300, 300);
		label8.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER, RIGHT of image icon
		label8.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, CENTER,BOTTOM of image icon*/
		
		JPanel tableP = new JPanel();
		tableP.setBackground(new Color(154, 133, 189));
		tableP.setBounds(270, 200, 900, 5000);

		/*String[] columns = {"ID", "Timestamp", "Drone", "Speed", "Alignment Roll", "Control Range", "Alignment Yaw", "Longitude", "Latitude", "Battery Status", "Last Seen", "Status"};
		Object[][] data = 
			{
					{"86832", "Dec. 26, 2023, 6:32 p.m.", "Drone: PoD8-2031-B53F1D (created: 2023-12-27 08:07:00.701950+00:00)", "0", "0.00", "0.00", "42.00", "8.678137129", "50.107668121", "0", "Dec. 26, 2023, 6:07 p.m.", "OF"},
			};*/
		
		
		String[] columns = {"ID", "Timestamp", "Drone", "Speed", "Alignment Roll", "Control Range", "Alignment Yaw", "Longitude", "Latitude", "Battery Status", "Last Seen", "Status"};

		Object[][] data = new Object[36030][columns.length];

		for (int i = 0; i < 36030; i++) {
		    data[i] = new Object[]{
		    		String.valueOf(i + 1), 
		            "Dec. 26, 2023, 6:32 p.m.", "Drone: PoD8-2031-B53F1D (created: 2023-12-27 08:07:00.701950+00:00)",
		            "0", "0.00", "0.00", "42.00", "8.678137129", "50.107668121", "0", "Dec. 26, 2023, 6:07 p.m.", "OF"
		    };
		}

		
		JTable table = new JTable(data, columns);
		//table.setRowSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(900, 2000));
		tableP.add(scroll);
		
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
		mainPanel.add(backB.getBackButton());
		mainPanel.add(moveB.getMoveButton());
		mainPanel.add(refreshB.getRefreshButton());
		mainPanel.add(startP.getStartPanel());
		mainPanel.add(catalogP.getCatalogPanel());
		mainPanel.add(dynamicsP.getDynamicsPanel());
		mainPanel.add(historicalP.getHistoricalPanel());
		mainPanel.add(topPanel);
		mainPanel.add(sidePanel);
		mainPanel.add(tableP);
		return mainPanel ;

		
	}

}

