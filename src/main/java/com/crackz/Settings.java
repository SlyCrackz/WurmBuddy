package com.crackz;

import java.util.prefs.Preferences;

public class Settings {

    private static final Preferences PREFERENCES = Preferences.userNodeForPackage(Settings.class);
    private static final String PATH_KEY = "path_to_directory";

    public static void setPathToDirectory(String path) {
        PREFERENCES.put(PATH_KEY, path);
    }

    public static String getPathToDirectory() {
        // Returning a default empty string if the path isn't set.
        return PREFERENCES.get(PATH_KEY, "");
    }
}