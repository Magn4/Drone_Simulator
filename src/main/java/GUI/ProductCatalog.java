import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

public class ProductCatalog extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public ProductCatalog() {
        setTitle("Product Catalog");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeTable();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);

        // Fetch data from the RESTful endpoint
        fetchDataFromEndpoint();
    }

    private void initializeTable() {
        String[] columnNames = {"ID", "Drone Type", "Creation Datum", "Serial Number", "Carriage Weight", "Carriage Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
    }

    private void fetchDataFromEndpoint() {
        try {
            // Replace "your_endpoint_url" with the actual URL of your RESTful endpoint
            URL url = new URL("https://dronesim.facets-labs.com/api/drones/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(response.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String droneType = jsonObject.getString("droneType");
                String creationDatum = jsonObject.getString("creationDatum");
                String serialNumber = jsonObject.getString("serialNumber");
                double carriageWeight = jsonObject.getDouble("carriageWeight");
                String carriageType = jsonObject.getString("carriageType");

                // Add a row to the table model
                Vector<Object> row = new Vector<>();
                row.add(id);
                row.add(droneType);
                row.add(creationDatum);
                row.add(serialNumber);
                row.add(carriageWeight);
                row.add(carriageType);

                tableModel.addRow(row);
            }

            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in a real application
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductCatalog::new);
    }
}
