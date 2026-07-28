package me.declinabledeathmessages;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;

import me.declinabledeathmessages.config.NeoForgeConfig;

public class DeathNameResolver {

    public static Component resolve(Component killer, String deathKey) {

        if (deathKey.contains(".by.") && deathKey.endsWith(".message")) {
            deathKey = deathKey.substring(0, deathKey.indexOf(".by."));
        }

        String prefix = deathKey + ".by.";

        String customName = killer.getString();


        if (!customName.isEmpty()
                && NeoForgeConfig.getBoolean(NeoForgeConfig.NAMES_DECLENSION)) {

            String key = prefix + customName;

            DeclinableDeathMessages.LOGGER.info(
                    "Trying death name key: {}",
                    key
            );

            Component result = find(
                    key,
                    killer
            );

            if (result != null) {
                return result;
            }
        }


        if (NeoForgeConfig.getBoolean(NeoForgeConfig.ENTITIES_DECLENSION)) {

            if (killer.getContents() instanceof TranslatableContents contents) {

                String entityKey = contents.getKey();

                String shortKey = entityKey;


                if (entityKey.startsWith("entity.")) {
                    shortKey =
                            entityKey.substring("entity.".length());
                }


                String key = prefix + shortKey;

                DeclinableDeathMessages.LOGGER.info(
                        "Trying entity death name key: {}",
                        key
                );


                Component result = find(
                        key,
                        killer
                );


                if (result != null) {
                    return result;
                }


                if (shortKey.contains(".")) {

                    key =
                            prefix +
                            shortKey.substring(
                                    shortKey.indexOf('.') + 1
                            );


                    DeclinableDeathMessages.LOGGER.info(
                            "Trying short entity death name key: {}",
                            key
                    );


                    result = find(
                            key,
                            killer
                    );


                    if (result != null) {
                        return result;
                    }
                }
            }
        }


        return killer;
    }



    public static Component resolveCustom(
            String key,
            Component original
    ) {

        Component result = find(
                key,
                original
        );

        return result != null
                ? result
                : original;
    }



    private static Component find(
            String key,
            Component original
    ) {

        if (Language.getInstance().has(key)) {

            DeclinableDeathMessages.LOGGER.info(
                    "Found custom death name key: {}",
                    key
            );

            return Component.translatable(key)
                    .setStyle(original.getStyle());
        }


        DeclinableDeathMessages.LOGGER.info(
                "Missing custom death name key: {}",
                key
        );

        return null;
    }
}