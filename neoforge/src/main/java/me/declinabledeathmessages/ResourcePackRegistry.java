package me.declinabledeathmessages;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;

import me.declinabledeathmessages.common.Common;

@EventBusSubscriber(modid = Common.MOD_ID)
public class ResourcePackRegistry {

    @SubscribeEvent
    public static void registerPack(AddPackFindersEvent event) {

        if (event.getPackType() != PackType.CLIENT_RESOURCES) {
            return;
        }

        event.addPackFinders(
                Identifier.fromNamespaceAndPath(
                        Common.MOD_ID,
                        "resourcepacks/russian"
                ),
                PackType.CLIENT_RESOURCES,
                Component.translatable("declinabledeathmessages.resourcepack.russian"),
                PackSource.BUILT_IN,
                false,
                Pack.Position.TOP
        );
    }
}