package me.declinabledeathmessages.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path PATH =
            FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve("declinable-death-messages.json");

    public static ModConfig config;


    public static void load() {

        try {

            if (Files.exists(PATH)) {

                String json = Files.readString(PATH);
                config = GSON.fromJson(json, ModConfig.class);

            } else {

                config = new ModConfig();
                save();

            }

        } catch (IOException e) {

            e.printStackTrace();
            config = new ModConfig();

        }
    }


    public static void save() {

        try {

            Files.writeString(
                    PATH,
                    GSON.toJson(config)
            );

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}