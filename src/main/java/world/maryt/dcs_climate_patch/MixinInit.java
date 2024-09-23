package world.maryt.dcs_climate_patch;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Collections;
import java.util.List;

import static world.maryt.dcs_climate_patch.HeatAndClimatePatch.MOD_ID;

public class MixinInit implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList(String.format("mixins.%s.json", MOD_ID));
    }
}
