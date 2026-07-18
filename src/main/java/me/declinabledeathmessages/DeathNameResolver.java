package me.declinabledeathmessages;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;

public class DeathNameResolver {

    public static Component resolve(Component killer, String deathKey) {

        String prefix = deathKey + ".by.";


        // Сначала пробуем имя
        String customName = killer.getString();

        if (!customName.isEmpty()) {

            Component result = find(prefix + customName, killer);

            if (result != null) {
                return result;
            }
        }


        // Потом обычный перевод сущности
        if (!(killer.getContents() instanceof TranslatableContents contents)) {
            return killer;
        }

        String entityKey = contents.getKey();

        // entity.minecraft.husk
        String shortKey = entityKey;

        if (entityKey.startsWith("entity.")) {
            shortKey = entityKey.substring("entity.".length());
        }


        Component result = find(prefix + shortKey, killer);

        if (result != null) {
            return result;
        }


        // minecraft.husk
        if (shortKey.contains(".")) {

            result = find(
                prefix + shortKey.substring(
                    shortKey.indexOf('.') + 1
                ),
                killer
            );

            if (result != null) {
                return result;
            }
        }


        return killer;
    }


    private static Component find(String key, Component original) {

        Language language = Language.getInstance();

        if (!language.has(key)) {
            return null;
        }

        return Component.translatable(key)
                .setStyle(original.getStyle());
    }
}