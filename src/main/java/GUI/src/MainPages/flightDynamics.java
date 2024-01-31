package GUI.src.MainPages;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    private static long startTime;
    private static long endTime;
   
    public static void main(String[] args) {

        startTime = System.currentTimeMillis();

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Flight Dynamics");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1800, 1500);

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
     

		back backButton = new back();
		move moveButton = new move();
		refresh refreshButton = new refresh();
		start startButton = new start();
		catalog catalogButton = new catalog();
		dynamics dynamicsButton = new dynamics();
		historical historicalButton = new historical();

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(54, 33, 89));
        sidePanel.setBounds(0, 80, 220, 2000);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(54, 33, 89));
        topPanel.setBounds(0, 0, 2000, 100);

        JPanel tableP = createTablePanel();

        // Add components to mainPanel
		mainPanel.add(backButton.getBackButton());
		mainPanel.add(moveButton.getMoveButton());
		mainPanel.add(refreshButton.getRefreshButton());    
        mainPanel.add(startButton.getStartButton());
		mainPanel.add(catalogButton.getCatalogButton());
		mainPanel.add(dynamicsButton.getdynamicsButton());
		mainPanel.add(historicalButton.getHistoricalButton());
		mainPanel.add(topPanel);
		mainPanel.add(sidePanel);
        mainPanel.add(tableP);

        endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;
        System.out.println("Running Time: " + runningTime + " milliseconds");
        return mainPanel;
    }

    private static JPanel createTablePanel() {
        JPanel tableP = new JPanel();
        tableP.setBackground(new Color(154, 133, 189));
        tableP.setBounds(280, 200, 1200, 440);


        String URL1 = URL_Maker.getUrlExtension("Drone Dynamics", 0, 25);
        String result = APIFetcher.FetchAPI(URL1);
        
        FileWriterUtil.writeToFile("Data is being Fetched from: " + URL1, "URLs.md");


        List<DroneDynamics> droneTypeList = JsonFormatter.ReadDroneList(3, result);

        String[] columns = {"ID", "Timestamp", "Speed", "Alignment Roll", "Alignment Yaw", "Longitude", "Latitude", "Battery Status", "Last Seen", "Status"};
        Object[][] data = new Object[25][columns.length];

   /*   // code Not needed anymore

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
 */ 
    ExecutorService executor = Executors.newFixedThreadPool(25); // set  the number of threads 

    CountDownLatch count  = new CountDownLatch(25); // count to wait for all threads to complete

    for (int i = 0; i < 25; i++) {
        final int index = i;
        executor.execute(() -> {
            String URL = droneTypeList.get(index).getDrone();
            String result2 = APIFetcher.FetchAPI(URL);
            List<Drone> droneTypeList2 = JsonFormatter.ReadDroneList(4, result2);
            int ID = droneTypeList2.get(0).getId();
            System.out.println("Iteration "+index);

            data[index] = new Object[]{
                    ID,
                    droneTypeList.get(index).getTimestamp(), droneTypeList.get(index).getSpeed(),
                    droneTypeList.get(index).getAlign_roll(), droneTypeList.get(index).getAlign_yaw(),
                    droneTypeList.get(index).getLongitude(), droneTypeList.get(index).getLatitude(),
                    droneTypeList.get(index).getBattery_status(), droneTypeList.get(index).getLast_seen(),
                    droneTypeList.get(index).getStatus()
            };

            count.countDown(); // count= count-1   : Signal that the task is complete 
        });
    }

    try {
        count.await(); // Wait for all tasks to complete
    } catch (InterruptedException e) {    //If the thread is interrupted while waiting
        e.printStackTrace();
    } finally {
        executor.shutdown();
    }
    
    
        JTable table = new JTable(data, columns);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1200, 425));
        table.setEnabled(false);
        table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(3).setPreferredWidth(70);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);
        table.getColumnModel().getColumn(7).setPreferredWidth(70);
        table.getColumnModel().getColumn(8).setPreferredWidth(200);
        table.getColumnModel().getColumn(9).setPreferredWidth(20);

        tableP.add(scroll);
       

        return tableP;
    }

    public static void dispose() {
		if(frame != null){
			frame.dispose();

		}
     
         
       
    }
   

}