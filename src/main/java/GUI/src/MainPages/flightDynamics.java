package GUI.src.MainPages;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import Formatter.Drones.Drone;
import Formatter.Drones.DroneDynamics;
import API.Fetcher.APIFetcher;
import API.Fetcher.FileWriterUtil;
import Formatter.JsonFormatter;
import API.Fetcher.URL_Maker;

public class flightDynamics {
    private static JFrame frame;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Flight Dynamics");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1500, 1500);

            // Create main panel:
            JPanel mainPanel = createMainPanel();

            // Create JScrollPane:
            JScrollPane scrollPane = new JScrollPane(mainPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            // Add JScrollPane
            frame.add(scrollPane);

            frame.setVisible(true);
        });
    }

    private static JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(480, 800));

        // Create buttons and panels
     

		back backB = new back();
		move moveB = new move();
		refresh refreshB = new refresh();
		start startP = new start();
		catalog catalogP = new catalog();
		dynamics dynamicsP = new dynamics();
		historical historicalP = new historical();

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(54, 33, 89));
        sidePanel.setBounds(0, 80, 200, 800);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(54, 33, 89));
        topPanel.setBounds(0, 0, 2000, 80);

        JPanel tableP = createTablePanel();

        // Add components to mainPanel
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

        return mainPanel;
    }

    private static JPanel createTablePanel() {
        JPanel tableP = new JPanel();
        tableP.setBackground(new Color(154, 133, 189));
        tableP.setBounds(270, 200, 900, 500);


        String URL1 = URL_Maker.getUrlExtension("Drone Dynamics", 0, 25);
        String result = APIFetcher.FetchAPI(URL1);
        
        FileWriterUtil.writeToFile("Data is being Fetched from: " + URL1, "URLs.md");


        List<DroneDynamics> droneTypeList = JsonFormatter.ReadDroneList(3, result);

        String[] columns = {"ID", "Timestamp", "Speed", "Alignment Roll", "Alignment Yaw", "Longitude", "Latitude", "Battery Status", "Last Seen", "Status"};
        Object[][] data = new Object[25][columns.length];

        for (int i = 0; i < 25; i++) {
            String URL = droneTypeList.get(i).getDrone();
            String result2 = APIFetcher.FetchAPI(URL);
            List<Drone> droneTypeList2 = JsonFormatter.ReadDroneList(4, result2);
            int ID = droneTypeList2.get(0).getId();
			System.out.println("• ");

            data[i] = new Object[]{
                    ID,
                    droneTypeList.get(i).getTimestamp(), droneTypeList.get(i).getSpeed(),
                    droneTypeList.get(i).getAlign_roll(), droneTypeList.get(i).getAlign_yaw(), droneTypeList.get(i).getLongitude(),
                    droneTypeList.get(i).getLatitude(), droneTypeList.get(i).getBattery_status(), droneTypeList.get(i).getLast_seen(),
                    droneTypeList.get(i).getStatus()
            };
        }

        JTable table = new JTable(data, columns);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(900, 2000));
        tableP.add(scroll);

        return tableP;
    }

    public static void dispose() {
		if(frame != null){
			frame.dispose();
		}
    }
}
