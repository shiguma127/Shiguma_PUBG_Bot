package xyz.shiguma.pubgpick.enums;

import java.security.SecureRandom;

public enum Sunhok {
    HA_TINH("Ha Tinh"),
    TAT_MOK("Tat Mok"),
    KHAO("Khao"),
    MONGNAI("Mongnai"),
    CAMP_ALPHA("Camp Alpha"),
    PARADISE_RESORT("Paradise Resort"),
    CAMP_BRAVO("Camp Bravo"),
    BOOTCAMP("Bootcamp"),
    BHAN("Bhan"),
    RUINS("Ruins"),
    QUARRY("Quarry"),
    LAKAWI("Lakawi"),
    TAMBANG("Tambang"),
    PAI_NAN("Pai Nan"),
    KAMPONG("Kampong"),
    NA_KHAM("Na Kham"),
    SHAMEE("Shamee"),
    CAMP_CHARLIE("Camp Charlie"),
    DOCKS("Docks"),
    BANTAI("Bantai"),
    CAVE("Cave");



    private String name;

    private static SecureRandom r = new SecureRandom();

    Sunhok(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Sunhok getRandom() {
        return Sunhok.values()[r.nextInt(Sunhok.values().length)];
    }
}
