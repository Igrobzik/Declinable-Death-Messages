package me.declinabledeathmessages.client;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.declinabledeathmessages.config.ConfigManager;

import net.minecraft.client.Minecraft;

import java.util.Optional;

public class ConfigScreen {

    public static Screen create(Screen parent) {

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("declinable-death-messages.config.title"));

        ConfigCategory general = builder.getOrCreateCategory(
                Component.translatable("declinable-death-messages.config.general")
        );

        ConfigEntryBuilder entryBuilder = ConfigEntryBuilder.create();

        general.addEntry(
                entryBuilder.startBooleanToggle(
                        Component.translatable("declinable-death-messages.config.names"),
                        ConfigManager.config.namesDeclension
                )
                .setDefaultValue(true)
                .setTooltipSupplier(() -> {
                    boolean advancedTooltips =
                            Minecraft.getInstance().options.advancedItemTooltips;

                    return Optional.of(new Component[] {
                            Component.translatable("declinable-death-messages.config.names.tooltip"),
                            advancedTooltips
                                    ? Component.translatable(
                                            "declinable-death-messages.config.names.tooltip.enabledAdvancedTooltips"
                                    )
                                    : Component.translatable(
                                            "declinable-death-messages.config.names.tooltip.disabledAdvancedTooltips",
                                            Component.keybind("key.debug.modifier"),
                                            Component.keybind("key.debug.showAdvancedTooltips")
                                    )
                    });
                })
                .setSaveConsumer(value -> {
                    ConfigManager.config.namesDeclension = value;
                    ConfigManager.save();
                })
                .build()
        );

        return builder.build();
    }
}