package GUI.src.MainPages.aLogin;

import javax.swing.*;

import API.Fetcher.APIFetcher;
import API.Fetcher.FileWriterUtil;
import API.Fetcher.URL_Maker;
import GUI.src.MainPages.Home.Home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    public static String Token;

    private JTextField tokenField;
    private JButton loginButton;

    public Login() {
        setTitle("Login Page");
        setSize(900, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        tokenField = new JTextField(10);
        loginButton = new JButton("Login");
        tokenField.setFont(new Font("Arial", Font.PLAIN, 12));
        

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onLoginButtonClick();
            }
        });
    }

    private void addComponents() {
    setLayout(new BorderLayout()); // Use BorderLayout for the frame

    // Create the main panel with BorderLayout
    JPanel mainPanel = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Create a gradient paint for the background
            GradientPaint gradient = new GradientPaint(0, 0, new Color(155, 190, 200), getWidth(), getHeight(), Color.ORANGE);
            ((Graphics2D) g).setPaint(gradient);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    // Create the photoPanel
    JPanel photoPanel = new JPanel(new BorderLayout());
    photoPanel.setBackground(new Color(155, 190, 200));

    String imagePath = "/GUI/src/Resources/MainDrone.png";
    java.net.URL imageURL = getClass().getResource(imagePath);

    if (imageURL != null) {
        ImageIcon imageIcon = new ImageIcon(imageURL);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setPreferredSize(new Dimension(510, 400)); 
        photoPanel.add(imageLabel, BorderLayout.CENTER);
    } else {
        System.err.println("Image not found: " + imagePath);
    }
    
    mainPanel.add(photoPanel, BorderLayout.WEST); // Add photoPanel to the west of the main panel

    // Create inputPanel
    JPanel inputPanel = new JPanel(new GridBagLayout());
    inputPanel.setBackground(new Color(155, 190, 200));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(8, 8, 8, 8);

    JLabel enterTokenLabel = new JLabel("Enter Token (Format: Token xxxxxxxxxxxxxxxx): ");
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST; // Align label to the left
    inputPanel.add(enterTokenLabel, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    tokenField.setPreferredSize(new Dimension(500, 20));
    inputPanel.add(tokenField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0; // Reset weight
    loginButton.setPreferredSize(new Dimension(90, 30));
    inputPanel.add(loginButton, gbc);

    mainPanel.add(inputPanel, BorderLayout.CENTER); // Add inputPanel to the center of the main panel

    add(mainPanel, BorderLayout.CENTER); // Add mainPanel to the center of the frame
}


    public static void setToken(String newToken) {
        Token = newToken;
    }

    public static String getToken() {
        return Token;
    }

    // Action listener for login button
    private void onLoginButtonClick() {
        String enteredToken = tokenField.getText();
    
        setToken(enteredToken);
        // String Token = "Token "  + enteredToken;
        
       
        
        FileWriterUtil.writeToFile("This is the entered token: " + enteredToken, "token.md");
        APIFetcher apiFetcher = new APIFetcher();
        // String responseContent = apiFetcher.FetchAPI(apiUrl);

        String URL = URL_Maker.getUrlExtension("Drones", 0, 25);

        
        String responseContent = apiFetcher.FetchAPI(URL, enteredToken);

        int responseCode = apiFetcher.getResponseCode();
        System.out.println(responseCode);
        System.out.println(responseContent);

        String responseCodeString = String.valueOf(responseCode);
        FileWriterUtil.writeToFile(responseCodeString, "response.md");

        if (responseCode == 200) {
            dispose();
            Home.main();
          //  JOptionPane.showMessageDialog(this, "Login Successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid token.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}
  
