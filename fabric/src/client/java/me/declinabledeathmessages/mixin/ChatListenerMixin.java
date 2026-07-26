package me.declinabledeathmessages.mixin;

import me.declinabledeathmessages.DeathMessageResolver;

import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.Component;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(ChatListener.class)
public class ChatListenerMixin {

    @ModifyVariable(
            method = "handleSystemMessage",
            at = @At("HEAD"),
            argsOnly = true
    )
    private Component declinableDeathMessages$replace(Component message) {
        return DeathMessageResolver.resolve(message);
    }
}