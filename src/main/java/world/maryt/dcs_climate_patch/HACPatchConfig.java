package world.maryt.dcs_climate_patch;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = HeatAndClimatePatch.MOD_ID)
@Mod.EventBusSubscriber(modid = HeatAndClimatePatch.MOD_ID)
public class HACPatchConfig {

	private final static String config = HeatAndClimatePatch.MOD_ID + ".config.";

	@Config.LangKey(config + "no_robber_to_players")
	@Config.Comment("If true, Robber enchantment will not to affect players.")
	public static boolean no_robber_to_players = false;

	@Config.LangKey(config + "remove_titanium_armor_enchantment")
	@Config.Comment("If true, Titanium armor will not be bind to Projectile Protection enchantment.")
	public static boolean remove_titanium_armor_enchantment = false;

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(HeatAndClimatePatch.MOD_ID)) {
			ConfigManager.sync(HeatAndClimatePatch.MOD_ID, Config.Type.INSTANCE);
		}
	}
}
