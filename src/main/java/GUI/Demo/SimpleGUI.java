package GUI.Demo;

import API.Fetcher.APIFetcher;
import Formatter.JsonFormatter;
import Formatter.Drones.DroneType1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SimpleGUI implements ActionListener {

    private int count = 0;
    private JLabel label;
    private JPanel panel;
    private JFrame frame;

    public SimpleGUI() {
        frame = new JFrame();

        JButton button = new JButton("Click me!");
        button.addActionListener(this);
        label = new JLabel("Number of Clicks: 0");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);
    }

    public void fetchDataAndPrintInfo() {
        APIFetcher apiFetcher = new APIFetcher();
        String urlExtension = "dronetypes/?format=json";
        String fileExtension = "YourNewGUI.json";

        String result = apiFetcher.FetchAPI(urlExtension, fileExtension);

        JsonFormatter jsonFormatter = new JsonFormatter();
        List<DroneType1> droneType1List = jsonFormatter.ReadDroneList(1, result);

        if (droneType1List != null && !droneType1List.isEmpty()) {
            // Access specific information from the list
            DroneType1 firstDrone = droneType1List.get(0);
            String infoText = "Drone ID: " + firstDrone.getId() + "\n" +
                    "Manufacturer: " + firstDrone.getManufacturer() + "\n" +
                    // ... (access other properties as needed)
                    "Number of clicks: " + count;

            // Update the label with fetched information
            label.setText(infoText);
        } else {
            label.setText("Error: Drone Type 1 list is empty.");
        }
    }

    public static void main(String[] args) {
        SimpleGUI simpleGUI = new SimpleGUI();
        simpleGUI.fetchDataAndPrintInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        fetchDataAndPrintInfo(); // Fetch and display information on button click
    }
}
