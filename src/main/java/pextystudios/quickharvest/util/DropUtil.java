package pextystudios.quickharvest.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public final class DropUtil {

    private final static boolean dropNaturallyDefault = true;

    public static @NotNull Collection<Item> dropItems(@NotNull Location location, @NotNull Collection<ItemStack> itemStacks) {
        return dropItems(location, itemStacks, dropNaturallyDefault);
    }

    public static @NotNull Collection<Item> dropItems(@NotNull Location location, @NotNull Collection<ItemStack> itemStacks, boolean dropNaturally) {
        ArrayList<Item> items = new ArrayList<>();

        for (ItemStack itemStack: itemStacks) items.add(dropItem(location, itemStack, dropNaturally));

        return items;
    }

    public static @NotNull Collection<Item> dropItems(@NotNull Entity entity, @NotNull Collection<ItemStack> itemStacks) {
        return dropItems(entity, itemStacks, dropNaturallyDefault);
    }

    public static @NotNull Collection<Item> dropItems(@NotNull Entity entity, @NotNull Collection<ItemStack> itemStacks, boolean dropNaturally) {
        return dropItems(entity.getLocation(), itemStacks, dropNaturally);
    }

    public static @NotNull Collection<Item> dropItems(@NotNull Block block, @NotNull Collection<ItemStack> itemStacks) {
        return dropItems(block, itemStacks, dropNaturallyDefault);
    }

    public static @NotNull Collection<Item> dropItems(@NotNull Block block, @NotNull Collection<ItemStack> itemStacks, boolean dropNaturally) {
        return dropItems(block.getLocation(), itemStacks, dropNaturally);
    }

    public static @NotNull Item dropItem(@NotNull Location location, @NotNull ItemStack itemStack) {
        return dropItem(location, itemStack, dropNaturallyDefault);
    }

    public static @NotNull Item dropItem(@NotNull Location location, @NotNull ItemStack itemStack, boolean dropNaturally) {
        return dropNaturally?
                location.getWorld().dropItemNaturally(location, itemStack)
                :
                location.getWorld().dropItem(location, itemStack);
    }

    public static @NotNull Item dropItem(@NotNull Entity entity, @NotNull ItemStack itemStack) {
        return dropItem(entity, itemStack, dropNaturallyDefault);
    }

    public static @NotNull Item dropItem(@NotNull Entity entity, @NotNull ItemStack itemStack, boolean dropNaturally) {
        return dropItem(entity.getLocation(), itemStack, dropNaturally);
    }

    public static @NotNull Item dropItem(@NotNull Block block, @NotNull ItemStack itemStack) {
        return dropItem(block, itemStack, dropNaturallyDefault);
    }

    public static @NotNull Item dropItem(@NotNull Block block, @NotNull ItemStack itemStack, boolean dropNaturally) {
        return dropItem(block.getLocation(), itemStack, dropNaturally);
    }
}
