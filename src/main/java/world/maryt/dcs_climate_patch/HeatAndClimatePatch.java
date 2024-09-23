package world.maryt.dcs_climate_patch;

import net.minecraftforge.fml.common.Mod;

import static world.maryt.dcs_climate_patch.HeatAndClimatePatch.MOD_ID;

@Mod(modid = MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION, dependencies = HeatAndClimatePatch.DEPENDENCIES)
public class HeatAndClimatePatch {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String DEPENDENCIES = "required-after:dcs_climate;required-after:mixinbooter";
}
