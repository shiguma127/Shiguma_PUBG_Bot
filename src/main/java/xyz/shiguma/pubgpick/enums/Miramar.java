package xyz.shiguma.pubgpick.enums;

import java.security.SecureRandom;

public enum Miramar {

    RUINS("Ruins"),
    LA_COBRERIA("La Cobreria"),
    TORRE_AHUMADA("Torre Ahumada"),
    CAMPO_MILITER("Campo Militer"),
    TRAILER_PARK("Trailer Park"),
    CRATER_FIELDS("Crater Fields"),
    WATAR_TREATMENT("Water Treatment"),
    CRUZ_DEL_VALLE("Cruz del Valle"),
    TIERRA_BRONCA("Tierra Bronca"),
    EL_POZO("El Pozo"),
    SAN_MARTIN("San Martin"),
    HACIENDA_DEL_PATRON("Hacienda del Patron"),
    EL_AZAHAR("El Azahar"),
    POWER_GRID("Power Grid"),
    GRAVEYARD("Graveyard"),
    MINAS_GENERALES("Minas Generales"),
    JUNKYARD("Junkyard"),
    MONTE_NUEVO("Monte Nuevo"),
    PECADO("Pecado"),
    LA_NEMDITA("La Bendita"),
    IMPALA("Impala"),
    LADRILLERA("Ladrillera"),
    CHUMACERA("Chumacera"),
    LOS_LEONES("Los Leones"),
    VALLE_DEL_MAR("Valle del Mar"),
    PUERTO_PARAISO("Puerto Paraiso"),
    MINAS_DEL_SUR("Minas del Sur"),
    LOS_HIGOS("Los Higos"),
    PRISON("Prison"),
    MINAS_DEL_VALLE("Minas del Valle"),
    ALCANTARA("Alcantara");

    private String name;

    private static SecureRandom r = new SecureRandom();

    Miramar(String name) {
        this.name = name;
    }

    public String getname() {
        return this.name;
    }

    public static Miramar getRundom() {
        return Miramar.values()[r.nextInt(Miramar.values().length)];
    }


}
