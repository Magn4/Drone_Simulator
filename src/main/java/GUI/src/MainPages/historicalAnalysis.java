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

public class historicalAnalysis extends JFrame {

    private JButton earlierButton;
    private JButton laterButton;
    private JButton earlier5MinButton;
    private JButton later5MinButton;
    private JTextArea infoTextArea;
    private LocalDateTime dateTime;
    private int time;
    // private int offset;

   

    public historicalAnalysis() {
        setTitle("Historical Analysis");
        setSize(800, 600);
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
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
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

        JPanel leftPanel = new JPanel(new GridLayout(0, 1));

        leftPanel.add(createMenuButton("Home"));
        leftPanel.add(createMenuButton("Drone Catalog"));
        leftPanel.add(createMenuButton("Flight Dynamics"));
        leftPanel.add(createMenuButton("Historical Analysis"));
        leftPanel.setPreferredSize(new Dimension(170, getHeight()));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.decode("#008e9b"));
        topPanel.setPreferredSize(new Dimension(getWidth(), 60));

        topPanel.add(earlier5MinButton);
        topPanel.add(earlierButton);
        topPanel.add(laterButton);
        topPanel.add(later5MinButton);

        add(leftPanel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(infoTextArea), BorderLayout.CENTER);

        updateInfoTextArea(0); // Update infoTextArea initially
    }


    private JButton createMenuButton(String buttonText) {
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
    }

    private void updateInfoTextArea(int time) {
        


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
        String formattedDateTime = dateTime.format(formatter);
        // Update the infoTextArea with the formatted time
        infoTextArea.setText("Displaying data for: " + formattedDateTime);

        infoTextArea.removeAll();

        
        FileWriterUtil.writeToFile("Data is being Fetched from this time: " + formattedDateTime, "HistoricalAnalysis.md");


        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(480, 800));

        JPanel tableP = addTable();
        infoTextArea.add(tableP);

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
        tableP.setBounds(270, 200, 900, 500);

        List <DroneDynamics> droneTypeList = getList();



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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(historicalAnalysis::new);
    }
}