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
                .setTitle(Component.translatable("declinabledeathmessages.config.title"));

        ConfigCategory general = builder.getOrCreateCategory(
                Component.translatable("declinabledeathmessages.config.general")
        );

        ConfigEntryBuilder entryBuilder = ConfigEntryBuilder.create();

        general.addEntry(
                entryBuilder.startBooleanToggle(
                        Component.translatable("declinabledeathmessages.config.names"),
                        ConfigManager.config.namesDeclension
                )
                .setDefaultValue(true)
                .setTooltipSupplier(() -> {
                    boolean advancedTooltips =
                            Minecraft.getInstance().options.advancedItemTooltips;

                    return Optional.of(new Component[] {
                            Component.translatable("declinabledeathmessages.config.names.tooltip"),
                            Component.translatable("declinabledeathmessages.config.tooltip.noKey"),
                            Component.translatable("declinabledeathmessages.config.tooltip.packAbuse"),
                            advancedTooltips
                                    ? Component.translatable(
                                            "declinabledeathmessages.config.tooltip.enabledAdvancedTooltips"
                                    )
                                    : Component.translatable(
                                            "declinabledeathmessages.config.tooltip.disabledAdvancedTooltips",
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

        general.addEntry(
                entryBuilder.startBooleanToggle(
                        Component.translatable("declinabledeathmessages.config.entities"),
                        ConfigManager.config.entitiesDeclension
                )
                .setDefaultValue(true)
                .setTooltipSupplier(() -> {
                    boolean advancedTooltips =
                            Minecraft.getInstance().options.advancedItemTooltips;

                    return Optional.of(new Component[] {
                            Component.translatable("declinabledeathmessages.config.entities.tooltip"),
                            Component.translatable("declinabledeathmessages.config.tooltip.noKey"),
                            Component.translatable("declinabledeathmessages.config.tooltip.packAbuse"),
                            advancedTooltips
                                    ? Component.translatable(
                                            "declinabledeathmessages.config.tooltip.enabledAdvancedTooltips"
                                    )
                                    : Component.translatable(
                                            "declinabledeathmessages.config.tooltip.disabledAdvancedTooltips",
                                            Component.keybind("key.debug.modifier"),
                                            Component.keybind("key.debug.showAdvancedTooltips")
                                    )
                    });
                })
                .setSaveConsumer(value -> {
                    ConfigManager.config.entitiesDeclension = value;
                    ConfigManager.save();
                })
                .build()
        );

        general.addEntry(
                entryBuilder.startBooleanToggle(
                        Component.translatable("declinabledeathmessages.config.message"),
                        ConfigManager.config.messageInflection
                )
                .setDefaultValue(true)
                .setTooltipSupplier(() -> {
                    return Optional.of(new Component[] {
                            Component.translatable("declinabledeathmessages.config.message.tooltip"),
                            Component.translatable("declinabledeathmessages.config.message.tooltip.noKey"),
                            Component.translatable("declinabledeathmessages.config.tooltip.packAbuse"),
                    });
                })
                .setSaveConsumer(value -> {
                    ConfigManager.config.messageInflection = value;
                    ConfigManager.save();
                })
                .build()
        );

        general.addEntry(
                entryBuilder.startBooleanToggle(
                        Component.translatable("declinabledeathmessages.config.originalMessageHover"),
                        ConfigManager.config.originalMessageHover
                )
                .setDefaultValue(false)
                .setTooltipSupplier(() -> {
                        return Optional.of(new Component[] {
                                Component.translatable(
                                        "declinabledeathmessages.config.originalMessageHover.tooltip"
                                )
                        });
                        })
                .setSaveConsumer(value -> {
                    ConfigManager.config.originalMessageHover = value;
                    ConfigManager.save();
                })
                .build()
        );

        return builder.build();
    }
}