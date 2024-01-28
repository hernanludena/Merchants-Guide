package com.hludena.romans;


/**
 * Enumeración de números romanos con sus símbolos.
 */
public enum RomanNumeral {
    I("I"), V("V"), X("X"), L("L"), C("C"), D("D"), M("M");

    private final String symbol;

    RomanNumeral(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
