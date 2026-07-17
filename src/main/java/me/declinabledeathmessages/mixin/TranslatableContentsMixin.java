package me.declinabledeathmessages.mixin;

import me.declinabledeathmessages.DeathNameResolver;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.contents.TranslatableContents;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(TranslatableContents.class)
public class TranslatableContentsMixin {


    @Inject(
        method = "getArgument",
        at = @At("HEAD"),
        cancellable = true
    )
    private void replaceDeathKiller(
            int index,
            CallbackInfoReturnable<FormattedText> cir
    ) {

        TranslatableContents self =
                (TranslatableContents)(Object)this;


        String key = self.getKey();


        if (!key.startsWith("death.")) {
            return;
        }


        // второй аргумент = убийца
        if (index != 1) {
            return;
        }


        Object[] args = self.getArgs();


        if (args.length <= 1) {
            return;
        }


        if (!(args[1] instanceof Component killer)) {
            return;
        }


        Component replaced =
                DeathNameResolver.resolve(
                        killer,
                        key
                );


        if (replaced != killer) {
            cir.setReturnValue(replaced);
        }
    }
}