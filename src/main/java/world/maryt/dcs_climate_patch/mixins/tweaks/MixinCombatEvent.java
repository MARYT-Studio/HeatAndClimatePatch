package world.maryt.dcs_climate_patch.mixins.tweaks;

import com.llamalad7.mixinextras.sugar.Local;
import defeatedcrow.hac.main.event.CombatEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import world.maryt.dcs_climate_patch.HACPatchConfig;

// Tweak: Robber Enchantment can be disabled when it's targeted to players
// Credit @ZekerZhayard
@Mixin(value = CombatEvent.class, remap = false)
public abstract class MixinCombatEvent {

    @ModifyVariable(
            method = "onHurt",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEnchantmentLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I"
            ),
            name = "robber"
    )
    private int modifyRobberLevel(int robber, @Local(ordinal = 0) EntityLivingBase living) {
        return HACPatchConfig.no_robber_to_players && living instanceof EntityPlayer ? 0 : robber;
    }
}
