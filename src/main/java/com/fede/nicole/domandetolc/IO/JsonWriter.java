package com.fede.nicole.domandetolc.IO;

import com.fede.nicole.domandetolc.modelli.Doucmento;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonWriter {


    public void scriviJson(Doucmento doc, String nome) throws IOException {
        scriviJson(doc, nome,Path.of("src/main/resources/com/fede/nicole/domandetolc/DomandeJson/").toAbsolutePath().toString());
    }

    public void scriviJson(Doucmento doc, String nome,String path) throws IOException {
        File file = new File(path+"/"+nome+".json");
        file.createNewFile();
        Files.writeString(file.toPath(), doc.toJson().toString(4));

    }
}
