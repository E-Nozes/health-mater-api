package br.com.fiap.healthmater.enumerator;

/**
 * Enumerate all possible currency types.
 *
 * @author Gabriel Oliveira
 */
public enum CurrencyEnum {

    BRL("Brazilian Real"),
    USD("U.S. Dollar"),
    EUR("European Euro"),
    JPY("Japanese Yen"),
    GBP("British Pound"),
    CHF("Swiss Franc"),
    CAD("Canadian Dollar"),
    AUD("Autralian Dollar"),
    NZD("New Zealand Dollar"),
    ZAR("South African Rand");

    private final String description;

    CurrencyEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
