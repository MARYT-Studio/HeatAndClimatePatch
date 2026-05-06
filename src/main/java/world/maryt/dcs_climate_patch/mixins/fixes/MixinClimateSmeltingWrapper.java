package world.maryt.dcs_climate_patch.mixins.fixes;

import defeatedcrow.hac.core.plugin.jei.ClimateSmeltingWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// Fix: Hardcoded texts in Heat Treatment recipes hints
@Mixin(value = ClimateSmeltingWrapper.class, remap = false)
public abstract class MixinClimateSmeltingWrapper implements IRecipeWrapper {
    @ModifyArg(
            method = "drawInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;FFIZ)I",
                    ordinal = 0
            )
    )
    private String modifyArg_drawInfo_0(String s) {
        return I18n.format("dcs.recipe_type." + s.toLowerCase());
    }

    @ModifyArg(
            method = "drawInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;FFIZ)I",
                    ordinal = 1
            )
    )
    private String modifyArg_drawInfo_1(String place) {
        switch (place) {
            case "Require the processing device.": place = "dcs.climate_smelting.place.require_the_processing_device"; break;
            case "Proceeds as a placed object.": place = "dcs.climate_smelting.place.proceeds_as_a_placed_object"; break;
            case "Proceeds as a drop item entity.": place = "dcs.climate_smelting.place.proceeds_as_a_drop_item_entity"; break;
            default: place = "";
        }
        return I18n.format(place);
    }

    @ModifyArg(
            method = "drawInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;FFIZ)I",
                    ordinal = 2
            )
    )
    private String modifyArg_drawInfo_2(String flq) {
        switch (flq) {
            case "Less Frequency Process": flq = "dcs.climate_smelting.freq.less_frequency_process"; break;
            case "Rapid Process": flq = "dcs.climate_smelting.freq.rapid_process"; break;
            case "Middle Frequency Process": flq = "dcs.climate_smelting.freq.middle_frequency_process"; break;
            default: flq = "";
        }
        return I18n.format(flq);
    }
}
