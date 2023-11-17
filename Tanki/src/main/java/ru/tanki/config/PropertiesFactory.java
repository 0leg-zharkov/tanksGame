package ru.tanki.config;

import java.io.IOException;
import java.io.InputStream;

public final class PropertiesFactory {

    private static DatabaseProperties properties;

    private PropertiesFactory() {}

    public synchronized static DatabaseProperties getProperties() {
        if (properties == null) {
            init();
        }
        return properties;
    }

    private static void init() {
        String filePropertiesName = "application.properties";
        properties = new DatabaseProperties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream(filePropertiesName)) {
            properties.load(stream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1); // выход , -1 - значит проблема
        }
    }
}
