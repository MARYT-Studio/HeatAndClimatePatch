package world.maryt.dcs_climate_patch.mixins;

import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.util.DCTimeHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DCTimeHelper.class, remap = false)
public abstract class MixinDCTimeHelper {
    @Shadow
    public static int getDay(World world) {
        return 0;
    }

    @Shadow
    public static int getYear(World world) {
        return 0;
    }

    @Inject(
            method = "getDate",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true)
    private static void inject_getDate(World world, CallbackInfoReturnable<String> cir) {
        if (!CoreConfigDC.enableRealTime) {
            int day = getDay(world);
            if (day > CoreConfigDC.yearLength) {
                day %= CoreConfigDC.yearLength;
            }

            int year = getYear(world);
            cir.setReturnValue(I18n.format("dcs.tip.date", year, day));
        }
    }
}
