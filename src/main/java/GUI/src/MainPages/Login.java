package GUI.src.MainPages;

import javax.swing.*;
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
        gbc.insets = new Insets(4, 4, 4, 4);

        JPanel innerPanel = new JPanel(new GridBagLayout());
        innerPanel.setBackground(new Color(230, 212, 194)); 

        JLabel enterTokenLabel = new JLabel("Enter Token:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        innerPanel.add(enterTokenLabel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(tokenField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(loginButton, gbc);

        innerPanel.setPreferredSize(new Dimension(600, 250));

        panel.add(innerPanel, gbc);

        return panel;
    }

    private void onLoginButtonClick() {
        String enteredToken = tokenField.getText();
        if (!enteredToken.isEmpty()) {
            // Add login logic here
            JOptionPane.showMessageDialog(this, "Login Successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid token.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}
