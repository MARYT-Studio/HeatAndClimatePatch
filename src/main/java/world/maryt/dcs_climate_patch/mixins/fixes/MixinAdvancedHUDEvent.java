package world.maryt.dcs_climate_patch.mixins.fixes;

import com.llamalad7.mixinextras.sugar.Local;
import defeatedcrow.hac.core.client.AdvancedHUDEvent;
import defeatedcrow.hac.core.util.DCTimeHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// Fix: Hardcoded texts at the Date info at the left corner of game screen
@Mixin(value = AdvancedHUDEvent.class, remap = false)
public abstract class MixinAdvancedHUDEvent {
    @ModifyVariable(
            method = "doRender",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Ldefeatedcrow/hac/core/util/DCTimeHelper;getDate(Lnet/minecraft/world/World;)Ljava/lang/String;"
            ),
            name = "s2"
    )
    private String modifyDateInfo(String s2, @Local(ordinal = 0) World world) {
        String[] yearAndDay = DCTimeHelper.getDate(world).split(";");
        return I18n.format("dcs.tip.date", yearAndDay[0], yearAndDay[1]);
    }
}
