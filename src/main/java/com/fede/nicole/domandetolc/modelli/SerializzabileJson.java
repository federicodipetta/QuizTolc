package com.fede.nicole.domandetolc.modelli;

import org.json.JSONObject;

/**
 * chi implementa questa interfaccia può essere convertito in un oggetto JSON
 */
public interface SerializzabileJson {
    /**
     * Converte l'oggetto in un oggetto JSON
     * @return l'oggetto JSON
     */
    JSONObject toJson();
}
