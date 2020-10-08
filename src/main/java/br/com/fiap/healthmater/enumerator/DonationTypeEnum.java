package br.com.fiap.healthmater.enumerator;

/**
 * Enumerate all possible donation types.
 *
 * @author Gabriel Oliveira
 */
public enum DonationTypeEnum {

    RIM("Rim"),
    FIGADO("Fígado"),
    CORACAO("Coração"),
    PANCREAS("Pancreas"),
    PULMAO("Pulmão"),
    CORNEA("Córnea"),
    PELE("Pele"),
    OSSOS("Ossos"),
    VALVULAS_CARDIACAS("Válvulas Cardíacas"),
    CARTILAGEM("Cartilagem"),
    MEDULA_OSSEA("Medula Ossea");

    private String description;

    DonationTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
