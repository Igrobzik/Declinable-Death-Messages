package me.declinabledeathmessages;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import me.declinabledeathmessages.client.ConfigScreen;
import me.declinabledeathmessages.common.Common;

@Mod(value = Common.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Common.MOD_ID, value = Dist.CLIENT)
public class DeclinableDeathMessagesClient {

    public DeclinableDeathMessagesClient(ModContainer container) {
        DeclinableDeathMessages.LOGGER.info("CLIENT MOD CONSTRUCTOR");

        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                (mod, parent) -> new ConfigScreen(parent)
        );
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        DeclinableDeathMessages.LOGGER.info("HELLO FROM CLIENT SETUP");
        DeclinableDeathMessages.LOGGER.info(
                "MINECRAFT NAME >> {}",
                Minecraft.getInstance().getUser().getName()
        );
    }
}