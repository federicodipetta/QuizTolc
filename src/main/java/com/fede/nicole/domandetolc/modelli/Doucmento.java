package com.fede.nicole.domandetolc.modelli;

import com.fede.nicole.domandetolc.parser.ModelloDomanda;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Doucmento implements SerializzabileJson {
    private String nome;
    private List<ModelloDomanda> domande;

    public Doucmento(List<ModelloDomanda> domande,String nome) {
        this.domande = domande;
        this.nome = nome;
    }

    public List<ModelloDomanda> getDomande() {
        return domande;
    }

    public void tagliaDomande(int inizio, int fine) {
        domande = domande.stream().filter(x-> x.getNumero() >= inizio && x.getNumero() <= fine).toList();
    }

    @Override
    public JSONObject toJson() {
        JSONArray array =  new JSONArray(domande.stream().map(ModelloDomanda::toJson).toList());
        return new JSONObject().put("domande", array);
    }

    public String getNome() {
        return nome;
    }
}
