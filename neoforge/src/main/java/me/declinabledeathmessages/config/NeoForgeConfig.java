package me.declinabledeathmessages.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeConfig {

    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue NAMES_DECLENSION;
    public static final ModConfigSpec.BooleanValue ENTITIES_DECLENSION;
    public static final ModConfigSpec.BooleanValue MESSAGE_INFLECTION;
    public static final ModConfigSpec.BooleanValue ORIGINAL_MESSAGE_HOVER;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        NAMES_DECLENSION = builder
                .translation("declinabledeathmessages.config.names")
                .define("namesDeclension", true);

        ENTITIES_DECLENSION = builder
                .translation("declinabledeathmessages.config.entities")
                .define("entitiesDeclension", true);

        MESSAGE_INFLECTION = builder
                .translation("declinabledeathmessages.config.message")
                .define("messageInflection", true);

        ORIGINAL_MESSAGE_HOVER = builder
                .translation("declinabledeathmessages.config.originalMessageHover")
                .define("originalMessageHover", false);

        SPEC = builder.build();
    }

    public static boolean getBoolean(ModConfigSpec.BooleanValue value) {
        try {
            return value.get();
        } catch (IllegalStateException e) {
            return value.getDefault();
        }
    }
}