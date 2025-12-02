package world.maryt.dcs_climate_patch.mixins.fixes;

import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.util.DCTimeHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Fix: Hardcoded texts at the Date info at the left corner of game screen
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
            cir.setReturnValue(String.format("%d;%d", year, day));
        }
    }
}
