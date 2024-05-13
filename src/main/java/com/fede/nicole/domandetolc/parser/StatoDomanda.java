package com.fede.nicole.domandetolc.parser;

public enum StatoDomanda {
    RISPOSTA_CORRETTAMENTE,
    RISPOSTA_SBAGLIATA,

    NON_RISPOSTA;

    public boolean toBoolean() {
        return this == RISPOSTA_CORRETTAMENTE;
    }

    public double toDouble() {
        return switch (this) {
            case RISPOSTA_CORRETTAMENTE -> 1;
            case RISPOSTA_SBAGLIATA -> -0.25;
            case NON_RISPOSTA -> 0;
        };
    }

    public static StatoDomanda fromBoolean(boolean b) {
        return b ? RISPOSTA_CORRETTAMENTE : RISPOSTA_SBAGLIATA;
    }
}
