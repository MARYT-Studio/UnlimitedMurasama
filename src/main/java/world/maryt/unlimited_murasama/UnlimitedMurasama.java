package world.maryt.unlimited_murasama;

import net.minecraftforge.fml.common.Mod;

@Mod(modid = UnlimitedMurasama.MOD_ID, name = UnlimitedMurasama.NAME, version = UnlimitedMurasama.VERSION, dependencies = UnlimitedMurasama.DEPENDENCIES)
public class UnlimitedMurasama {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String NAME = "UnlimitedMurasama";
    public static final String VERSION = Tags.VERSION;
    public static final String DEPENDENCIES = "required-after:mixinbooter;required-after:lastsmith";
}
