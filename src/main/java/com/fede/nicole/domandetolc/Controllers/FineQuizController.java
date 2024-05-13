package com.fede.nicole.domandetolc.Controllers;

import com.fede.nicole.domandetolc.Controllers.ModelloController.ControllerQuiz;
import javafx.scene.control.Label;

import java.io.IOException;

public class FineQuizController {
    public ControllerQuiz controllerQuiz;
    public Label labelValutazione;
    public void init(double punteggio) {
        labelValutazione.setText("Hai totalizzato " + punteggio + " punti");
    }

    public void onNuovo() throws IOException {
        controllerQuiz.vaiAForm();
    }

    public void onEsci() {
        System.exit(0);
    }

    public void onRevisione() throws IOException {
        controllerQuiz.vaiARevisione();
    }
}
