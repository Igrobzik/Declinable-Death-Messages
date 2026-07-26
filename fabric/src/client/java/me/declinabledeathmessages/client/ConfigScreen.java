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
                            Component.translatable("declinable-death-messages.config.tooltip.noKey"),
                            Component.translatable("declinable-death-messages.config.tooltip.packAbuse"),
                            advancedTooltips
                                    ? Component.translatable(
                                            "declinable-death-messages.config.tooltip.enabledAdvancedTooltips"
                                    )
                                    : Component.translatable(
                                            "declinable-death-messages.config.tooltip.disabledAdvancedTooltips",
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
                        Component.translatable("declinable-death-messages.config.entities"),
                        ConfigManager.config.entitiesDeclension
                )
                .setDefaultValue(true)
                .setTooltipSupplier(() -> {
                    boolean advancedTooltips =
                            Minecraft.getInstance().options.advancedItemTooltips;

                    return Optional.of(new Component[] {
                            Component.translatable("declinable-death-messages.config.entities.tooltip"),
                            Component.translatable("declinable-death-messages.config.tooltip.noKey"),
                            Component.translatable("declinable-death-messages.config.tooltip.packAbuse"),
                            advancedTooltips
                                    ? Component.translatable(
                                            "declinable-death-messages.config.tooltip.enabledAdvancedTooltips"
                                    )
                                    : Component.translatable(
                                            "declinable-death-messages.config.tooltip.disabledAdvancedTooltips",
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
                        Component.translatable("declinable-death-messages.config.message"),
                        ConfigManager.config.messageInflection
                )
                .setDefaultValue(true)
                .setTooltipSupplier(() -> {
                    return Optional.of(new Component[] {
                            Component.translatable("declinable-death-messages.config.message.tooltip"),
                            Component.translatable("declinable-death-messages.config.message.tooltip.noKey"),
                            Component.translatable("declinable-death-messages.config.tooltip.packAbuse"),
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
                        Component.translatable("declinable-death-messages.config.originalMessageHover"),
                        ConfigManager.config.originalMessageHover
                )
                .setDefaultValue(false)
                .setTooltipSupplier(() -> {
                    boolean advancedTooltips =
                            Minecraft.getInstance().options.advancedItemTooltips;

                    return Optional.of(new Component[] {
                            advancedTooltips
                                    ? Component.translatable(
                                            "declinable-death-messages.config.originalMessageHover.tooltip.enabledAdvancedTooltips",
                                            Component.translatable("declinable-death-messages.config.originalMessageHover.tooltip")
                                    )
                                    : Component.translatable(
                                            "declinable-death-messages.config.originalMessageHover.tooltip.disabledAdvancedTooltips",
                                            Component.translatable("declinable-death-messages.config.originalMessageHover.tooltip"),
                                            Component.keybind("key.debug.modifier"),
                                            Component.keybind("key.debug.showAdvancedTooltips")
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