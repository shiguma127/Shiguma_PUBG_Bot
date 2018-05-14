package xyz.shiguma.pubgpick.enums;

import java.security.SecureRandom;

public enum Erangel {
    ZHARKI("Zharki"),
    SHOOTING_RANGE("Shootiing Range"),
    SEVERNY("Severny"),
    STALBER("Stalber"),
    KAMESHIKI("Kameshki"),
    GEORGOPOL("Georgopol"),
    RUINS("Ruins"),
    ROZHOK("Rozhok"),
    YASNAYA_POLYANA("Yasnaya Polyana"),
    HOSPITAL("Hospital"),
    SCHOOL("School"),
    MANSION("Mansion"),
    LIPOVKA("Lipovka"),
    GATKA("Gatka"),
    POCHINKI("Pochinki"),
    SHELTER("Shelter"),
    PRISON("Prison"),
    FARM("Farm"),
    MYLTA("Mylta"),
    MYLTA_POWER("Mylta Power"),
    QUARRY("Quarry"),
    PRIMORSK("Primorsk"),
    FERRY_PIER("Ferry Pier"),
    NOVOREPNOYE("Novorepnoye"),
    MILITARY_BASE("Military Base");

    private String name;

    private static SecureRandom r = new SecureRandom();

    Erangel(String name) {
        this.name = name;
    }

    public String getname() {
        return this.name;
    }

    public static Erangel getRandom() {
        return Erangel.values()[r.nextInt(Erangel.values().length)];
    }
}
