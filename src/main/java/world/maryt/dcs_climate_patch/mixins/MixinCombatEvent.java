package world.maryt.dcs_climate_patch.mixins;

import defeatedcrow.hac.main.event.CombatEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import world.maryt.dcs_climate_patch.HACPatchConfig;

@Mixin(value = CombatEvent.class, remap = false)
public abstract class MixinCombatEvent {

    @Unique
    boolean heatAndClimatePatch$isHurtEntityPlayer;

    @Inject(
            method = "onHurt",
            at = @At(
                    value = "HEAD"
            )
    )
    public void markEntityIsPlayer(LivingHurtEvent event, CallbackInfo ci) {
        heatAndClimatePatch$isHurtEntityPlayer = HACPatchConfig.no_robber_to_players && (event.getEntityLiving() instanceof EntityPlayer);
    }

    @Redirect(
            method = "onHurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEnchantmentLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I",
                    ordinal = 1
            )
    )
    public int getRobberLevel(Enchantment ench, ItemStack enchantment) {
        return heatAndClimatePatch$isHurtEntityPlayer ? 0 : EnchantmentHelper.getEnchantmentLevel(ench, enchantment);
    }
}
