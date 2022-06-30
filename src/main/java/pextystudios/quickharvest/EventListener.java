package pextystudios.quickharvest;

import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            @NotNull FileConfiguration config = QuickHarvest.getInstance().getConfig();

            Block block = Objects.requireNonNull(e.getClickedBlock());
            String blockKey = block.getType().getKey().asString();
            ItemStack item = e.getItem();
            String itemKey = e.getMaterial().getKey().asString();

            if (config.getConfigurationSection("reason").getKeys(false).contains(itemKey) && Objects.equals(config.getString("reason." + itemKey + ".target"), blockKey)) {
                BlockData blockData = block.getBlockData();
                Ageable ageable = (Ageable) blockData;

                if (ageable.getAge() == ageable.getMaximumAge()) {
                    Inventory pInv = e.getPlayer().getInventory();

                    for (ItemStack dropItem : block.getDrops()) {
                        if (item != null && item.getType() == dropItem.getType())
                            dropItem.setAmount(dropItem.getAmount() - 1);

                        if (pInv.firstEmpty() == -1) {
                            int allInvAmount = 0;
                            for (ItemStack invItem : pInv.getContents()) {
                                if (invItem != null && invItem.getType() == dropItem.getType())
                                    allInvAmount += invItem.getAmount();
                            }

                            int freeAmount = dropItem.getMaxStackSize();
                            freeAmount = freeAmount - (allInvAmount % freeAmount);
                            freeAmount = freeAmount == dropItem.getMaxStackSize() ? 0 : freeAmount;

                            if (dropItem.getAmount() <= freeAmount)
                                pInv.addItem(dropItem);
                            else {
                                int dropItemDropAmount = dropItem.getAmount() - freeAmount;

                                dropItem.setAmount(freeAmount);
                                pInv.addItem(dropItem.clone());

                                dropItem.setAmount(dropItemDropAmount);
                                block.getWorld().dropItemNaturally(block.getLocation(), dropItem);
                            }
                        } else
                            pInv.addItem(dropItem);
                    }

                    e.getPlayer().playSound(block.getLocation(), Objects.requireNonNull(config.getString("sound")), 1, 1);

                    ageable.setAge(0);
                    block.setBlockData(ageable);
                }
            }
        }
    }
}
