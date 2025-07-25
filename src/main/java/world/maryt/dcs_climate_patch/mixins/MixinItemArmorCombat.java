package world.maryt.dcs_climate_patch.mixins;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.item.equip.ItemArmorCombat;
import defeatedcrow.hac.main.item.equip.ItemArmorDC;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import world.maryt.dcs_climate_patch.HACPatchConfig;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@Mixin(value = ItemArmorCombat.class, remap = false)
public abstract class MixinItemArmorCombat extends ItemArmorDC implements ITexturePath{

    @Shadow
    @Final
    public static Map<Enchantment, Integer> PROJ4;

    public MixinItemArmorCombat(ItemArmor.ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
        super(m, mat, slot, t);
    }

    /**
     * @author RisingInIris2017
     * @reason Remove the default Projectile Protection enchantment for Titanium Armor.
     */
    @Overwrite
    @ParametersAreNonnullByDefault
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (!HACPatchConfig.remove_titanium_armor_enchantment && !DCUtil.isEmpty(stack) && stack.getItem() == MainInit.titaniumArmor[1]) {
            EnchantmentHelper.setEnchantments(PROJ4, stack);
        }
        super.onCreated(stack, world, player);
    }
}
