package io.github.aratakileo.quickharvest.util;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public final class SoundUtil {
    private final static byte valueDefault = 1;

    public static void playSound(@NotNull Location location, @NotNull Sound sound) {
        playSound(location, sound, valueDefault, valueDefault);
    }

    public static void playSound(@NotNull Location location, @NotNull Sound sound, float volume, float pitch) {
        location.getWorld().playSound(location, sound, volume, pitch);
    }

    public static void playSound(@NotNull Location location, @NotNull String sound) {
        playSound(location, sound, valueDefault, valueDefault);
    }

    public static void playSound(@NotNull Location location, @NotNull String sound, float volume, float pitch) {
        location.getWorld().playSound(location, sound, volume, pitch);
    }

    public static void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory category) {
        playSound(location, sound, category, valueDefault, valueDefault);
    }

    public static void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch) {
        location.getWorld().playSound(location, sound, category, volume, pitch);
    }

    public static void playSound(@NotNull Location location, @NotNull String sound, @NotNull SoundCategory category) {
        playSound(location, sound, category, valueDefault, valueDefault);
    }

    public static void playSound(@NotNull Location location, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch) {
        location.getWorld().playSound(location, sound, category, volume, pitch);
    }

    public static void playSound(@NotNull Entity entity, @NotNull Sound sound) {
        playSound(entity, sound, valueDefault, valueDefault);
    }

    public static void playSound(@NotNull Entity entity, @NotNull Sound sound, float volume, float pitch) {
        playSound(entity.getLocation(), sound, volume, pitch);
    }

    public static void playSound(@NotNull Entity entity, @NotNull Sound sound, @NotNull SoundCategory category) {
        playSound(entity, sound, category, valueDefault, valueDefault);
    }

    public static void playSound(@NotNull Entity entity, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch) {
        entity.getWorld().playSound(entity, sound, category, volume, pitch);
    }

    public static void playSound(@NotNull Block block, @NotNull Sound sound) {
        playSound(block, sound, valueDefault, valueDefault);
    }

    public static void playSound(@NotNull Block block, @NotNull Sound sound, float volume, float pitch) {
        playSound(block.getLocation(), sound, volume, pitch);
    }

    public static void playSound(@NotNull Block block, @NotNull String sound) {
        playSound(block, sound, valueDefault, valueDefault);
    }

    public static void playSound(@NotNull Block block, @NotNull String sound, float volume, float pitch) {
        playSound(block.getLocation(), sound, volume, pitch);
    }

    public static void playSound(@NotNull Block block, @NotNull Sound sound, @NotNull SoundCategory category) {
        playSound(block, sound, category, valueDefault, valueDefault);
    }

    public static void playSound(@NotNull Block block, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch) {
        playSound(block.getLocation(), sound, category, volume, pitch);
    }

    public static void playSound(@NotNull Block block, @NotNull String sound, @NotNull SoundCategory category) {
        playSound(block, sound, category, valueDefault, valueDefault);
    }

    public static void playSound(@NotNull Block block, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch) {
        playSound(block.getLocation(), sound, category, volume, pitch);
    }
}
