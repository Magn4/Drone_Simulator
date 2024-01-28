package GUI.src.MainPages;

import API.Fetcher.APIFetcher;
import Formatter.Drones.DroneType;
import Formatter.JsonFormatter;
import API.Fetcher.URL_Maker;

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

        catalogButton.addActionListener(e -> onCatalogButtonClick());
        getInfoButton.addActionListener(e -> onGetInfoButtonClick());

        droneList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        droneList.addListSelectionListener(e -> {
            int selectedIndex = droneList.getSelectedIndex();
            if (selectedIndex != -1) {
                displayDroneInfo(selectedIndex);
            }
        });

        droneComboBox = new JComboBox<>();
        for (int i = 71; i <= 95; i++) {
            droneComboBox.addItem(i);
        }
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
        topPanel.setBackground(new Color(54, 33, 89));
        topPanel.setPreferredSize(new Dimension(getWidth(), 60));

        JLabel chooseDroneLabel = new JLabel("Choose Drone ID:");
        chooseDroneLabel.setForeground(Color.WHITE);
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

        int padding = 10;
        button.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        button.setBorderPainted(false);

        return button;
    }

    private void onCatalogButtonClick() {
        System.out.println("Drone Catalog button clicked");
        // Add logic for navigating to the catalog or any specific action
    }

    private void onGetInfoButtonClick() {
        int selectedDroneId = droneComboBox.getItemAt(droneComboBox.getSelectedIndex());

        String urlExtension = URL_Maker.getUrlExtension("Drone Type", null, "25");
        String fileExtension = "Test.json";

        String result = APIFetcher.FetchAPI(urlExtension, fileExtension);

        if (result != null && droneComboBox.getSelectedItem() instanceof Integer) {
            List<DroneType> droneTypeList = JsonFormatter.ReadDroneList(1, result);
            droneTypeList.sort(Comparator.comparingInt(DroneType::getId));

            listModel.clear();
            for (DroneType droneType : droneTypeList) {
                listModel.addElement("Drone ID: " + droneType.getId());
                StringBuilder infoText = new StringBuilder();
                infoText.append("Drone ID: ").append(droneType.getId()).append("\n");
                infoText.append("Manufacturer: ").append(droneType.getManufacturer()).append("\n");
                infoText.append("Type Name: ").append(droneType.getTypename()).append("\n");
                infoText.append("Weight: ").append(droneType.getWeight()).append("\n");
                infoText.append("Max Speed: ").append(droneType.getMax_speed()).append("\n");
                infoText.append("Battery Capacity: ").append(droneType.getBattery_capacity()).append("\n");
                infoText.append("Control Range: ").append(droneType.getControl_range()).append("\n");
                infoText.append("Max Carriage: ").append(droneType.getMax_carriage()).append("\n");
            }

            if (!droneTypeList.isEmpty()) {
                displayDroneInfo(droneTypeList.get(0));
            }
        } else {
            infoTextArea.setText("Error: Failed to fetch API data.");
        }
    }

    private void displayDroneInfo(int selectedIndex) {
        int selectedDroneId = droneComboBox.getItemAt(droneComboBox.getSelectedIndex());
        String urlExtension = URL_Maker.getUrlExtension("Drone Type",null, "25");
        String fileExtension = "Test.json";

        String result = APIFetcher.FetchAPI(urlExtension, fileExtension);
        if (result != null && droneComboBox.getSelectedItem() instanceof Integer) {
            List<DroneType> droneTypeList = JsonFormatter.ReadDroneList(1, result);
            displayDroneInfo(droneTypeList.get(selectedIndex));
        } else {
            infoTextArea.setText("Error: Failed to fetch API data.");
        }
    }

    private void displayDroneInfo(DroneType droneType) {
        StringBuilder infoText = new StringBuilder();
        infoText.append("Drone ID: ").append(droneType.getId()).append("\n");
        infoText.append("Manufacturer: ").append(droneType.getManufacturer()).append("\n");
        infoText.append("Type Name: ").append(droneType.getTypename()).append("\n");
        infoText.append("Weight: ").append(droneType.getWeight()).append("\n");
        infoText.append("Max Speed: ").append(droneType.getMax_speed()).append("\n");
        infoText.append("Battery Capacity: ").append(droneType.getBattery_capacity()).append("\n");
        infoText.append("Control Range: ").append(droneType.getControl_range()).append("\n");
        infoText.append("Max Carriage: ").append(droneType.getMax_carriage()).append("\n");

        infoTextArea.setText(infoText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DroneCatalog::new);
    }
}
