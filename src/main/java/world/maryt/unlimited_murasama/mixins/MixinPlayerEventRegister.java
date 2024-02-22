package world.maryt.unlimited_murasama.mixins;

import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.event.UseSlashBladeEvent;
import cn.mmf.lastsmith.util.IMultiModeBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import cn.mmf.lastsmith.event.PlayerEventRegister;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = PlayerEventRegister.class, remap = false)
public abstract class MixinPlayerEventRegister {

    /**
     * @author RisingInIris2017
     * @reason Make MURASAMA bypass the cool down mechanic.
     */
    @SubscribeEvent
    @Overwrite
    public static void onDoSlashBladeAttack(UseSlashBladeEvent.doAttackEvent event) {
        ItemStack blade = event.getBlade();
        NBTTagCompound nbt = ItemSlashBlade.getItemTagCompound(blade);
        if (ItemSlashBladeNamed.CurrentItemName.get(nbt).equalsIgnoreCase("flammpfeil.slashblade.named.thousand") && ((IMultiModeBlade)blade.getItem()).getMode(blade) != 1) {
            event.setCanceled(true);
        }

        if (!TLSConfig.slashblade_broken_blade_attack_enable && ItemSlashBlade.IsBroken.get(nbt)) {
            event.setCanceled(true);
        }

        if (TLSConfig.advanced_mode) {
            if (TLSConfig.slashblade_action_cooldown_enable && TLSConfig.slashblade_action_cooldown > 0.0) {
                int timer = event.getComboSeq().comboResetTicks;
                if (timer < 10) {
                    timer -= 2;
                } else if (timer > 20) {
                    timer += 2;
                }
                if (ItemSlashBladeNamed.CurrentItemName.get(nbt).equalsIgnoreCase("flammpfeil.slashblade.named.murasamablade") && ((IMultiModeBlade)blade.getItem()).getMode(blade) == 1) {
                    event.getPlayer().getCooldownTracker().setCooldown(blade.getItem(), 0);
                } else {
                    event.getPlayer().getCooldownTracker().setCooldown(blade.getItem(), (int)((double)timer * TLSConfig.slashblade_action_cooldown));
                }
            }

        }
    }
}
