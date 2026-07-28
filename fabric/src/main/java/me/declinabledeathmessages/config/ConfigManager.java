package me.declinabledeathmessages.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigManager {

    private static final Logger LOGGER =
            LoggerFactory.getLogger("Declinable Death Messages/Config");


    private static final Gson GSON =
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create();


    private static final Path PATH =
            FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve("declinabledeathmessages.json");


    public static ModConfig config;


    private static String lastSavedConfig = "";


    public static void load() {

        try {

            if (Files.exists(PATH)) {

                String json =
                        Files.readString(PATH);


                config =
                        GSON.fromJson(
                                json,
                                ModConfig.class
                        );


                lastSavedConfig = json;


                LOGGER.info("Config loaded");

            } else {

                config =
                        new ModConfig();


                save();

                LOGGER.info("Created default config");

            }


        } catch (IOException e) {

            LOGGER.error(
                    "Failed to load config",
                    e
            );


            config =
                    new ModConfig();
        }
    }



    public static void save() {

        String json =
                GSON.toJson(config);


        if (json.equals(lastSavedConfig)) {
            return;
        }


        try {

            Files.writeString(
                    PATH,
                    json
            );


            lastSavedConfig = json;


            LOGGER.info("Config saved");


        } catch (IOException e) {

            LOGGER.error(
                    "Failed to save config",
                    e
            );
        }
    }
}