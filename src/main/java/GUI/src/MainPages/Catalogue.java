package GUI.src.MainPages;

import API.Fetcher.APIFetcher;
import Formatter.Drones.DroneType;
import Formatter.JsonFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Comparator;
import java.util.List;

public class Catalogue extends JFrame {

    private JComboBox<Integer> droneComboBox;
    private JButton getInfoButton;
    private JTextArea infoTextArea;

    public Catalogue() {
        setTitle("Drone Information");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        getInfoButton = new JButton("Get Info");
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);

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
        leftPanel.add(createMenuButton("Flight dynamics"));
        leftPanel.add(createMenuButton("Historical Analysis"));
        leftPanel.setPreferredSize(new Dimension(170, getHeight()));
    
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.decode("#008e9b"));
        topPanel.setPreferredSize(new Dimension(getWidth(),60));

        JLabel chooseDroneLabel = new JLabel("Choose Drone ID:");
        chooseDroneLabel.setForeground(Color.WHITE);
        topPanel.add(chooseDroneLabel);
        topPanel.add(droneComboBox);
        topPanel.add(getInfoButton);
    
        add(leftPanel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(infoTextArea), BorderLayout.EAST);
    }
    
    private JButton createMenuButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#008e9b"));
        button.setForeground(Color.WHITE);
        
        int topPadding = 20;
        int leftPadding = 20;
        int bottomPadding = 20;
        int rightPadding = 20;
        button.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
    
        // Remove border
        //button.setBorderPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonText.equals("Home")) {
                    
                    dispose();
                    new Home();
                    Home.main(null);

                } else if (buttonText.equals("Flight dynamics")) {

                    dispose();
                    flightDynamics.main(null);
                }
            }
        });
    
        return button;
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
        SwingUtilities.invokeLater(Catalogue::new);
    }
}
