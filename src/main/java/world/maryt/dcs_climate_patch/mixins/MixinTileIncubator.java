package world.maryt.dcs_climate_patch.mixins;

import defeatedcrow.hac.api.climate.*;
import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.food.block.TileIncubator;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileIncubator.class, remap = false)
public abstract class MixinTileIncubator extends DCLockableTE implements ISidedInventory {
    @Shadow public IClimate current;

    @Unique
    public void heatAndClimatePatch$dataStore(IClimate climate) {
        NBTTagCompound data = this.getTileData();
        data.setInteger("currentClimateInt", climate.getClimateInt());
    }

    @Inject(
            method = "setClimate",
            at = @At(
                    value = "TAIL"
            )
    )
    private void inject_setClimate(IClimate climate, CallbackInfo ci) {
        heatAndClimatePatch$dataStore(climate);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void inject_init(CallbackInfo ci) {
        if (this.getTileData().hasKey("currentClimateInt")) {
            this.current = ClimateAPI.register.getClimateFromInt(this.getTileData().getInteger("currentClimateInt"));
        }
    }

    @Redirect(
            method = "updateTile",
            at = @At(
                    value = "INVOKE",
                    target = "Ldefeatedcrow/hac/api/climate/IBiomeClimateRegister;getClimateFromParam(Ldefeatedcrow/hac/api/climate/DCHeatTier;Ldefeatedcrow/hac/api/climate/DCHumidity;Ldefeatedcrow/hac/api/climate/DCAirflow;)Ldefeatedcrow/hac/api/climate/IClimate;"
            )
    )
    private IClimate redirect_updateTile(IBiomeClimateRegister instance, DCHeatTier dcHeatTier, DCHumidity dcHumidity, DCAirflow dcAirflow) {
        if (this.getTileData().hasKey("currentClimateInt")) {
            int climateInt = this.getTileData().getInteger("currentClimateInt");
            // When climateInt < 0, this method returns a (NORMAL, NORMAL, NORMAL).
            // This is the global default climate value, but not the default value for Incubator.
            return climateInt < 0 ? ClimateAPI.register.getClimateFromParam(dcHeatTier, dcHumidity, dcAirflow) : ClimateAPI.register.getClimateFromInt(climateInt);
        }
        // Return default value
        return ClimateAPI.register.getClimateFromParam(dcHeatTier, dcHumidity, dcAirflow);
    }
}
