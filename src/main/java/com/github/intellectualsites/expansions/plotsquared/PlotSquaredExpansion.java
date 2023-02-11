package com.github.intellectualsites.expansions.plotsquared;

import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class PlotSquaredExpansion extends PlaceholderExpansion implements Configurable {
    private PlotSquaredApiInterface api;

    public PlotSquaredExpansion() {
        this.api = null;
    }

    @Override
    public boolean canRegister() {
        return Bukkit.getPluginManager().getPlugin(getRequiredPlugin()) != null;
    }

    @Override
    public @NotNull String getAuthor() {
        return "IronApollo";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "plotsquared";
    }

    @Override
    public @Nullable String getRequiredPlugin() {
        return "PlotSquared";
    }

    @Override
    public @NotNull String getVersion() {
        return "2.3";
    }

    @Override
    public boolean register() {
        this.api = determineApi();
        return this.api != null && super.register();
    }

    @Override
    public String onPlaceholderRequest(final Player p, final @NotNull String identifier) {
        return this.api.onPlaceHolderRequest(p, identifier);
    }

    private PlotSquaredApiInterface determineApi() {
        try {
            return new PlotSquaredApiV3(fromConfig(getConfigSection()));
        } catch (NoClassDefFoundError e1) {
            return null;
        }
    }

    @Override
    public Map<String, Object> getDefaults() {
        return new HashMap<String, Object>() {{
            put("ratings.empty", "Not Rated");
        }};
    }

    private Configuration fromConfig(ConfigurationSection section) {
        if (section == null) {
            return new Configuration("No Ratings");
        }

        return new Configuration(
                section.getString("ratings.empty")
        );
    }
}
