package GUI.src.MainPages;

import API.Fetcher.APIFetcher;
import API.Fetcher.URL_Maker;
import API.Formatter.JsonFormatter;
import API.Formatter.Drones.DroneType;
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
        setSize(1920, 1080);
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
                Charts charts = new Charts(dronesList);
                charts.drawBarCharts(getContentPane());
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

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null); 

        JButton homeButton = createMenuButton("Home");
        homeButton.setBounds(0, 100, 200, 50); 
        leftPanel.add(homeButton);
        JButton catalogueButton = createMenuButton("Drone Catalog");
        catalogueButton.setBounds(0, 200, 200, 50); 
        leftPanel.add(catalogueButton);

        JButton dynamicsButton = createMenuButton("Flight dynamics");
        dynamicsButton.setBounds(0, 300, 200, 50); 
        leftPanel.add(dynamicsButton);

        JButton analysisButton = createMenuButton("Historical Analysis");
        analysisButton.setBounds(0, 400, 200, 50); 
        leftPanel.add(analysisButton);

        leftPanel.add(createMenuButton("Home"));
        leftPanel.add(createMenuButton("Drone Catalog"));
        leftPanel.add(createMenuButton("Flight dynamics"));
        leftPanel.add(createMenuButton("Historical Analysis"));
        leftPanel.setPreferredSize(new Dimension(220, getHeight()));
        leftPanel.setBackground(Color.decode("#164863"));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.decode("#164863"));
        topPanel.setPreferredSize(new Dimension(getWidth(), 100));

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
        button.setBackground(Color.decode("#164863"));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(5, 2));

        int topPadding = 5;
        int leftPadding = 5;
        int bottomPadding = 5;
        int rightPadding = 5;
        button.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));

        if (buttonText.equals("Home")) {
            ImageIcon icon4 = new ImageIcon("src/main/java/GUI/src/Resources/home.png");
            button.setIcon(icon4);
        }
        else if (buttonText.equals("Drone Catalog")) {
            ImageIcon icon5 = new ImageIcon("src/main/java/GUI/src/Resources/list.png");
            button.setIcon(icon5);
        }
        else if (buttonText.equals("Flight dynamics")) {
            ImageIcon icon6 = new ImageIcon("src/main/java/GUI/src/Resources/drone.png");
            button.setIcon(icon6);
        }
        else if (buttonText.equals("Historical Analysis")) {
            ImageIcon icon7 = new ImageIcon("src/main/java/GUI/src/Resources/kalender.png");
            button.setIcon(icon7);
        }

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
    

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Catalogue::new);
    }
    
}