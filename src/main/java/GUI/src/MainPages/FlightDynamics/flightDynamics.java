package GUI.src.MainPages.FlightDynamics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import API.Fetcher.APIFetcher;
import API.Fetcher.FileWriterUtil;
import API.Fetcher.URL_Maker;
import API.Formatter.JsonFormatter;
import API.Formatter.Drones.Drone;
import API.Formatter.Drones.DroneDynamics;
import API.Formatter.countFinder;
import GUI.src.MainPages.Refresh.refresh;
import GUI.src.MainPages.aLogin.Login;
import GUI.src.MainPages.Catalogue.catalogueButton;
import GUI.src.MainPages.Historical.historical;
import GUI.src.MainPages.Home.homeButton;



public class flightDynamics {
    
    
    private static JFrame frame;

    private static long startTime;
    private static long endTime;
    private static String Token = Login.getToken();

   
    public static void main(String[] args) {

        startTime = System.currentTimeMillis();

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Flight Dynamics");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1920, 1080);

            // Create main panel:
            JPanel mainPanel = createMainPanel();

            // Create JScrollPane:
            JScrollPane scrollPane = new JScrollPane(mainPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


            // Add JScrollPane
            frame.add(scrollPane);

            frame.setVisible(true);
        });
      

        
    }

    private static JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(1500, 1100));

        // Create buttons and panels
     

		refresh refreshButton = new refresh();
		homeButton startButton = new homeButton();
		catalogueButton catalogButton = new catalogueButton();
		flightDynamicsButton dynamicsButton = new flightDynamicsButton();
		historical historicalButton = new historical();

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(22, 72, 99));
        sidePanel.setBounds(0, 80, 220, 2000);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(22, 72, 99));
        topPanel.setBounds(0, 0, 2000, 100);

        JPanel tableP = createTablePanel();

        // Add components to mainPanel

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
        tableP.setBackground(new Color(66, 125, 157));
        tableP.setBounds(280, 200, 1200, 510);

        APIFetcher apiFetcher = new APIFetcher();

        
        // This retrieves the number of drones for a more Dynamic Use.
        int count1 = countFinder.getCount("Drones");
        

        String URL1 = URL_Maker.getUrlExtension("Drone Dynamics", 0, count1);

       // System.out.println("This is the count1: " + count1);
        //String Token = "Token 6ffe7e815e07b6ede78cade7617454eeb944d168";

       

        String result = apiFetcher.FetchAPI(URL1, Token);
        
        FileWriterUtil.writeToFile("Data is being Fetched from: " + URL1, "URLs.md");


        List<DroneDynamics> droneTypeList = JsonFormatter.ReadDroneList(3, result);

        String[] columns = {"ID", "Timestamp", "Speed", "Alignment Roll", "Alignment Yaw", "Longitude", "Latitude", "Battery Status", "Last Seen", "Status"};
        Object[][] data = new Object[count1][columns.length];

   
    ExecutorService executor = Executors.newFixedThreadPool(count1); // set  the number of threads 

    CountDownLatch count  = new CountDownLatch(count1); // count to wait for all threads to complete

    for (int i = 0; i < count1; i++) {
        final int index = i;
        executor.execute(() -> {
            String URL = droneTypeList.get(index).getDrone();
            
            String result2 = apiFetcher.FetchAPI(URL, Token);
            List<Drone> droneTypeList2 = JsonFormatter.ReadDroneList(4, result2);
            int ID = droneTypeList2.get(0).getId();
            System.out.println("Iteration: "+index);

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
        scroll.setPreferredSize(new Dimension(1200, 500));
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
        //table.setBackground(new Color(154, 133, 189));
        //table.setForeground(Color.black);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setIntercellSpacing(new Dimension(10, 10));
        table.setRowHeight(40);



        tableP.add(scroll);
       

        return tableP;
    }

    public static void dispose() {
		if(frame != null){
			frame.dispose();

		}
     
         
       
    }
   

}