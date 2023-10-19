package io.github.aratakileo.quickharvest.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class DropItemUtil {
    private final static boolean DROP_NATURALLY = true;

    public static @NotNull Item drop(@NotNull Location location, @NotNull ItemStack itemStack) {
        return drop(location, itemStack, DROP_NATURALLY);
    }

    public static @NotNull Item drop(@NotNull Location location, @NotNull ItemStack itemStack, boolean isDroppingNaturally) {
        return isDroppingNaturally?
                location.getWorld().dropItemNaturally(location, itemStack)
                :
                location.getWorld().dropItem(location, itemStack);
    }

    public static @NotNull Item drop(@NotNull Entity entity, @NotNull ItemStack itemStack) {
        return drop(entity, itemStack, DROP_NATURALLY);
    }

    public static @NotNull Item drop(@NotNull Entity entity, @NotNull ItemStack itemStack, boolean isDroppingNaturally) {
        return drop(entity.getLocation(), itemStack, isDroppingNaturally);
    }

    public static @NotNull Item drop(@NotNull Block block, @NotNull ItemStack itemStack) {
        return drop(block, itemStack, DROP_NATURALLY);
    }

    public static @NotNull Item drop(@NotNull Block block, @NotNull ItemStack itemStack, boolean isDroppingNaturally) {
        return drop(block.getLocation(), itemStack, isDroppingNaturally);
    }
}
