package world.maryt.dcs_climate_patch.mixins;

import defeatedcrow.hac.plugin.jei.DCHeatTreatmentWrapper;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = DCHeatTreatmentWrapper.class, remap = false)
public abstract class MixinDCHeatTreatmentWrapper {

    @Shadow
    public int time1;
    @Shadow
    public int time2;

    @ModifyArg(
            method = "drawInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;FFIZ)I",
                    ordinal = 0
            )
    )
    private String modifyArg_drawInfo_1(String text) {
        return I18n.format("dcs.tip.heat_treatment_jei1", time1);
    }

    @ModifyArg(
            method = "drawInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;FFIZ)I",
                    ordinal = 1
            )
    )
    private String modifyArg_drawInfo_2(String text) {
        return I18n.format("dcs.tip.heat_treatment_jei1", time2);
    }
}
