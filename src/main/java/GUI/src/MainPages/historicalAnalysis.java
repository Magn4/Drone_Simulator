package GUI.src.MainPages;

import javax.swing.*;

import API.Fetcher.APIFetcher;
import API.Fetcher.FileWriterUtil;
import API.Fetcher.URL_Maker;
import Formatter.JsonFormatter;
import Formatter.Drones.Drone;
import Formatter.Drones.DroneType;
import Formatter.Drones.DroneDynamics;

import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class historicalAnalysis extends JFrame {

    private JButton earlierButton;
    private JButton laterButton;
    private JButton earlier5MinButton;
    private JButton later5MinButton;
    private JLabel infoTextArea;
    private LocalDateTime dateTime;
    private int time;
    // private int offset;

    private long startTime;
    private long endTime;
   

    public historicalAnalysis() {
        startTime = System.currentTimeMillis(); //record the start of the running time

        setTitle("Historical Analysis");
        setSize(1500, 1500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();

        setLocationRelativeTo(null);
        setVisible(true);

    }
    
    

    private void initComponents() {
        int offset = 0;
        earlierButton = new JButton("<");
        laterButton = new JButton(">");
        earlier5MinButton = new JButton("<<");
        later5MinButton = new JButton(">>");
        
        infoTextArea = new JLabel();
        dateTime = LocalDateTime.of(2023, 12, 26, 9, 7, 0, 618782);
        time = 0;
        offset = time*25;


        earlier5MinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTime = dateTime.minusMinutes(5);
                time = time - 5;
                updateInfoTextArea(time);                
            }
        });

        earlierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTime = dateTime.minusMinutes(1);

                time = time - 1;
                updateInfoTextArea(time);
            }
        });

        laterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTime = dateTime.plusMinutes(1);
                time = time + 1;
                updateInfoTextArea(time);

            }
        });

        later5MinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTime = dateTime.plusMinutes(5);
                
                time = time + 5;
                updateInfoTextArea(time);

            }
        });
    }

    private void addComponents() {
        setLayout(new BorderLayout());

		JPanel sidePanel = new JPanel() ;
		sidePanel.setBackground(new Color(54, 33, 89));
		sidePanel.setBounds(0, 80, 200, 2000);


		JPanel topPanel = new JPanel() ;
		topPanel.setBackground(new Color(54, 33, 89));
		topPanel.setBounds(0, 0, 2000, 80);



                JPanel infosP = new JPanel();
        infosP.setBounds(200, 50, 200, 200);
        infosP.setBackground(new Color(54, 33, 89));


        back backButton = new back();
		move moveButton = new move();
		refresh refreshButton = new refresh();
		start startButton = new start();
		catalog catalogButton = new catalog();
		dynamics dynamicsButton = new dynamics();
		historical historicalButton = new historical();







        topPanel.add(earlier5MinButton);
        topPanel.add(earlierButton);
        topPanel.add(laterButton);
        topPanel.add(later5MinButton);


        add(backButton.getBackButton());
		add(moveButton.getMoveButton());
		add(refreshButton.getRefreshButton());
		add(startButton.getStartButton());
		add(catalogButton.getCatalogButton());
		add(dynamicsButton.getdynamicsButton());
		add(historicalButton.getHistoricalButton());



        add(sidePanel);
        add(topPanel);
        add(infoTextArea);

        updateInfoTextArea(0); // Update infoTextArea initially
    }


   /*  private JButton createMenuButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#008e9b"));
        button.setForeground(Color.WHITE);

        int topPadding = 10;
        int leftPadding = 20;
        int bottomPadding = 10;
        int rightPadding = 20;
        button.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));

        button.setBorderPainted(false);

        return button;
    }*/

    private void updateInfoTextArea(int time) {
        


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
        String formattedDateTime = dateTime.format(formatter);
        // Update the infoTextArea with the formatted time
        infoTextArea.setText("Displaying data for: " + formattedDateTime);
        //infoTextArea.setBounds(0, 0, 400, 20);
        infoTextArea.removeAll();
        // JLabel infos = new JLabel();
        // infos.setText("Displaying data for: " + formattedDateTime);
        
        FileWriterUtil.writeToFile("Data is being Fetched from this time: " + formattedDateTime, "HistoricalAnalysis.md");


        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(480, 800));

        JPanel tableP = addTable();
        infoTextArea.add(tableP);
        
        endTime = System.currentTimeMillis();

        // Calculate the running time in milliseconds
        long runningTime = endTime - startTime;

        // Print the running time in seconds
        System.out.println("Running Time: " + runningTime + " miliseconds");

    }

    private String getURL(int offset) {
        return URL_Maker.getUrlExtension("Drone Dynamics", offset, 25);
    }

    private List<DroneDynamics> getList() {

        int offset = time * 25;

        String URL1 = getURL(offset);
        String result = APIFetcher.FetchAPI(URL1);
        List<DroneDynamics> droneTypeList = JsonFormatter.ReadDroneList(3, result);
        return droneTypeList;

    } 

    private JPanel addTable() {

        JPanel tableP = new JPanel();
        tableP.setBackground(new Color(154, 133, 189));
        tableP.setBounds(230, 200, 1020, 500);

        List <DroneDynamics> droneTypeList = getList();



        String[] columns = {"ID", "Timestamp", "Speed", "Alignment Roll", "Alignment Yaw", "Longitude", "Latitude", "Battery Status", "Last Seen", "Status"};
        Object[][] data = new Object[25][columns.length];

       
       /*  for (int i = 0; i < 25; i++) {
            String URL = droneTypeList.get(i).getDrone();
            String result2 = APIFetcher.FetchAPI(URL);
            List<Drone> droneTypeList2 = JsonFormatter.ReadDroneList(4, result2);
            int ID = droneTypeList2.get(0).getId();
			System.out.println(i);

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
            System.out.println(index);

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
        scroll.setPreferredSize(new Dimension(1020, 2000));
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
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(historicalAnalysis::new);
        

    }
}
