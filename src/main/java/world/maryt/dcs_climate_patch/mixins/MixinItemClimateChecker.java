package world.maryt.dcs_climate_patch.mixins;

import defeatedcrow.hac.core.item.ItemClimateChecker;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = ItemClimateChecker.class, remap = false)
public abstract class MixinItemClimateChecker {
    @ModifyArg(
            method = "onItemUse2",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;func_145747_a(Lnet/minecraft/util/text/ITextComponent;)V",
                    ordinal = 0
            )
    )
    public ITextComponent modifyArg_onItemUse2_0(ITextComponent par1) {
        return new TextComponentTranslation("dcs.climate_checker.title");
    }
    @ModifyArg(
            method = "onItemUse2",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;func_145747_a(Lnet/minecraft/util/text/ITextComponent;)V",
                    ordinal = 1
            )
    )
    public ITextComponent modifyArg_onItemUse2_1(ITextComponent par1) {
        String name = (par1.getUnformattedText().split(" "))[1];
        String title = new TextComponentTranslation("dcs.climate_checker.temperature").getFormattedText();
        String info = new TextComponentTranslation("dcs.tip.heat." + name).getUnformattedText();
        return new TextComponentString(title + info);
    }
    @ModifyArg(
            method = "onItemUse2",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;func_145747_a(Lnet/minecraft/util/text/ITextComponent;)V",
                    ordinal = 2
            )
    )
    public ITextComponent modifyArg_onItemUse2_2(ITextComponent par1) {
        String name = (par1.getUnformattedText().split(" "))[1];
        String title = new TextComponentTranslation("dcs.climate_checker.humidity").getFormattedText();
        String info = new TextComponentTranslation("dcs.tip.hum." + name).getUnformattedText();
        return new TextComponentString(title + info);
    }
    @ModifyArg(
            method = "onItemUse2",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;func_145747_a(Lnet/minecraft/util/text/ITextComponent;)V",
                    ordinal = 3
            )
    )
    public ITextComponent modifyArg_onItemUse2_3(ITextComponent par1) {
        String name = (par1.getUnformattedText().split(" "))[1];
        String title = new TextComponentTranslation("dcs.climate_checker.airflow").getFormattedText();
        String info = new TextComponentTranslation("dcs.tip.air." + name).getUnformattedText();
        return new TextComponentString(title + info);
    }
}
