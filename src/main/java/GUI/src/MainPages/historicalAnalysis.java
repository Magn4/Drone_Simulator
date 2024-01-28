package GUI.src.MainPages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class historicalAnalysis extends JFrame {

    private JButton earlierButton;
    private JButton laterButton;
    private JButton earlier5MinButton;
    private JButton later5MinButton;
    private JTextArea infoTextArea;
    private LocalDateTime dateTime;

    public historicalAnalysis() {
        setTitle("Historical Analysis");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();

        setLocationRelativeTo(null);
        setVisible(true);

    }
    

    private void initComponents() {
        earlierButton = new JButton("<<5");
        laterButton = new JButton("<1");
        earlier5MinButton = new JButton(">1");
        later5MinButton = new JButton(">>5");
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        dateTime = LocalDateTime.now();

        earlier5MinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTime = dateTime.minusMinutes(5);
                updateInfoTextArea();
            }
        });

        earlierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTime = dateTime.minusMinutes(1);
                updateInfoTextArea();
            }
        });

        laterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTime = dateTime.plusMinutes(1);
                updateInfoTextArea();
            }
        });

        later5MinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTime = dateTime.plusMinutes(5);
                updateInfoTextArea();
            }
        });
    }

    private void addComponents() {
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new GridLayout(0, 1));

        leftPanel.add(createMenuButton("Home"));
        leftPanel.add(createMenuButton("Drone Catalog"));
        leftPanel.add(createMenuButton("Flight Dynamics"));
        leftPanel.add(createMenuButton("Historical Analysis"));
        leftPanel.setPreferredSize(new Dimension(170, getHeight()));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.decode("#008e9b"));
        topPanel.setPreferredSize(new Dimension(getWidth(), 60));

        topPanel.add(earlierButton);
        topPanel.add(laterButton);
        topPanel.add(earlier5MinButton);
        topPanel.add(later5MinButton);

        add(leftPanel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(infoTextArea), BorderLayout.CENTER);

        updateInfoTextArea(); // Update infoTextArea initially
    }

    private JButton createMenuButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#008e9b"));
        button.setForeground(Color.WHITE);

        int topPadding = 10;
        int leftPadding = 20;
        int bottomPadding = 10;
        int rightPadding = 20;
        button.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));

        button.setBorderPainted(false);

        return button;
    }

    private void updateInfoTextArea() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("2023-12-26 09:07:00.618782+01:00");
        String formattedDateTime = dateTime.format(formatter);
        // Update the infoTextArea with the formatted time
        infoTextArea.setText("Displaying data for: " + formattedDateTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(historicalAnalysis::new);
    }
}