package world.maryt.dcs_climate_patch.mixins;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.item.equip.ItemArmorCombat;
import defeatedcrow.hac.main.item.equip.ItemArmorDC;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import world.maryt.dcs_climate_patch.HACPatchConfig;

@Mixin(value = ItemArmorCombat.class, remap = false)
public abstract class MixinItemArmorCombat extends ItemArmorDC implements ITexturePath{


    public MixinItemArmorCombat(ItemArmor.ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
        super(m, mat, slot, t);
    }

    @Inject(
            method = "onCreated",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    public void onCreated(ItemStack stack, World world, EntityPlayer player, CallbackInfo ci) {
        if (HACPatchConfig.remove_titanium_armor_enchantment) {
            super.onCreated(stack, world, player);
            ci.cancel();
        }
    }

}
