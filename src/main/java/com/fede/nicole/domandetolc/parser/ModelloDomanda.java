package com.fede.nicole.domandetolc.parser;

import com.fede.nicole.domandetolc.modelli.SerializzabileJson;
import org.controlsfx.control.PropertySheet;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ModelloDomanda implements SerializzabileJson {
    private final String domanda;
    private List<ModelloRisposta> risposte;
    private String doucento;
    private int numero;
    private StatoDomanda corretta = StatoDomanda.NON_RISPOSTA;

    private int numeroDomanda;
    public ModelloDomanda(String domanda, List<ModelloRisposta> risposte,int numeroDomanda) {
        this.domanda = domanda;
        this.risposte = risposte;
        this.numeroDomanda = numeroDomanda;
    }


    /**
     * Risponde alla domanda, se la risposta non è presente
     * allora la domanda viene lasciata, quindi per lasciare la domanda
     * si può usare il merotodo rispondi con "" oppure lascia()
     * @see ModelloDomanda#lascia()
     * @param risposta
     */
    public void ripsondi(String risposta) {
        corretta = risposte.stream()
                .filter(r -> r.risposta().equals(risposta))
                .map(ModelloRisposta::corretta)
                .findFirst()
                .map(StatoDomanda::fromBoolean)
                .orElse(StatoDomanda.NON_RISPOSTA);
    }

    public void ripsondi(int i) {
        risposte.forEach(r -> r.seleziona(false));
        risposte.get(i).seleziona(true);
        corretta = risposte.get(i).corretta() ? StatoDomanda.RISPOSTA_CORRETTAMENTE : StatoDomanda.RISPOSTA_SBAGLIATA;
    }

    /**
     * Lascia la domanda
     */
    public void lascia() {
        corretta = StatoDomanda.NON_RISPOSTA;
    }

    public ModelloRisposta rispostaCorretta() {
        return risposte.stream()
                .filter(ModelloRisposta::corretta)
                .findFirst()
                .orElseThrow();
    }

    public List<ModelloRisposta> risposteCorrette() {
        return risposte.stream()
                .filter(ModelloRisposta::corretta)
                .toList();
    }

    public StatoDomanda stato() {
        return corretta;
    }

    public String domanda() {
        return domanda;
    }

    public List<ModelloRisposta> risposte() {
        return risposte;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ModelloDomanda) obj;
        return Objects.equals(this.domanda, that.domanda) &&
                Objects.equals(this.risposte, that.risposte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domanda, risposte);
    }

    @Override
    public String toString() {
        return "ModelloDomanda[" +
                "domanda=" + domanda + ", " +
                "risposte=" + risposte + ']';
    }


    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("domanda", domanda);
        jsonObject.put("risposte", risposte.stream().map(ModelloRisposta::toJson).toList());
        return jsonObject;
    }

    public String getDoucento() {
        return doucento;
    }

    public void setDoucento(String doucento) {
        this.doucento = doucento;
    }

    public int getNumero() {
        return numero;
    }

    public void MischiaRisposte() {
        Collections.shuffle(risposte);
    }

    public ModelloRisposta getRisposta(String risposta) {
        return risposte.stream().filter(r -> r.risposta().equals(risposta)).findFirst().orElseThrow();
    }

    public int daiIndiceDomandaSelezionata(){
        if (StatoDomanda.NON_RISPOSTA == this.stato()) return -1;
        return this.risposte.indexOf(
                this.risposte.stream()
                        .filter(ModelloRisposta::selezionata)
                        .findFirst()
                        .orElseThrow()
        );
    }

    public ModelloRisposta getRisposta(int i) {
        return risposte.get(i);
    }



}
