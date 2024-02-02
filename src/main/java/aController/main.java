package aController;

import javax.swing.*;

import GUI.src.MainPages.aLogin.Login;


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