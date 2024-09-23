package world.maryt.dcs_climate_patch.mixins;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import defeatedcrow.hac.magic.event.MagicCommonEvent;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = MagicCommonEvent.class, remap = false)
public abstract class MixinMagicCommonEvent {
    /**
     * @author RisingInIris2017
     * @reason Remove dimension and position record event.
     * Not know how HeatAndClimate Mod use this record NBT to teleport players,
     * but removing these NBTs will disable the teleporting.
     */
    @SubscribeEvent
    @Overwrite
    public void afterWarpDimEvent(PlayerEvent.PlayerChangedDimensionEvent event) {}
}
