package net.lighthouse.settings;

import java.util.HashMap;

/**
 * Default settings to fall back to when something goes wrong with
 * initiating the user settings.
 *
 * @author Christoph Fricke
 */
final class DefaultSettings {

    /**
     * HashMap which stores the settings.
     */
    private HashMap<String, String> settings;

    /**
     * Array with all valid setting keys.
     */
    final String[] validSettings = {
        "token", "web-view", "lighthouse-view", "particles"
    };

    /**
     * Creates new default settings.
     */
    DefaultSettings() {
        settings = new HashMap<>();

        settings.put(validSettings[0], "API-TOK_0Hup-ruQ4-/Vz2-YJTt-g1Jx");
        settings.put(validSettings[1], "false");
        settings.put(validSettings[2], "false");
        settings.put(validSettings[3], "false");
    }

    /**
     * Returns the value for a given setting.
     *
     * @param setting A setting string.
     *
     * @return The corresponding default value.
     * @throws IllegalArgumentException If the value is {@code null}. This also means
     *                                  that the setting was invalid.
     */
    String get(String setting) {
        assert setting != null : "A setting can not be null!";

        String value = settings.get(setting);

        if (value == null) {
            throw new IllegalArgumentException("This setting does not exists");
        } else {
            return value;
        }
    }

    /**
     * Returns a HashMap which contains all the settings mapped to their keys.
     *
     * @return HashMap with the default settings.
     */
    HashMap getAllSettings() {
        return settings;
    }
}
