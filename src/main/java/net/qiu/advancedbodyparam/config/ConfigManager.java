package net.qiu.advancedbodyparam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.qiu.advancedbodyparam.QsAdvancedBodyParameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDir();
    private static final File CONFIG_FILE = CONFIG_DIR.resolve(QsAdvancedBodyParameters.MOD_ID + "_config" + ".json").toFile();

    public static ModConfig INSTANCE = new ModConfig();

    public static void loadConfig() {
        if (!CONFIG_FILE.exists()) {
            // File doesn't exist, create it with default values
            saveConfig();
            return;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            INSTANCE = GSON.fromJson(reader, ModConfig.class);
        } catch (IOException e) {
            System.err.println("[MyMod] Failed to load config, using defaults: " + e.getMessage());
            QsAdvancedBodyParameters.LOGGER.info("Failed to load config, using defaults: " + e.getMessage());
            INSTANCE = new ModConfig(); // Fallback to defaults if broken
        }
    }

    private static void saveConfig() {

        File parentDir = CONFIG_FILE.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (java.io.FileWriter writer = new java.io.FileWriter(CONFIG_FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            QsAdvancedBodyParameters.LOGGER.error("Failed to save config: " + e.getMessage(), e);
        }
    }
}
