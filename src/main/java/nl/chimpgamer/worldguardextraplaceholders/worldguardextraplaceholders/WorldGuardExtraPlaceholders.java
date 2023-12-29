package nl.chimpgamer.worldguardextraplaceholders.worldguardextraplaceholders;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class WorldGuardExtraPlaceholders extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "worldguardextraplaceholders";
    }

    @Override
    public @NotNull String getAuthor() {
        return "ChimpGamer";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean canRegister() {
        return getPlaceholderAPI().getServer().getPluginManager().isPluginEnabled("WorldGuard");
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        String[] temp;
        if (params.startsWith("players_in_region_")) {
            temp = params.replaceFirst("players_in_region_", "").split("_");
            var worldName = temp[0];
            var regionName = temp[1];

            var region = getRegionByName(worldName, regionName);
            if (region == null) return "Region or World might not exist!";
            return String.valueOf(countPlayersInRegion(region));
        }
        return null;
    }

    @Nullable
    private ProtectedRegion getRegionByName(@NotNull String worldName, @NotNull String regionName) {
        var worldGuardPlatform = WorldGuard.getInstance().getPlatform();
        var regionContainer = worldGuardPlatform.getRegionContainer();

        var world = getPlaceholderAPI().getServer().getWorld(worldName);
        if (world == null) return null;
        var regionManager = regionContainer.get(BukkitAdapter.adapt(world));
        if (regionManager == null) return null;

        return regionManager.getRegion(regionName);
    }

    private long countPlayersInRegion(@NotNull ProtectedRegion region) {
        return getPlaceholderAPI().getServer().getOnlinePlayers().stream().filter(player -> region.contains(BukkitAdapter.asBlockVector(player.getLocation()))).count();
    }
}
