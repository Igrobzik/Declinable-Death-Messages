package me.declinabledeathmessages;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.declinabledeathmessages.common.Common;
import me.declinabledeathmessages.config.NeoForgeConfig;

@Mod(Common.MOD_ID)
public class DeclinableDeathMessages {

    public static final Logger LOGGER =
            LoggerFactory.getLogger(Common.MOD_ID);

    public DeclinableDeathMessages(ModContainer container) {

        container.registerConfig(
                ModConfig.Type.CLIENT,
                NeoForgeConfig.SPEC
        );

        Common.init();

        LOGGER.info("Declinable Death Messages loaded!");
    }
}