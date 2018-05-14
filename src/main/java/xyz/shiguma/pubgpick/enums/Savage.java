package xyz.shiguma.pubgpick.enums;

import java.security.SecureRandom;

public enum Savage {

    COASTAL("Coastal"),
    COCONUT_FARM("Coconut Farm"),
    LOGGING_CAMP_N("Logging Camp(北)"),
    MANUFACTURING("Manufacturing"),
    ABANDONED_RESORT("Abandoned Resort"),
    BOOTCAMP_ALPHA("Bootcamp Alpha"),
    BOOTCAMP_BRAVO("Bootcamp Bravo"),
    TRANING_CENTER("Training Center"),
    BANYAN_GROVE("Banyan Grove"),
    SWAP_TEMPLE("Swamp Temple"),
    RIVER_TOWN("River Town"),
    RICE_FARMINGTOWN("Rice FarmingTown"),
    LOGGING_CAMP_S("Logging Camp(南)"),
    DOCK("Dock"),
    COMMERCE("Commerce"),
    BOOTCAMP_CHARLIE("Bootcamp Charlie"),
    BEACH("Beach");


    private String name;

    private static SecureRandom r = new SecureRandom();

    Savage(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Savage getRandom() {
        return Savage.values()[r.nextInt(Savage.values().length)];
    }
}
