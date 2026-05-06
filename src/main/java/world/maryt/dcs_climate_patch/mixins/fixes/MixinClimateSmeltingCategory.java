package world.maryt.dcs_climate_patch.mixins.fixes;

import defeatedcrow.hac.core.plugin.jei.ClimateSmeltingCategory;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

// Fix: Hardcoded texts in Heat Treatment recipes hints
@SuppressWarnings("rawtypes")
@Mixin(value = ClimateSmeltingCategory.class, remap = false)
public abstract class MixinClimateSmeltingCategory implements IRecipeCategory {
    @ModifyArg(
            method = "drawExtras",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;FFIZ)I",
                    ordinal = 0
            )
    )
    private String modifyArg_drawInfo_0(String s) {
        return I18n.format("dcs.tip.heat_name2");
    }
    @ModifyArg(
            method = "drawExtras",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;FFIZ)I",
                    ordinal = 1
            )
    )
    private String modifyArg_drawInfo_1(String s) {
        return I18n.format("dcs.tip.humidity_name2");
    }
    @ModifyArgs(
            method = "drawExtras",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;FFIZ)I",
                    ordinal = 2
            )
    )
    private void modifyArgs_drawInfo_2(Args args) {
        args.set(0, I18n.format("dcs.tip.airflow_name2"));
        args.set(1, 18.0F);
    }
}
