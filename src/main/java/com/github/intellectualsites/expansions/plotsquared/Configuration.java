package com.github.intellectualsites.expansions.plotsquared;

import org.bukkit.configuration.ConfigurationSection;

public class Configuration {
    private final String noRatings;

    public Configuration(String noRatings) {
        this.noRatings = noRatings;
    }

    public String getNoRatings() {
        return noRatings;
    }
}
