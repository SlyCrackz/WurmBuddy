package com.crackz.gui;

import javax.swing.*;
import java.awt.*;

public class WurmBuddyMainPanelGUI extends JPanel {
    private SettingsWindow settingsWindow;

    // Enum to represent each button type and its properties.
    private enum ButtonType {
        TRIGGERS("Triggers"),
        MOCK1("Mock Button 1"),
        MOCK2("Mock Button 2"),
        MOCK3("Mock Button 3"),
        MOCK4("Mock Button 4"),
        MOCK5("Mock Button 5");

        private final String label;

        ButtonType(String label) {
            this.label = label;
        }
    }

    public WurmBuddyMainPanelGUI() {
        initGUI();
    }

    private void initGUI() {
        this.setLayout(new GridLayout(2, 3, 10, 10));
        this.setBackground(Color.DARK_GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addButtons();
    }

    private void addButtons() {
        for (ButtonType buttonType : ButtonType.values()) {
            this.add(createButton(buttonType));
        }
    }
    public JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");

        JMenuItem settingsMenuItem = new JMenuItem("Settings");
        settingsMenuItem.addActionListener(e -> {
            if (settingsWindow == null || !settingsWindow.isDisplayable()) {
                settingsWindow = new SettingsWindow();
            } else {
                settingsWindow.toFront();
            }
        });

        optionsMenu.add(settingsMenuItem);
        menuBar.add(optionsMenu);
        return menuBar;
    }



    private JButton createButton(ButtonType buttonType) {
        JButton button = new JButton(buttonType.label);
        button.setPreferredSize(new Dimension(150, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 16));

        // You can add individual button actions like this.
        switch (buttonType) {
            case TRIGGERS:
                button.addActionListener(e -> openTriggersGUI());
                break;
            // Add other cases as necessary for other buttons.
        }

        return button;
    }

    private void openTriggersGUI() {
        // Logic for opening Triggers GUI.
    }
}
