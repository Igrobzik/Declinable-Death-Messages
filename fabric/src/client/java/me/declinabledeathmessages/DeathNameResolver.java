package me.declinabledeathmessages;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;

import me.declinabledeathmessages.config.ConfigManager;

public class DeathNameResolver {

    public static Component resolve(Component killer, String deathKey) {

        // death.attack.mob.by.Bob.message -> death.attack.mob
        if (deathKey.contains(".by.") && deathKey.endsWith(".message")) {
            deathKey = deathKey.substring(0, deathKey.indexOf(".by."));
        }

        String prefix = deathKey + ".by.";


        /*
         * Имена:
         *
         * death.attack.mob.by.Боб
         */
        String customName = killer.getString();


        if (!customName.isEmpty()
                && ConfigManager.config.namesDeclension) {

            Component result = find(
                    prefix + customName,
                    killer
            );

            if (result != null) {
                return result;
            }
        }



        /*
         * Сущности:
         *
         * death.attack.mob.by.minecraft.husk
         * death.attack.mob.by.husk
         */
        if (ConfigManager.config.entitiesDeclension) {

            if (killer.getContents() instanceof TranslatableContents contents) {

                String entityKey = contents.getKey();


                String shortKey = entityKey;


                if (entityKey.startsWith("entity.")) {
                    shortKey =
                            entityKey.substring("entity.".length());
                }


                Component result = find(
                        prefix + shortKey,
                        killer
                );


                if (result != null) {
                    return result;
                }



                if (shortKey.contains(".")) {

                    result = find(
                            prefix +
                            shortKey.substring(
                                    shortKey.indexOf('.') + 1
                            ),
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

            /*
             * Сохраняем hover/click/color оригинала
             */
            return Component.translatable(key)
                    .setStyle(original.getStyle());
        }

        return null;
    }
}