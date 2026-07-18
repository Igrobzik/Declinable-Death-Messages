package me.declinabledeathmessages.client;

import net.fabricmc.api.ClientModInitializer;
import me.declinabledeathmessages.config.ConfigManager;

public class DeclinableDeathMessagesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ConfigManager.load();
    }
}