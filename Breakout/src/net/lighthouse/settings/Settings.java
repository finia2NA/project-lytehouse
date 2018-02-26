package net.lighthouse.settings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * This class provides user settings for the app. Default settings will be provided
 * if the file cannot be read or for settings that are missing.
 *
 * @author Christoph Fricke
 */
public final class Settings {

    private DefaultSettings defaultSettings = new DefaultSettings();

    private HashMap<String, String> userSettings;

    /**
     * We do not want objects of the type Settings to exits.
     */
    private Settings() {

    }

    /**
     * Inits settings.
     *
     * @param settingsFile File to read the setting.
     */
    public void readUserSettings(String settingsFile) {
        userSettings = defaultSettings.getSettings();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(settingsFile));

            while (true) {
                String setting = reader.readLine();

                if (setting == null) {
                    break;
                } else if (setting.equals("") || setting.charAt(0) == '#') {
                    continue;
                } else if (!Pattern.compile("[a-zA-Z\\-]+ *= *[a-zA-Z\\-]+").matcher(setting.trim()).matches()) {
                    continue;
                }

                String[] settingKeyValue = setting.split("=");

                String key = settingKeyValue[0].trim();
                String value = settingKeyValue[1].trim();

                if (isValidSettingKey(key)) {
                    userSettings.put(key, value);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read user settings! Will use default settings.");
        }
    }

    /**
     * Get the value of a settings. Throws an exceptions if user settings are not initialized.
     * Call {@code readUserSettings()} first.
     *
     * @param key Key of the settings.
     *
     * @return The setting value if the key exists or {@code null} if the key does not exists.
     */
    public String getSetting(String key) {
        if (userSettings == null) {
            throw new IllegalStateException("User Settings are not initialized yet. Call readUserSettings() first.");
        }

        return userSettings.get(key);
    }

    /**
     * Check if a key is a valid setting.
     *
     * @param key Key to check
     *
     * @return True if key valid. False otherwise.
     */
    private boolean isValidSettingKey(String key) {
        for (String defaultKey : defaultSettings.validSettings) {
            if (defaultKey.equals(key)) {
                return true;
            }
        }

        return false;
    }
}
