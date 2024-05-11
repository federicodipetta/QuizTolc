package com.fede.nicole.domandetolc.parser;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DocumentoParser {


    public List<ModelloDomanda> parse(String path) throws FileNotFoundException {
        path = Paths.get(path).toAbsolutePath().toString();
        Scanner scanner = new Scanner(new File(path));
        List<ModelloDomanda> domande = new LinkedList<>();
        scanner.useDelimiter("Domanda N°");
        Pattern pattern = Pattern.compile("Domanda N°  (\\d+)(.*?)([A-E]\\).*)", Pattern.DOTALL);
        while (scanner.hasNext()) {
            String domanda = "Domanda N° "+scanner.next();

            Matcher matcher = pattern.matcher(domanda);

            while (matcher.find()) {
                String[] risposte = matcher.group(3).split("([A-E])\\)");
                risposte = Arrays.copyOfRange(risposte, 1, risposte.length);
                List<ModelloRisposta> risposteList = new ArrayList<>();
                for (int i = 0; i < risposte.length; i++) {
                    risposteList.add(new ModelloRisposta(pulisciStringa(risposte[i]), i == 0));
                }
                ModelloDomanda modelloDomanda = new ModelloDomanda(pulisciStringa(matcher.group(2)), risposteList, Integer.parseInt(matcher.group(1)));
                domande.add(modelloDomanda);
            }
        }
        return domande;
    }

    private String pulisciStringa(String s){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<s.length();i++){
            if(i<4 && !acapoCarage(s.charAt(i)))
                builder.append(s.charAt(i));
            if(i>s.length() - 4 && !acapoCarage(s.charAt(i)))
                builder.append(s.charAt(i));
            if(i>=4 && i<= s.length()-4)
                builder.append(s.charAt(i));
        }
        return new String(builder);
    }

    private boolean acapoCarage(char c) {
        return c == '\n' || c=='\r';
    }

}
