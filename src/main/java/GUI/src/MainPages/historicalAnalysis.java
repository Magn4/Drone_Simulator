package GUI.src.MainPages;

import API.Fetcher.APIFetcher;
import Formatter.Drones.DroneDynamics;
import Formatter.Drones.DroneType;
//import Formatter.Drones.DroneDynamics;
import Formatter.JsonFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

public class historicalAnalysis extends JFrame{

    private JComboBox<Integer> droneComboBox;
   // private JButton catalogButton;
    private JButton refreshButton;
    private JList<String>droneList;
    private DefaultListModel<String> listModel;
    private JTextArea infoTextArea;

    public historicalAnalysis(){
        setTitle("HistoricalAnalysis");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();

        setLocationRelativeTo(null);
        setVisible(true);

    }
    private void initComponents() {
        //catalogButton = new JButton("Drone Catalog");
        refreshButton = new JButton("refresh");
        listModel = new DefaultListModel<>();
        droneList = new JList<>(listModel);
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);

        /*catalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onHistoricalAnalysisButtonClick();
            }
        });*/

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRefreshButtonClick();
            }
        });
        droneList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        droneList.addListSelectionListener(e -> {
            int selectedIndex = droneList.getSelectedIndex();
            if (selectedIndex != -1) {
                displayDroneInfo(selectedIndex);
            }
        });

        // Populate droneComboBox with drone IDs
        droneComboBox = new JComboBox<>();
        // Add drone IDs (adjust as needed)
        for (int i = 0; i <= 5; i++) {
            droneComboBox.addItem(i);
        }
    }
    private void addComponents() {
        setLayout(new BorderLayout());
    
        JPanel leftPanel = new JPanel(new GridLayout(0, 1));
    
        // Create and add buttons to the leftPanel
        leftPanel.add(createMenuButton("Home"));
        leftPanel.add(createMenuButton("Drone Catalog"));
        leftPanel.add(createMenuButton("Flight Dynamics"));
        leftPanel.add(createMenuButton("Historical Analysis"));
        leftPanel.setPreferredSize(new Dimension(170, getHeight()));
    
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.decode("#008e9b"));
        topPanel.setPreferredSize(new Dimension(getWidth(),60));

        JLabel chooseDroneLabel = new JLabel("Choose Drone min:");
        chooseDroneLabel.setForeground(Color.WHITE); // Set text color
        topPanel.add(chooseDroneLabel);
        topPanel.add(droneComboBox);
        topPanel.add(refreshButton);
    
        add(leftPanel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(droneList), BorderLayout.CENTER);
        add(new JScrollPane(infoTextArea), BorderLayout.EAST);
    }
    
    private JButton createMenuButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#008e9b"));
        button.setForeground(Color.WHITE);
        
    
        // Add some padding
        int topPadding = 10;
        int leftPadding = 20;
        int bottomPadding = 10;
        int rightPadding = 20;
        button.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
    
        // Remove border
        button.setBorderPainted(false);
    
        return button;
    }
    
    private void onHistoricalAnalysisButtonClick() {
        // Add logic for the button
        // This can be used for navigating to the dynamics or any other specific action
        System.out.println("Historical Analysis button clicked");
    }
    
    private void onRefreshButtonClick() {
        int selectedDroneId = (int) droneComboBox.getSelectedItem();
        String urlExtension = getUrlExtension("Drone Type");
        String fileExtension = "Test.json";

        String result = APIFetcher.FetchAPI(urlExtension, fileExtension);
        if (result != null) {
            listModel.clear();
            if (droneComboBox.getSelectedItem().equals("Drone Type")) {
                List<DroneType> DroneDynamicsList = JsonFormatter.ReadDroneList(1, result);
                DroneDynamicsList.sort(Comparator.comparingInt(DroneType::getId));
                for (DroneType droneType : DroneDynamicsList) {
                    listModel.addElement("Drone ID: " + droneType.getId());
                }
                if (!DroneDynamicsList.isEmpty()) {
                    displayDroneInfo(DroneDynamicsList.get(0));
                }
            }
        } else {
            infoTextArea.setText("Error: Failed to fetch API data.");
        }
    }

    private void displayDroneInfo(int selectedIndex) {
        int selectedDroneId = (int) droneComboBox.getSelectedItem();
        String urlExtension = getUrlExtension("Drone Dynamics");
        String fileExtension = "Test.json";

        String result = APIFetcher.FetchAPI(urlExtension, fileExtension);
        if (result != null && droneComboBox.getSelectedItem().equals("Drone Dynamics List")) {
            List<DroneType> DroneDynamicsList = JsonFormatter.ReadDroneList(1, result);
            displayDroneInfo(DroneDynamicsList.get(selectedIndex));
        } else {
            infoTextArea.setText("Error: Failed to fetch API data.");
        }
    
    }

    private void displayDroneInfo(DroneDynamics droneDynamics) {
        StringBuilder infoText = new StringBuilder();
       

        infoTextArea.setText(infoText.toString());
    }

    private String getUrlExtension(String selectedDrone) {
        switch (selectedDrone) {
            case "Drone Historical":
                return "http://dronesim.facets-labs.com/api/dronedynamics/?limit=10&offset=10";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(historicalAnalysis::new);
    }
}

    

