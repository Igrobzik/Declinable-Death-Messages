package me.declinabledeathmessages;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.declinabledeathmessages.common.Common;

public class DeclinableDeathMessages implements ModInitializer {

    public static final Logger LOGGER =
            LoggerFactory.getLogger(Common.MOD_ID);

    @Override
    public void onInitialize() {

        Common.init();

        LOGGER.info("Declinable Death Messages loaded!");

        ModContainer modContainer = FabricLoader.getInstance()
                .getModContainer(Common.MOD_ID)
                .orElseThrow();

        ResourceLoader.registerBuiltinPack(
                Identifier.fromNamespaceAndPath(Common.MOD_ID, "russian"),
                modContainer,
                Component.translatable(
                        "declinabledeathmessages.resourcepack.russian"
                ),
                PackActivationType.DEFAULT_ENABLED
        );
    }
}