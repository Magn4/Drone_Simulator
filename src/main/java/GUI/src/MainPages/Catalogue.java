package GUI.src.MainPages;

import API.Fetcher.APIFetcher;
import API.Fetcher.URL_Maker;
import API.Formatter.JsonFormatter;
import API.Formatter.Drones.DroneType;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Catalogue extends JFrame {

    private JComboBox<Integer> droneComboBox;
    private JButton getInfoButton;
    private JTextArea infoTextArea;
    private List<DroneType> dronesList;

    public Catalogue() {
        setTitle("Drone Catalog");
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

        Font font = new Font("Abadi", Font.BOLD+Font.PLAIN, 17); 
        infoTextArea.setFont(font);
        infoTextArea.setEditable(false);
        setDroneList();

        droneComboBox = new JComboBox<>();
        for (int i = 71; i <= 90; i++) {
            droneComboBox.addItem(i);
        }
        getInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
  
              displayDroneInfo();
              drawBarCharts();


            }
        });
    }

    private void setDroneList() {
        String urlExtension = URL_Maker.getUrlExtension("Drone Type", 0, 20);
        
		String Token = "Token 6ffe7e815e07b6ede78cade7617454eeb944d168";
        APIFetcher apiFetcher = new APIFetcher();

        String result = apiFetcher.FetchAPI(urlExtension, Token);
        dronesList = JsonFormatter.ReadDroneList(1, result);
        dronesList.sort((o1, o2) -> o1.getId() - o2.getId());
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
        topPanel.setPreferredSize(new Dimension(getWidth(), 60));

        JLabel chooseDroneLabel = new JLabel("Choose Drone ID:");
        chooseDroneLabel.setForeground(Color.WHITE);
        topPanel.add(chooseDroneLabel);
        topPanel.add(droneComboBox);
        topPanel.add(getInfoButton);

        add(leftPanel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(infoTextArea), BorderLayout.CENTER);
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

        button.addActionListener(e -> handleMenuButtonClick(buttonText));

        return button;
    }

    private void handleMenuButtonClick(String buttonText) {
        if (buttonText.equals("Home")) {
            dispose();
            new Home();
            Home.main(null);
        } else if (buttonText.equals("Flight dynamics")) {
            dispose();
            flightDynamics.main(null);
        }
                else if (buttonText.equals("Historical Analysis")){
                    dispose();
                    new historicalAnalysis();
                }
    }

    private void displayDroneInfo() {
        int i = (int) droneComboBox.getSelectedItem();

        StringBuilder infoText = new StringBuilder();
        infoText.append("Drone ID: ").append(dronesList.get(i - 71).getId()).append("\n");
        infoText.append("Manufacturer: ").append(dronesList.get(i - 71).getManufacturer()).append("\n");
        infoText.append("Type Name: ").append(dronesList.get(i - 71).getTypename()).append("\n");
        infoText.append("Weight: ").append(dronesList.get(i - 71).getWeight()).append("\n");
        infoText.append("Max Speed: ").append(dronesList.get(i - 71).getMax_speed()).append("\n");
        infoText.append("Battery Capacity: ").append(dronesList.get(i - 71).getBattery_capacity()).append("\n");
        infoText.append("Control Range: ").append(dronesList.get(i - 71).getControl_range()).append("\n");
        infoText.append("Max Carriage: ").append(dronesList.get(i - 71).getMax_carriage()).append("\n");

        infoTextArea.setText(infoText.toString());
    }
    
    private void drawBarCharts() {
        JFXPanel fxPanel1 = new JFXPanel();
        JFXPanel fxPanel2 = new JFXPanel();
        JFXPanel fxPanel3 = new JFXPanel();
        JFXPanel fxPanel4 = new JFXPanel();
        JFXPanel fxPanel5 = new JFXPanel(); 
    
        Platform.runLater(() -> {
            // Chart 1: Max Speed
            CategoryAxis xAxis1 = new CategoryAxis(); 
            NumberAxis yAxis1 = new NumberAxis(); 
            BarChart<String, Number> chart1 = new BarChart<>(xAxis1, yAxis1);
            chart1.setTitle("Max Speed");
    
            XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            for (DroneType drone : dronesList) {
                series1.getData().add(new XYChart.Data<>(String.valueOf(drone.getId()), drone.getMax_speed()));
            }
    
            chart1.getData().add(series1);
            fxPanel1.setScene(new Scene(chart1));
    
            // Chart 2: Battery Capacity
            CategoryAxis xAxis2 = new CategoryAxis(); 
            NumberAxis yAxis2 = new NumberAxis(); 
            BarChart<String, Number> chart2 = new BarChart<>(xAxis2, yAxis2);
            chart2.setTitle("Battery Capacity");
    
            XYChart.Series<String, Number> series2 = new XYChart.Series<>();
            for (DroneType drone : dronesList) {
                series2.getData().add(new XYChart.Data<>(String.valueOf(drone.getId()), drone.getBattery_capacity()));
            }
    
            chart2.getData().add(series2);
            fxPanel2.setScene(new Scene(chart2));
    
            // Chart 3: Control Range
            CategoryAxis xAxis3 = new CategoryAxis(); 
            NumberAxis yAxis3 = new NumberAxis(); 
            BarChart<String, Number> chart3 = new BarChart<>(xAxis3, yAxis3);
            chart3.setTitle("Control Range");
    
            XYChart.Series<String, Number> series3 = new XYChart.Series<>();
            for (DroneType drone : dronesList) {
                series3.getData().add(new XYChart.Data<>(String.valueOf(drone.getId()), drone.getControl_range()));
            }
    
            chart3.getData().add(series3);
            fxPanel3.setScene(new Scene(chart3));
    
            // Chart 4: Max Carriage
            CategoryAxis xAxis4 = new CategoryAxis(); 
            NumberAxis yAxis4 = new NumberAxis(); 
            BarChart<String, Number> chart4 = new BarChart<>(xAxis4, yAxis4);
            chart4.setTitle("Max Carriage");
    
            XYChart.Series<String, Number> series4 = new XYChart.Series<>();
            for (DroneType drone : dronesList) {
                series4.getData().add(new XYChart.Data<>(String.valueOf(drone.getId()), drone.getMax_carriage()));
            }
    
            chart4.getData().add(series4);
            fxPanel4.setScene(new Scene(chart4));
    
            // Chart 5: Weight
            CategoryAxis xAxis5 = new CategoryAxis(); 
            NumberAxis yAxis5 = new NumberAxis(); 
            BarChart<String, Number> chart5 = new BarChart<>(xAxis5, yAxis5);
            chart5.setTitle("Weight");
    
            XYChart.Series<String, Number> series5 = new XYChart.Series<>();
            for (DroneType drone : dronesList) {
                series5.getData().add(new XYChart.Data<>(String.valueOf(drone.getId()), drone.getWeight()));
            }
    
            chart5.getData().add(series5);
            fxPanel5.setScene(new Scene(chart5));
            //chart1.setLegendVisible(false); in JavaFX wird standardmäßig eine Legende angezeigt,

        });
    
        JPanel rightPanel = new JPanel(new GridLayout(2, 2)); // Grid layout to accommodate 5 charts
        rightPanel.add(fxPanel1); 
        rightPanel.add(fxPanel2); 
        rightPanel.add(fxPanel3); 
        rightPanel.add(fxPanel4); 
        rightPanel.add(fxPanel5); 
    
        add(rightPanel, BorderLayout.EAST); // Add the right panel to the main frame
    }
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Catalogue::new);
    }
    
}