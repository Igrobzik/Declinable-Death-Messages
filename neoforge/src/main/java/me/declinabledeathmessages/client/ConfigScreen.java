package me.declinabledeathmessages.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import me.declinabledeathmessages.config.NeoForgeConfig;

import java.util.function.Supplier;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigScreen extends Screen {

    private final Screen parent;

    private final java.util.List<Button> buttons = new java.util.ArrayList<>();

    public ConfigScreen(Screen parent) {
        super(Component.translatable(
            "declinabledeathmessages.config.title"
        ));

        this.parent = parent;
    }

    private Component getBooleanButtonText(String key, boolean value) {
        return Component.translatable(
            value ? "options.on.composed" : "options.off.composed",
            Component.translatable(key)
        );
    }

    private Button createBooleanButton(
        String translationKey,
        ModConfigSpec.BooleanValue value,
        Supplier<Component> tooltip,
        int y
    ) {
        Button button = Button.builder(
            getBooleanButtonText(
                translationKey,
                value.get()
            ),
            b -> {
                value.set(!value.get());
                NeoForgeConfig.SPEC.save();

                b.setMessage(
                    getBooleanButtonText(
                        translationKey,
                        value.get()
                    )
                );
            }
        )
        .tooltip(Tooltip.create(tooltip.get()))
        .bounds(
            this.width / 2 - 100,
            y,
            200,
            20
        )
        .build();

        buttons.add(button);
        return button;
    }

    private Component getNamesTooltip() {
        boolean advancedTooltips =
            Minecraft.getInstance().options.advancedItemTooltips;

        return Component.translatable(
            "declinabledeathmessages.config.names.tooltip"
        )
        .append(Component.literal("\n"))
        .append(Component.translatable(
            "declinabledeathmessages.config.tooltip.noKey"
        ))
        .append(Component.literal("\n"))
        .append(Component.translatable(
            "declinabledeathmessages.config.tooltip.packAbuse"
        ))
        .append(Component.literal("\n"))
        .append(
            advancedTooltips
                ? Component.translatable(
                    "declinabledeathmessages.config.tooltip.enabledAdvancedTooltips"
                )
                : Component.translatable(
                    "declinabledeathmessages.config.tooltip.disabledAdvancedTooltips",
                    Component.keybind("key.debug.modifier"),
                    Component.keybind("key.debug.showAdvancedTooltips")
                )
        );
    }

    private Component getEntitiesTooltip() {
        boolean advancedTooltips =
            Minecraft.getInstance().options.advancedItemTooltips;

        return Component.translatable(
            "declinabledeathmessages.config.entities.tooltip"
        )
        .append(Component.literal("\n"))
        .append(Component.translatable(
            "declinabledeathmessages.config.tooltip.noKey"
        ))
        .append(Component.literal("\n"))
        .append(Component.translatable(
            "declinabledeathmessages.config.tooltip.packAbuse"
        ))
        .append(Component.literal("\n"))
        .append(
            advancedTooltips
                ? Component.translatable(
                    "declinabledeathmessages.config.tooltip.enabledAdvancedTooltips"
                )
                : Component.translatable(
                    "declinabledeathmessages.config.tooltip.disabledAdvancedTooltips",
                    Component.keybind("key.debug.modifier"),
                    Component.keybind("key.debug.showAdvancedTooltips")
                )
        );
    }

    private Component getMessageTooltip() {
        return Component.translatable(
            "declinabledeathmessages.config.message.tooltip"
        )
        .append(Component.literal("\n"))
        .append(Component.translatable(
            "declinabledeathmessages.config.message.tooltip.noKey"
        ))
        .append(Component.literal("\n"))
        .append(Component.translatable(
            "declinabledeathmessages.config.tooltip.packAbuse"
        ));
    }

    private Component getOriginalMessageHoverTooltip() {
        return Component.translatable(
                "declinabledeathmessages.config.originalMessageHover.tooltip"
        );
    }

    @Override
    protected void init() {

        addRenderableWidget(
            createBooleanButton(
                "declinabledeathmessages.config.names",
                NeoForgeConfig.NAMES_DECLENSION,
                this::getNamesTooltip,
                40
            )
        );

        addRenderableWidget(
            createBooleanButton(
                "declinabledeathmessages.config.entities",
                NeoForgeConfig.ENTITIES_DECLENSION,
                this::getEntitiesTooltip,
                65
            )
        );

        addRenderableWidget(
            createBooleanButton(
                "declinabledeathmessages.config.message",
                NeoForgeConfig.MESSAGE_INFLECTION,
                this::getMessageTooltip,
                90
            )
        );

        addRenderableWidget(
            createBooleanButton(
                "declinabledeathmessages.config.originalMessageHover",
                NeoForgeConfig.ORIGINAL_MESSAGE_HOVER,
                this::getOriginalMessageHoverTooltip,
                115
            )
        );
    }

    @Override
        public void tick() {
            if (buttons.size() > 0)
                buttons.get(0).setTooltip(Tooltip.create(getNamesTooltip()));

            if (buttons.size() > 1)
                buttons.get(1).setTooltip(Tooltip.create(getEntitiesTooltip()));

            if (buttons.size() > 2)
                buttons.get(2).setTooltip(Tooltip.create(getMessageTooltip()));

            if (buttons.size() > 3)
                buttons.get(3).setTooltip(Tooltip.create(getOriginalMessageHoverTooltip()));
        }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(parent);
    }
}