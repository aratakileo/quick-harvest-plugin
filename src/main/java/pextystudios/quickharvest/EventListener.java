package pextystudios.quickharvest;

import io.papermc.paper.event.block.BlockPreDispenseEvent;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.Directional;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pextystudios.quickharvest.util.DropUtil;
import pextystudios.quickharvest.util.SoundUtil;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        @NotNull FileConfiguration config = QuickHarvest.getInstance().getConfig();

        if (!config.getBoolean("feature.player") || e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = e.getClickedBlock();
        String blockKey = block.getType().getKey().asString();
        ItemStack itemStack = e.getItem();
        String itemKey = e.getMaterial().getKey().asString();

        if (!config.getConfigurationSection("reason").getKeys(false).contains(itemKey) || !blockKey.equals(config.getString("reason." + itemKey + ".target"))) return;

        Ageable ageable = (Ageable) block.getBlockData();

        if (ageable.getAge() != ageable.getMaximumAge()) return;

        Inventory pInv = e.getPlayer().getInventory();

        for (ItemStack dropItemStack : block.getDrops()) {
            if (itemStack != null && itemStack.getType() == dropItemStack.getType())
                dropItemStack.setAmount(dropItemStack.getAmount() - 1);

            if (pInv.firstEmpty() == -1) {
                int allInvAmount = 0;

                for (ItemStack invItemStack : pInv.getContents()) {
                    if (invItemStack != null && invItemStack.getType() == dropItemStack.getType())
                        allInvAmount += invItemStack.getAmount();
                }

                int freeAmount = dropItemStack.getMaxStackSize();
                freeAmount = freeAmount - (allInvAmount % freeAmount);
                freeAmount = freeAmount == dropItemStack.getMaxStackSize() ? 0 : freeAmount;

                if (dropItemStack.getAmount() <= freeAmount) {
                    pInv.addItem(dropItemStack);
                    continue;
                }

                int dropItemDropAmount = dropItemStack.getAmount() - freeAmount;

                dropItemStack.setAmount(freeAmount);
                pInv.addItem(dropItemStack.clone());

                dropItemStack.setAmount(dropItemDropAmount);
                DropUtil.dropItem(block, dropItemStack);

                continue;
            }

            pInv.addItem(dropItemStack);
        }
        SoundUtil.playSound(block, config.getString("sound"));

        ageable.setAge(0);
        block.setBlockData(ageable);
    }

    @EventHandler
    public void onDispenserEvent(BlockPreDispenseEvent e) {
        Block dispenser = e.getBlock();
        @NotNull FileConfiguration config = QuickHarvest.getInstance().getConfig();

        if (!config.getBoolean("feature.dispenser") || dispenser.getType() != Material.DISPENSER) return;

        ItemStack itemStack = e.getItemStack();
        String itemKey = itemStack.getType().getKey().asString();

        if (!config.getConfigurationSection("reason").getKeys(false).contains(itemKey)) return;

        Block cropBlock = dispenser.getRelative(((Directional) dispenser.getBlockData()).getFacing());

        if (!cropBlock.getType().getKey().asString().equals(config.getString("reason." + itemKey + ".target"))) return;

        Ageable cropAgeable = (Ageable) cropBlock.getBlockData();

        if (cropAgeable.getMaximumAge() != cropAgeable.getAge()) return;

        for (ItemStack dropItemStack: cropBlock.getDrops()) {
            if (dropItemStack.getType() == itemStack.getType()) dropItemStack.setAmount(dropItemStack.getAmount() - 1);
            if (dropItemStack.getAmount() == 0) continue;

            DropUtil.dropItem(cropBlock, dropItemStack);
        }

        dispenser.getWorld().spawnParticle(Particle.SMOKE_NORMAL, cropBlock.getLocation(), 100);

        SoundUtil.playSound(cropBlock, Sound.BLOCK_DISPENSER_FAIL);
        SoundUtil.playSound(cropBlock, config.getString("sound"));

        cropAgeable.setAge(0);
        cropBlock.setBlockData(cropAgeable);
        e.setCancelled(true);
    }
}
