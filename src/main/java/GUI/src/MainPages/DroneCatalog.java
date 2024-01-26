//the main

package GUI.Demo;

import API.Fetcher.APIFetcher;
import Formatter.Drones.DroneType1;
import Formatter.JsonFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

public class DroneCatalog extends JFrame {

    private JComboBox<Integer> droneComboBox;
    private JButton catalogButton;
    private JButton getInfoButton;
    private JList<String> droneList;
    private DefaultListModel<String> listModel;
    private JTextArea infoTextArea;

    public DroneCatalog() {
        setTitle("Drone Information");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        catalogButton = new JButton("Drone Catalog");
        getInfoButton = new JButton("Get Info");
        listModel = new DefaultListModel<>();
        droneList = new JList<>(listModel);
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);

        catalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCatalogButtonClick();
            }
        });

        getInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onGetInfoButtonClick();
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
        for (int i = 71; i <= 95; i++) {
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
        topPanel.setBackground(new Color(54,33,89));
        topPanel.setPreferredSize(new Dimension(getWidth(),60));

        JLabel chooseDroneLabel = new JLabel("Choose Drone ID:");
        chooseDroneLabel.setForeground(Color.WHITE); // Set text color
        topPanel.add(chooseDroneLabel);
        topPanel.add(droneComboBox);
        topPanel.add(getInfoButton);
    
        add(leftPanel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(droneList), BorderLayout.CENTER);
        add(new JScrollPane(infoTextArea), BorderLayout.EAST);
    }
    
    private JButton createMenuButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFocusPainted(false);
        button.setBackground(new Color(54, 33, 89));
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
    
    private void onCatalogButtonClick() {
        // Add logic for "Drone Catalog" button
        // This can be used for navigating to the catalog or any other specific action
        System.out.println("Drone Catalog button clicked");
    }
    
    private void onGetInfoButtonClick() {
        int selectedDroneId = (int) droneComboBox.getSelectedItem();
        String urlExtension = getUrlExtension("Drone Type");
        String fileExtension = "Test.json";

        String result = APIFetcher.FetchAPI(urlExtension, fileExtension);
        if (result != null) {
            listModel.clear();
            if (droneComboBox.getSelectedItem().equals("Drone Type")) {
                List<DroneType1> droneType1List = JsonFormatter.ReadDroneList(1, result);
                droneType1List.sort(Comparator.comparingInt(DroneType1::getId));
                for (DroneType1 droneType1 : droneType1List) {
                    listModel.addElement("Drone ID: " + droneType1.getId());
                }
                if (!droneType1List.isEmpty()) {
                    displayDroneInfo(droneType1List.get(0));
                }
            }
        } else {
            infoTextArea.setText("Error: Failed to fetch API data.");
        }
    }

    private void displayDroneInfo(int selectedIndex) {
        int selectedDroneId = (int) droneComboBox.getSelectedItem();
        String urlExtension = getUrlExtension("Drone Type");
        String fileExtension = "Test.json";

        String result = APIFetcher.FetchAPI(urlExtension, fileExtension);
        if (result != null && droneComboBox.getSelectedItem().equals("Drone Type")) {
            List<DroneType1> droneType1List = JsonFormatter.ReadDroneList(1, result);
            displayDroneInfo(droneType1List.get(selectedIndex));
        } else {
            infoTextArea.setText("Error: Failed to fetch API data.");
        }
    
    }

    private void displayDroneInfo(DroneType1 droneType1) {
        StringBuilder infoText = new StringBuilder();
        infoText.append("Drone ID: ").append(droneType1.getId()).append("\n");
        infoText.append("Manufacturer: ").append(droneType1.getManufacturer()).append("\n");
        infoText.append("Type Name: ").append(droneType1.getTypename()).append("\n");
        infoText.append("Weight: ").append(droneType1.getWeight()).append("\n");
        infoText.append("Max Speed: ").append(droneType1.getMax_speed()).append("\n");
        infoText.append("Battery Capacity: ").append(droneType1.getBattery_capacity()).append("\n");
        infoText.append("Control Range: ").append(droneType1.getControl_range()).append("\n");
        infoText.append("Max Carriage: ").append(droneType1.getMax_carriage()).append("\n");

        infoTextArea.setText(infoText.toString());
    }

    private String getUrlExtension(String selectedDrone) {
        switch (selectedDrone) {
            case "Drone Type":
                return "dronetypes/?format=json&limit=30";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DroneCatalog::new);
    }
}


