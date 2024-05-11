package com.fede.nicole.domandetolc.Controllers.ModelloController;

import com.fede.nicole.domandetolc.Controllers.FineQuizController;
import com.fede.nicole.domandetolc.Controllers.FormController;
import com.fede.nicole.domandetolc.Controllers.QuizController;
import com.fede.nicole.domandetolc.HelloApplication;
import com.fede.nicole.domandetolc.modelli.Doucmento;
import com.fede.nicole.domandetolc.parser.DocumentoParser;
import com.fede.nicole.domandetolc.parser.ModelloDomanda;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControllerQuiz {

    List<Doucmento> documenti;
    List<ModelloDomanda> domande;
    private int domandaAttuale;
    int inizo;
    int fine;
    List<String> paths;
    Stage stage;
    public ControllerQuiz(Stage stage){
        this.stage = stage;
        this.paths = new ArrayList<>();
        this.documenti = new ArrayList<>();
        this.domande = new ArrayList<>();
    }

    public void aggiungiPaths(List<String> paths){
        this.paths = paths;
    }

    public void aggiungiPath(String path){
        this.paths.clear();
        this.paths.add(path);
    }
    public void aggiungiLimite(int inizo, int fine){
        this.inizo = inizo;
        this.fine = fine;
    }

     public void vaiAQuiz() throws IOException {
         DocumentoParser documentoParser = new DocumentoParser();
         Doucmento doc = new Doucmento(documentoParser.parse(paths.get(0)).subList(inizo,fine), Path.of(paths.get(0)).getFileName().toString());
         documenti.add(doc);
         this.domande = getDomande(doc);
         MischiaDomandeRisposte();
         this.domande = this.domande.subList(0,10);
         FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Quiz.fxml"));
         Scene scene = new Scene(fxmlLoader.load(), 320, 240);
         QuizController c = fxmlLoader.getController();
         c.controllerQuiz = this;
         c.init();
         stage.setTitle("Quiz");
         stage.setScene(scene);
         stage.show();
     }

     public void vaiAForm() throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Form.fxml"));
         Scene scene = new Scene(fxmlLoader.load(), 600, 400);
         FormController c = fxmlLoader.getController();
         c.controllerQuiz = this;
         stage.setTitle("Form");
         stage.setScene(scene);
         stage.show();
     }

     public void vaiAFineQuiz() throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FineQuiz.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            FineQuizController c = fxmlLoader.getController();
            c.controllerQuiz = this;
            stage.setTitle("Fine Quiz");
            stage.setScene(scene);
            stage.show();
     }

     public void invia() throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource ("FineQuiz.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            FineQuizController c = fxmlLoader.getController();
            c.controllerQuiz = this;
            c.init(this.domande.stream().mapToInt(x->x.stato().toInt()).sum());
            stage.setTitle("Fine Quiz");
            stage.setScene(scene);
            stage.show();
        }


     public ModelloDomanda daiDomanda(){
        return domande.get(domandaAttuale);
     }

    public void prossimaDomanda(){
        if(domandaAttuale + 1  < domande.size())
            domandaAttuale++;
    }

    public void precedenteDomanda(){
        if(domandaAttuale - 1 >= 0)
            domandaAttuale--;
    }

    public boolean isFinito(){
        return domandaAttuale == domande.size();
    }



     private List<ModelloDomanda> getDomande(Doucmento doc){
        return doc.getDomande().stream()
                .filter(domanda -> domanda.getNumero() >= inizo && domanda.getNumero() <= fine)
                .toList();
     }

     private void MischiaDomandeRisposte(){
        this.domande = new ArrayList<>(this.domande);
        Collections.shuffle(this.domande);
        for(ModelloDomanda domanda : domande){
            domanda.MischiaRisposte();
        }
     }


    public void rispondi(int rispostaSelezionata) {
        if(rispostaSelezionata == -1)
            domande.get(domandaAttuale).lascia();
        else
            domande.get(domandaAttuale).ripsondi(rispostaSelezionata);
    }
}
