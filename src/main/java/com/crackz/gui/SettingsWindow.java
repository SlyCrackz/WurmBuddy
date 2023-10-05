package com.crackz.gui;

import com.crackz.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SettingsWindow extends JFrame {
    private JLabel selectedDirectoryLabel;

    public SettingsWindow() {
        setTitle("Settings");
        setSize(400, 300); // Adjust as needed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);  // Centers the window

        initializeComponents();

        // Load the saved directory if it exists
        String savedDirectory = Settings.getPathToDirectory();
        if (!savedDirectory.isEmpty()) {
            selectedDirectoryLabel.setText(savedDirectory);
        }

        setVisible(true);
    }

    private void initializeComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        selectedDirectoryLabel = new JLabel("No directory selected");
        JButton selectDirectoryButton = new JButton("Choose Directory");
        selectDirectoryButton.addActionListener(e -> chooseDirectory());

        panel.add(selectedDirectoryLabel, BorderLayout.CENTER);
        panel.add(selectDirectoryButton, BorderLayout.SOUTH);

        add(panel);
    }

    private void chooseDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            selectedDirectoryLabel.setText(selectedDirectory.getAbsolutePath());

            // Save the selected directory using the Settings utility
            Settings.setPathToDirectory(selectedDirectory.getAbsolutePath());
        }
    }
}
