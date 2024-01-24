package GUI.Demo;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainApplication extends JFrame {

    private ProductCatalog productCatalogSwing;

    public MainApplication() {
        setTitle("Drone Catalog");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object productCatalog = new ProductCatalog();

        // Set up the main UI
        JTable table = new JTable();
        JButton nextButton = new JButton("Next");
        JButton prevButton = new JButton("Previous");

        // Add event handlers for next and previous buttons
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // productCatalog.showNextPage();
            }
        });

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 // productCatalog).showPreviousPage();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

      //   productCatalog.initializeTable(table);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout(10, 10));
        rootPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        rootPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(rootPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApplication::new);
    }
}
