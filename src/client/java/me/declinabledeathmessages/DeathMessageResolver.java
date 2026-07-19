package me.declinabledeathmessages;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;

import me.declinabledeathmessages.config.ConfigManager;

public class DeathMessageResolver {

    public static Component resolve(Component message) {

        if (!(message.getContents() instanceof TranslatableContents contents)) {
            return message;
        }


        String key = contents.getKey();


        if (!key.startsWith("death.attack.")) {
            return message;
        }


        // Уже заменённое сообщение не трогаем
        if (key.contains(".by.") && key.endsWith(".message")) {
            return message;
        }


        Object[] args = contents.getArgs();


        if (args.length <= 1) {
            return message;
        }


        if (!(args[1] instanceof Component killer)) {
            return message;
        }


        /*
         * 1. Полная замена сообщения:
         *
         * death.attack.mob.by.Боб.message
         * death.attack.player.by.Steve.message
         * death.attack.mob.by.minecraft.husk.message
         */
        Component customMessage = findCustomMessage(
                key,
                killer,
                args
        );


        if (customMessage != null) {
            return customMessage;
        }


        /*
         * 2. Если полного сообщения нет —
         * меняем только убийцу:
         *
         * Кадавр -> Кадавром
         */
        Component replacedKiller =
                DeathNameResolver.resolve(
                        killer,
                        key
                );


        if (replacedKiller != killer) {

            Object[] newArgs = args.clone();

            newArgs[1] = replacedKiller;


            return Component.translatable(
                    key,
                    newArgs
            );
        }


        return message;
    }



    private static Component findCustomMessage(
            String deathKey,
            Component killer,
            Object[] args
    ) {

        String prefix = deathKey + ".by.";


        /*
         * Именованный убийца:
         *
         * death.attack.mob.by.Боб.message
         */
        String name = killer.getString();


        if (!name.isEmpty() && ConfigManager.config.namesDeclension) {

            Component result = find(
                    prefix + name + ".message",
                    args
            );


            if (result != null) {
                return result;
            }
        }



        /*
         * Сущность:
         *
         * entity.minecraft.husk
         *
         * -> minecraft.husk
         */
        if (killer.getContents() instanceof TranslatableContents entityContents) {


            String entityKey = entityContents.getKey();


            String shortKey = entityKey;


            if (entityKey.startsWith("entity.")) {
                shortKey = entityKey.substring("entity.".length());
            }



            /*
             * death.attack.mob.by.minecraft.husk.message
             */
            Component result = find(
                    prefix + shortKey + ".message",
                    args
            );


            if (result != null) {
                return result;
            }



            /*
             * death.attack.mob.by.husk.message
             */
            if (shortKey.contains(".")) {

                result = find(
                        prefix +
                        shortKey.substring(
                                shortKey.indexOf('.') + 1
                        ) +
                        ".message",
                        args
                );


                if (result != null) {
                    return result;
                }
            }
        }


        return null;
    }



    private static Component find(
            String key,
            Object[] args
    ) {

        if (Language.getInstance().has(key)) {

            Object[] newArgs = args.clone();


            if (newArgs.length > 1 && newArgs[1] instanceof Component killer) {

                Component replaced =
                        DeathNameResolver.resolve(
                                killer,
                                key
                        );

                newArgs[1] = replaced;
            }


            return Component.translatable(
                    key,
                    newArgs
            );
        }


        return null;
    }
}