package me.declinabledeathmessages;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.contents.TranslatableContents;

import me.declinabledeathmessages.config.ConfigManager;

public class DeathMessageResolver {

    public static Component resolve(Component message) {

        if (!(message.getContents()
                instanceof TranslatableContents contents)) {
            return message;
        }


        String key = contents.getKey();


        if (!key.startsWith("death.attack.")) {
            return message;
        }


        // Уже изменённое сообщение не трогаем
        if (key.contains(".by.")
                && key.endsWith(".message")) {
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
         * Полная замена сообщения:
         *
         * death.attack.mob.by.husk.message
         */
        if (ConfigManager.config.messageInflection) {

            Component customMessage =
                    findCustomMessage(
                            key,
                            killer,
                            args
                    );


            if (customMessage != null) {

                if (ConfigManager.config.originalMessageHover) {

                    Component original =
                            Component.translatable(
                                    key,
                                    args
                            );

                    return addOriginalMessageHover(
                            customMessage,
                            original
                    );
                }


                return customMessage;
            }
        }



        /*
         * Только склонение убийцы:
         *
         * Кадавр -> Кадавром
         */
        Component replacedKiller =
                DeathNameResolver.resolve(
                        killer,
                        key
                );


        if (replacedKiller != killer) {

            Object[] newArgs =
                    args.clone();


            /*
             * сохраняем оригинальный hover убийцы
             */
            newArgs[1] =
                    replacedKiller.copy()
                            .withStyle(
                                    killer.getStyle()
                            );


            Component result =
                    Component.translatable(
                            key,
                            newArgs
                    );


            if (ConfigManager.config.originalMessageHover) {

                Component original =
                        Component.translatable(
                                key,
                                args
                        );


                return addOriginalMessageHover(
                        result,
                        original
                );
            }


            return result;
        }


        return message;
    }



    private static Component findCustomMessage(
            String deathKey,
            Component killer,
            Object[] args
    ) {

        String prefix =
                deathKey + ".by.";


        /*
         * Именованный убийца:
         *
         * death.attack.mob.by.Боб.message
         */
        String name =
                killer.getString();


        if (!name.isEmpty()
                && ConfigManager.config.namesDeclension) {


            Component result =
                    find(
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
         * death.attack.mob.by.minecraft.husk.message
         * death.attack.mob.by.husk.message
         */
        if (ConfigManager.config.entitiesDeclension) {


            if (killer.getContents()
                    instanceof TranslatableContents entityContents) {


                String entityKey =
                        entityContents.getKey();


                String shortKey =
                        entityKey;


                if (entityKey.startsWith("entity.")) {

                    shortKey =
                            entityKey.substring(
                                    "entity.".length()
                            );
                }



                Component result =
                        find(
                                prefix +
                                shortKey +
                                ".message",
                                args
                        );


                if (result != null) {
                    return result;
                }



                if (shortKey.contains(".")) {


                    result =
                            find(
                                    prefix +
                                    shortKey.substring(
                                            shortKey.indexOf('.') + 1
                                    )
                                    + ".message",
                                    args
                            );


                    if (result != null) {
                        return result;
                    }
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


            Object[] newArgs =
                    args.clone();



            if (newArgs.length > 1
                    && newArgs[1] instanceof Component killer) {


                Component replaced =
                        DeathNameResolver.resolve(
                                killer,
                                key
                        );


                /*
                 * сохраняем hover убийцы
                 */
                newArgs[1] =
                        replaced.copy()
                                .withStyle(
                                        killer.getStyle()
                                );
            }


            return Component.translatable(
                    key,
                    newArgs
            );
        }


        return null;
    }



    private static Component addOriginalMessageHover(
            Component message,
            Component original
    ) {

        Component hoverText =
                Component.translatable(
                        "declinable-death-messages.hover.original",
                        original
                );


        return message.copy()
                .withStyle(style ->
                        style.withHoverEvent(
                                new HoverEvent.ShowText(hoverText)
                        )
                );
    }
}