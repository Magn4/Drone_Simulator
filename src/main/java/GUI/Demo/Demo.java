package GUI.Demo;

import Formatter.Drones.DroneType1;
import Formatter.Drones.DroneType2;
import Formatter.Drones.DroneType3;
import API.Fetcher.APIFetcher;
import Formatter.JsonFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

public class Demo extends JFrame {

    private JComboBox<String> droneComboBox;
    private JButton getInfoButton;
    private JList<String> droneList;
    private DefaultListModel<String> listModel;
    private JTextArea infoTextArea;

    public Demo() {
        setTitle("Drone Information");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        droneComboBox = new JComboBox<>(new String[]{"Drone Type", "Drone List", "Drone Dynamics"});
        getInfoButton = new JButton("Get Info");
        listModel = new DefaultListModel<>();
        droneList = new JList<>(listModel);
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);

        getInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onGetInfoButtonClick();
            }
        });

        droneList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        droneList.setCellRenderer(new IconCellRenderer());
        droneList.addListSelectionListener(e -> {
            int selectedIndex = droneList.getSelectedIndex();
            if (selectedIndex != -1) {
                displayDroneInfo(selectedIndex);
            }
        });
    }

    private void addComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Choose Drone:"));
        topPanel.add(droneComboBox);
        topPanel.add(getInfoButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(droneList), BorderLayout.WEST);
        add(new JScrollPane(infoTextArea), BorderLayout.CENTER);
    }

    private void onGetInfoButtonClick() {
        String selectedDrone = (String) droneComboBox.getSelectedItem();
        if (selectedDrone != null) {
            String urlExtension = getUrlExtension(selectedDrone);
            String fileExtension = "Test.json";

            String result = APIFetcher.FetchAPI(urlExtension, fileExtension);

            if (result != null) {
                listModel.clear();
                if (selectedDrone.equals("Drone Type")) {
                    List<DroneType1> droneType1List = JsonFormatter.ReadDroneList(1, result);
                    droneType1List.sort(Comparator.comparingInt(DroneType1::getId));
                    for (DroneType1 droneType1 : droneType1List) {
                        listModel.addElement("Drone Name: " + droneType1.getTypename());
                    }
                } else if (selectedDrone.equals("Drone List")) {
                    List<DroneType2> droneType2List = JsonFormatter.ReadDroneList(2, result);
                    droneType2List.sort(Comparator.comparingInt(DroneType2::getId));
                    for (DroneType2 droneType2 : droneType2List) {
                        listModel.addElement("Drone ID: " + droneType2.getId());
                    }
                } else if (selectedDrone.equals("Drone Dynamics")) {
                    List<DroneType3> droneType3List = JsonFormatter.ReadDroneList(3, result);
                    droneType3List.sort(Comparator.comparingInt(DroneType3::getNumber));
                    for (DroneType3 droneType3 : droneType3List) {
                        listModel.addElement("Drone ID: " + droneType3.getNumber());
                    }
                }
            } else {
                infoTextArea.setText("Error: Failed to fetch API data.");
            }
        }
    }

    private <T> void displayDroneInfo(int selectedIndex) {
        String selectedDrone = (String) droneComboBox.getSelectedItem();
        if (selectedDrone != null) {
            String urlExtension = getUrlExtension(selectedDrone);
            String fileExtension = "Test.json";

            String result = APIFetcher.FetchAPI(urlExtension, fileExtension);

            if (result != null) {
                if (selectedDrone.equals("Drone Type")) {
                    List<DroneType1> droneType1List = JsonFormatter.ReadDroneList(1, result);
                    displayDroneInfo(droneType1List.get(selectedIndex));
                } else if (selectedDrone.equals("Drone List")) {
                    List<DroneType2> droneType2List = JsonFormatter.ReadDroneList(2, result);
                    displayDroneInfo(droneType2List.get(selectedIndex));
                } else if (selectedDrone.equals("Drone Dynamics")) {
                    List<DroneType3> droneType3List = JsonFormatter.ReadDroneList(3, result);
                    displayDroneInfo(droneType3List.get(selectedIndex));
                }
            } else {
                infoTextArea.setText("Error: Failed to fetch API data.");
            }
        }
    }

    private void displayDroneInfo(Object drone) {
        StringBuilder infoText = new StringBuilder();
        if (drone instanceof DroneType1) {
            DroneType1 droneType1 = (DroneType1) drone;
            infoText.append("Drone ID: ").append(droneType1.getId()).append("\n");

            infoText.append("Additional Information for Drone Type 1:\n");
            infoText.append("Number: ").append(droneType1.getNumber()).append("\n");
            infoText.append("ID: ").append(droneType1.getId()).append("\n");
            infoText.append("Manufacturer: ").append(droneType1.getManufacturer()).append("\n");
            infoText.append("Type Name: ").append(droneType1.getTypename()).append("\n");
            infoText.append("Weight: ").append(droneType1.getWeight()).append("\n");
            infoText.append("Max Speed: ").append(droneType1.getMax_speed()).append("\n");
            infoText.append("Battery Capacity: ").append(droneType1.getBattery_capacity()).append("\n");
            infoText.append("Control Range: ").append(droneType1.getControl_range()).append("\n");
            infoText.append("Max Carriage: ").append(droneType1.getMax_carriage()).append("\n\n");
        } else if (drone instanceof DroneType2) {
            DroneType2 droneType2 = (DroneType2) drone;
            infoText.append("Drone ID: ").append(droneType2.getId()).append("\n");
            infoText.append("Drone Type: ").append(droneType2.getDronetype()).append("\n");
            infoText.append("Serial Number: ").append(droneType2.getSerialnumber()).append("\n");
            infoText.append("Carriage Weight: ").append(droneType2.getCarriage_weight()).append("\n");
            infoText.append("Carriage Type: ").append(droneType2.getCarriage_type()).append("\n\n");
        } else if (drone instanceof DroneType3) {
            DroneType3 droneType3 = (DroneType3) drone;
            infoText.append("Drone ID: ").append(droneType3.getNumber()).append("\n");
            infoText.append("Status: ").append(droneType3.getStatus()).append("\n\n");

            infoText.append("Additional Information for Drone Type 3:\n");
            infoText.append("Number: ").append(droneType3.getNumber()).append("\n");
            infoText.append("Drone: ").append(droneType3.getDrone()).append("\n");
            infoText.append("Timestamp: ").append(droneType3.getTimestamp()).append("\n");
            infoText.append("Speed: ").append(droneType3.getSpeed()).append("\n");
            infoText.append("Alignment Roll: ").append(droneType3.getAlign_roll()).append("\n");
            infoText.append("Alignment Pitch: ").append(droneType3.getAlign_pitch()).append("\n");
            infoText.append("Alignment Yaw: ").append(droneType3.getAlign_yaw()).append("\n");
            infoText.append("Longitude: ").append(droneType3.getLongitude()).append("\n");
            infoText.append("Latitude: ").append(droneType3.getLatitude()).append("\n");
            infoText.append("Battery Status: ").append(droneType3.getBattery_status()).append("\n");
            infoText.append("Last Seen: ").append(droneType3.getLast_seen()).append("\n");
            infoText.append("Status: ").append(droneType3.getStatus()).append("\n\n");
        }
        infoTextArea.setText(infoText.toString());
    }

    private String getUrlExtension(String selectedDrone) {
        switch (selectedDrone) {
            case "Drone Type":
                return "dronetypes/?format=json&limit=30";
            case "Drone List":
                return "drones/?format=json&limit=30";
            case "Drone Dynamics":
                return "dronedynamics/?format=json&limit=20";
            default:
                return "";
        }
    }

    private class IconCellRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

        private DroneIcon droneIcon = new DroneIcon();

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setIcon(droneIcon);
            label.setHorizontalTextPosition(JLabel.RIGHT);
            return label;
        }
    }

    private class DroneIcon implements Icon {
        private static final int SIZE = 20;
    
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(Color.GREEN);
            g.fillOval(x, y, SIZE, SIZE);
            
        }
    
        @Override
        public int getIconWidth() {
            return SIZE;
        }
    
        @Override
        public int getIconHeight() {
            return SIZE;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Demo());
    }
}
