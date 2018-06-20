package xyz.shiguma.pubgpick.enums;

import java.security.SecureRandom;

public enum Weapon {
    AWM("AWM"),
    M24("M24"),
    KAR98K("Kar98K"),
    VSS("Vss"),
    WIN98("Win98"),
    SKS("SKS"),
    MK14("Mk14-EBR"),
    Mini("Mini-14"),
    AKM("AKM"),
    Groza("Groza"),
    M16A4("M16A4"),
    M416("M416"),
    SCAR("SCAR-L"),
    QBZ("QBZ"),
    AUG("AUG A3"),
    UZI("Micro UZi"),
    TOMMY("トミーガン"),
    UMP("UMP-9"),
    VECTOR("Vector"),
    S12K("S12K"),
    S1897("S1897"),
    S686("S686"),
    M249("M249"),
    DP("DP-28"),
    P1911("P1911"),
    P92("P92"),
    R1895("R18965"),
    P18C("P18C"),
    SAWED("ソードオフ"),
    R45("R45"),
    CROSS("クロスボウ"),
    CWORBAR("バール"),
    MACHETE("マチェテ"),
    PAN("フライパン"),
    SICKLE("鎌"),
    PUNCH("パンチ"),
    GRENADE("手榴弾");
    private String name;

    private static SecureRandom r = new SecureRandom();

    Weapon(String name) {
        this.name = name;
    }

    public String getname() {
        return this.name;
    }

    public static Weapon getRandom() {
        return Weapon.values()[r.nextInt(Weapon.values().length)];
    }
}
