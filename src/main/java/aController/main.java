package aController;

import GUI.src.MainPages.Login.Login;
import javax.swing.*;

public class main {
    public static void mainClass(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login();
            }
        });
    }
}