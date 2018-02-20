package net.lighthouse.settings;

import java.util.HashMap;

/**
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
}
