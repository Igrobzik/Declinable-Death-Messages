package me.declinabledeathmessages;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;

import me.declinabledeathmessages.config.ConfigManager;

public class DeathNameResolver {

    public static Component resolve(Component killer, String deathKey) {

        System.out.println(
            "[DDM] DeathNameResolver: key=" + deathKey +
            ", killer=" + killer.getString()
        );

        // Нормализуем уже заменённые сообщения
        // death.attack.mob.by.Боб.message -> death.attack.mob
        if (deathKey.contains(".by.") && deathKey.endsWith(".message")) {
            deathKey = deathKey.substring(0, deathKey.indexOf(".by."));
        }

        String prefix = deathKey + ".by.";

        // Сначала пробуем имя
        String customName = killer.getString();

        if (!customName.isEmpty()) {

            if (ConfigManager.config.namesDeclension) {

                Component result = find(prefix + customName, killer);

                if (result != null) {
                    return result;
                }
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

    // Новый метод для поиска кастомных переводов
    public static Component resolveCustom(String customKey, Component original) {
        Component result = find(customKey, original);
        return result != null ? result : original;
    }

    // Вспомогательный метод для поиска перевода
    private static Component find(String key, Component original) {
        // Проверяем, есть ли перевод в текущем языке
        if (Language.getInstance().has(key)) {
            return Component.translatable(
                key,
                original
            );
        }
        return null;
    }
}
