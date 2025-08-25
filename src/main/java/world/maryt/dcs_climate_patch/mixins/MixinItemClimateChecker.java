package world.maryt.dcs_climate_patch.mixins;

import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.IClimateObject;
import defeatedcrow.hac.core.item.ItemClimateChecker;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = ItemClimateChecker.class, remap = false)
public abstract class MixinItemClimateChecker {
    @Unique
    private IClimate heatAndClimatePatch$cliamte;

    @Inject(
            method = "onItemUse2",
            at = @At(
                    value = "INVOKE",
                    target = "Ldefeatedcrow/hac/api/climate/IClimateCalculator;getClimate(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;[I)Ldefeatedcrow/hac/api/climate/IClimate;",
                    ordinal = 0
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    public void modifyArg_onItemUse2_0(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<EnumActionResult> cir, IBlockState state, IClimate c, IClimateObject co) {
        c == null ?
    }

    @ModifyArg(
            method = "onItemUse2",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;func_145747_a(Lnet/minecraft/util/text/ITextComponent;)V",
                    ordinal = 0
                )
            )
    public ITextComponent modifyArg_onItemUse2_0(ITextComponent component) {
        return new TextComponentTranslation("dcs.climate_checker.title");
    }

}
