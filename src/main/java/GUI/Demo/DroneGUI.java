package GUI.Demo;

import javax.swing.*;
import java.util.List;


import API.Fetcher.APIFetcher;
import Formatter.JsonFormatter;
import Formatter.Drones.DroneType3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DroneGUI extends JFrame {
     // String UrlExt = "51/dynamics/?limit=1&format=json";
       // String UrlExt = "dronedynamics/?limit=10&offset=10&format=json";
    
      //  String UrlExt = "dronetypes/?format=json";
       // String UrlExt2 = "drones/?format=json";
       String UrlExt = "dronedynamics/?format=json";
       String FileExt = "Test.json";
        
       String result = APIFetcher.FetchAPI(UrlExt,FileExt);

        List<DroneType3> droneList = JsonFormatter.ReadDroneList( 3, result);

        String Status = droneList.get(0).getStatus();


    private JTextArea droneDashboardTextArea;
    private JTable droneCatalogTable;

    public DroneGUI() {
        setTitle("Drone Simulator GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel dashboardPanel = createTitledPanel("Drone Dashboard");
        JPanel catalogPanel = createTitledPanel("Drone Catalog");

        JButton refreshButton = createStyledButton("Refresh Data");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshData();
            }
        });

        setLayout(new GridLayout(2, 1));
        add(dashboardPanel);
        add(catalogPanel);

        getContentPane().add(refreshButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

    }

    private JPanel createTitledPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));

        if (title.equals("Drone Dashboard")) {
            droneDashboardTextArea = new JTextArea();
            droneDashboardTextArea.setEditable(false);
            JScrollPane dashboardScrollPane = new JScrollPane(droneDashboardTextArea);
            panel.add(dashboardScrollPane, BorderLayout.CENTER);
        } else if (title.equals("Drone Catalog")) {
            String[] columnNames = {"Model", "Attributes"};
            String[][] data = {{"Drone Model 1", "Attribute 1, Attribute 2"},
                               {"Drone Model 2", "Attribute 1, Attribute 2"}};
            droneCatalogTable = new JTable(data, columnNames);
            JScrollPane catalogScrollPane = new JScrollPane(droneCatalogTable);
            panel.add(catalogScrollPane, BorderLayout.CENTER);
        }

        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(59, 89, 182));
        button.setForeground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void refreshData() {
        droneDashboardTextArea.setText("Updated drone data goes here");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DroneGUI().setVisible(true);
            }
        });
    }
}