package world.maryt.dcs_climate_patch.mixins;

import defeatedcrow.hac.core.client.ClientProxyD;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ClientProxyD.class, remap = false)
public abstract class MixinClientProxyD {

    @Inject(
            method = "isWarpKeyDown",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true)
    public void isWarpKeyDown(CallbackInfoReturnable<Boolean> cir) {
        if (Minecraft.getMinecraft().currentScreen != null) cir.setReturnValue(false);
    }
}
