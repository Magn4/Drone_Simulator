package GUI.src.MainPages;

import javax.swing.*;

import API.Fetcher.APIFetcher;
import API.Fetcher.FileWriterUtil;
import API.Fetcher.URL_Maker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Login extends JFrame {

    private JTextField tokenField;
    private JButton loginButton;

    public Login() {
        setTitle("Login Page");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();

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
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel bluePanel = createInputPanel();
        mainPanel.add(bluePanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JPanel innerPanel = new JPanel(new GridBagLayout());
        innerPanel.setBackground(new Color(230, 212, 194)); 
    
        JLabel enterTokenLabel = new JLabel("Enter Token:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        innerPanel.add(enterTokenLabel, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; 
        tokenField.setPreferredSize(new Dimension(200,30));
        innerPanel.add(tokenField, gbc);
    
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0; // Reset weight
        loginButton.setPreferredSize(new Dimension(90, 30));
        innerPanel.add(loginButton, gbc);
    
        innerPanel.setPreferredSize(new Dimension(600, 250));
        panel.add(innerPanel,gbc);
    
        return panel;
    }
    
    private void onLoginButtonClick() {
        String enteredToken = tokenField.getText();
    
        // String Token= "Token 6ffe7e815e07b6ede78cade7617454eeb944d168";

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
            Home.main(new String[0]);
          //  JOptionPane.showMessageDialog(this, "Login Successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid token.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}
  
