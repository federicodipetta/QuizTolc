package com.fede.nicole.domandetolc.parser;

import com.fede.nicole.domandetolc.modelli.SerializzabileJson;
import org.json.JSONObject;

import java.util.Objects;

public final class ModelloRisposta implements SerializzabileJson {
    private final String risposta;
    private final boolean corretta;
    private boolean selezionata = false;

    public ModelloRisposta(String risposta, boolean corretta) {
        this.risposta = risposta;
        this.corretta = corretta;
    }
    public ModelloRisposta(String risposta, boolean corretta,boolean selezionata) {
        this(risposta,corretta);
        this.selezionata = selezionata;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("risposta", risposta)
                .put("corretta", corretta);
    }

    public String risposta() {
        return risposta;
    }

    public boolean corretta() {
        return corretta;
    }
    public boolean selezionata() {
        return selezionata;
    }
    public void seleziona(boolean selezionata) {
        this.selezionata = selezionata;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ModelloRisposta) obj;
        return Objects.equals(this.risposta, that.risposta) &&
                this.corretta == that.corretta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(risposta, corretta);
    }

    @Override
    public String toString() {
        return "ModelloRisposta[" +
                "risposta=" + risposta + ", " +
                "corretta=" + corretta + ']';
    }

}
