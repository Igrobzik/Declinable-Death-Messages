package me.declinabledeathmessages;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.contents.TranslatableContents;

import me.declinabledeathmessages.config.NeoForgeConfig;

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

        if (NeoForgeConfig.MESSAGE_INFLECTION.get()) {

            Component customMessage =
                    findCustomMessage(
                            key,
                            killer,
                            args
                    );

            if (customMessage != null) {

                DeclinableDeathMessages.LOGGER.info(
                        "Death message replaced: [{}] {} -> {}",
                        key,
                        message.getString(),
                        customMessage.getString()
                );

                if (NeoForgeConfig.ORIGINAL_MESSAGE_HOVER.get()) {

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

        Component replacedKiller =
                DeathNameResolver.resolve(
                        killer,
                        key
                );

        if (replacedKiller != killer) {

            Object[] newArgs =
                    args.clone();

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

            DeclinableDeathMessages.LOGGER.info(
                    "Death name replaced: [{}] {} -> {}",
                    key,
                    killer.getString(),
                    replacedKiller.getString()
            );

            if (NeoForgeConfig.ORIGINAL_MESSAGE_HOVER.get()) {

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

        String name =
                killer.getString();


        if (!name.isEmpty()
                && NeoForgeConfig.NAMES_DECLENSION.get()) {

            String searchKey =
                    prefix + name + ".message";

            Component result =
                    find(
                            searchKey,
                            args
                    );

            if (result != null) {
                return result;
            }
        }


        if (NeoForgeConfig.ENTITIES_DECLENSION.get()) {

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


                String searchKey =
                        prefix + shortKey + ".message";


                Component result =
                        find(
                                searchKey,
                                args
                        );


                if (result != null) {
                    return result;
                }


                if (shortKey.contains(".")) {

                    searchKey =
                            prefix +
                            shortKey.substring(
                                    shortKey.indexOf('.') + 1
                            )
                            + ".message";


                    result =
                            find(
                                    searchKey,
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

            DeclinableDeathMessages.LOGGER.info(
                    "Found custom death message key: {}",
                    key
            );

            Object[] newArgs =
                    args.clone();


            if (newArgs.length > 1
                    && newArgs[1] instanceof Component killer) {

                Component replaced =
                        DeathNameResolver.resolve(
                                killer,
                                key
                        );

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
                        "declinabledeathmessages.hover.original",
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