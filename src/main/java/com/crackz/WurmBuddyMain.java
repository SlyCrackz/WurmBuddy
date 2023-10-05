package com.crackz;

import com.crackz.gui.WurmBuddyMainPanelGUI;
import com.crackz.logger.LoggerManager;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class WurmBuddyMain {
    public static void main(String[] args) {
        setupLookAndFeel();

        // Get the path from settings
        String path = Settings.getPathToDirectory();
        if (path.isEmpty()) {
            // Handle cases where the path isn't set, maybe provide a default value or show an error message.
        } else {
            LoggerManager loggerManager = new LoggerManager(path);
            loggerManager.startMonitoring();
        }

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("WurmBuddy");
        WurmBuddyMainPanelGUI mainPanel = new WurmBuddyMainPanelGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(mainPanel.createMenu()); // Set the menu bar.
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);  // Centers the window
        frame.setVisible(true);
    }
}
