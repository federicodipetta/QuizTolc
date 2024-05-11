package com.fede.nicole.domandetolc.Controllers;

import com.fede.nicole.domandetolc.Controllers.ModelloController.ControllerQuiz;
import javafx.scene.control.Label;

public class FineQuizController {
    public ControllerQuiz controllerQuiz;
    public Label labelValutazione;
    public void init(int punteggio) {
        labelValutazione.setText("Hai totalizzato " + punteggio + " punti");
    }
}
