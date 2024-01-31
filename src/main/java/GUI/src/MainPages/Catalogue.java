package GUI.src.MainPages;

import API.Fetcher.APIFetcher;
import API.Fetcher.URL_Maker;
import Formatter.Drones.DroneType;
import Formatter.JsonFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Catalogue extends JFrame {

    private JComboBox<Integer> droneComboBox;
    private JButton getInfoButton;
    private JLabel infoTextArea;
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
        infoTextArea = new JLabel();
        //infoTextArea.setEditable(false);
        setDroneList();

        droneComboBox = new JComboBox<>();
        for (int i = 71; i <= 90; i++) {
            droneComboBox.addItem(i);
        }
        getInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
  
              displayDroneInfo();


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

		JPanel sidePanel = new JPanel() ;
		sidePanel.setBackground(new Color(54, 33, 89));
		sidePanel.setBounds(0, 80, 200, 2000);

        back backButton = new back();
		move moveButton = new move();
		refresh refreshButton = new refresh();
		start startButton = new start();
		catalog catalogButton = new catalog();
		dynamics dynamicsButton = new dynamics();
		historical historicalButton = new historical();

        JPanel mainPanel = new JPanel() ;
		mainPanel.setBackground(new Color(54, 33, 89));
		mainPanel.setBounds(500, 200, 200, 200);



        /*leftPanel.add(createMenuButton("Home"));
        leftPanel.add(createMenuButton("Drone Catalog"));
        leftPanel.add(createMenuButton("Flight dynamics"));
        leftPanel.add(createMenuButton("Historical Analysis"));
        leftPanel.setPreferredSize(new Dimension(170, getHeight()));*/

        /*JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.decode("#008e9b"));
        topPanel.setPreferredSize(new Dimension(getWidth(), 60));*/

        JPanel topPanel = new JPanel() ;
		topPanel.setBackground(new Color(54, 33, 89));
		topPanel.setBounds(0, 0, 2000, 80);


        JLabel chooseDroneLabel = new JLabel("Choose Drone ID:");
        chooseDroneLabel.setForeground(Color.WHITE);
        topPanel.add(chooseDroneLabel);
        topPanel.add(droneComboBox);
        topPanel.add(getInfoButton);
        add(backButton.getBackButton());
		add(moveButton.getMoveButton());
		add(refreshButton.getRefreshButton());
		add(startButton.getStartButton());
		add(catalogButton.getCatalogButton());
		add(dynamicsButton.getdynamicsButton());
		add(historicalButton.getHistoricalButton());


        add(sidePanel);
        add(topPanel);
        add(mainPanel);
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
        infoText.append("\t\t\t Drone ID: ").append(dronesList.get(i - 71).getId()).append("\n");
        infoText.append("Manufacturer: ").append(dronesList.get(i - 71).getManufacturer()).append("\n");
        infoText.append("Type Name: ").append(dronesList.get(i - 71).getTypename()).append("\n");
        infoText.append("Weight: ").append(dronesList.get(i - 71).getWeight()).append("\n");
        infoText.append("Max Speed: ").append(dronesList.get(i - 71).getMax_speed()).append("\n");
        infoText.append("Battery Capacity: ").append(dronesList.get(i - 71).getBattery_capacity()).append("\n");
        infoText.append("Control Range: ").append(dronesList.get(i - 71).getControl_range()).append("\n");
        infoText.append("Max Carriage: ").append(dronesList.get(i - 71).getMax_carriage()).append("\n");




        
        infoTextArea.setText(infoText.toString());
        infoTextArea.setBounds(500, 200, 200, 200);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Catalogue::new);
    }
<<<<<<< HEAD
}
=======
    
}
>>>>>>> 05e4115b249e25fa49b07a12f607bc8a7a970821
