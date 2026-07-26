package me.declinabledeathmessages;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class DeclinableDeathMessages implements ModInitializer {

    public static final String MOD_ID = "declinable-death-messages";

    @Override
    public void onInitialize() {
        System.out.println("Declinable Death Messages loaded!");

        ModContainer modContainer = FabricLoader.getInstance()
                .getModContainer(MOD_ID)
                .orElseThrow();

        ResourceLoader.registerBuiltinPack(
                Identifier.fromNamespaceAndPath(MOD_ID, "russian"),
                modContainer,
                Component.translatable("declinable-death-messages.resourcepack.russian"),
                PackActivationType.DEFAULT_ENABLED
        );
    }
}