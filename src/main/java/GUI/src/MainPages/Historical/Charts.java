package GUI.src.MainPages.Historical;

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
import java.util.List;

public class Charts {
    private List<DroneType> dronesList;

    public Charts(List<DroneType> dronesList) {
        this.dronesList = dronesList;
    }

    public void drawBarCharts(Container container) {
        JFXPanel[] fxPanels = new JFXPanel[5];

        for (int i = 0; i < 5; i++) {
            fxPanels[i] = new JFXPanel();
        fxPanels[i].setPreferredSize(new Dimension(370, 250));
        }

        Platform.runLater(() -> {
            // Draw charts
            drawChart(fxPanels[0], "Max Speed", "Max Speed", DroneType::getMax_speed);
            drawChart(fxPanels[1], "Battery Capacity", "Battery Capacity", DroneType::getBattery_capacity);
            drawChart(fxPanels[2], "Control Range", "Control Range", DroneType::getControl_range);
            drawChart(fxPanels[3], "Max Carriage", "Max Carriage", DroneType::getMax_carriage);
            drawChart(fxPanels[4], "Weight", "Weight", DroneType::getWeight);

            JPanel rightPanel = new JPanel(new GridLayout(2, 2));
            for (JFXPanel fxPanel : fxPanels) {
                rightPanel.add(fxPanel);
            }

            container.add(rightPanel, BorderLayout.EAST);
            container.revalidate();
            container.repaint();
        });
    }

    private void drawChart(JFXPanel fxPanel, String chartTitle, String xAxisLabel, ValueExtractor extractor) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle(chartTitle);
        chart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (DroneType drone : dronesList) {
            series.getData().add(new XYChart.Data<>(String.valueOf(drone.getId()), extractor.extract(drone)));
        }

        chart.getData().add(series);
        fxPanel.setScene(new Scene(chart));
    }

    interface ValueExtractor {
        double extract(DroneType drone);
    }
}
