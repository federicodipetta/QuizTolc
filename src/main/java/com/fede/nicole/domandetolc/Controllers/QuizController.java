package com.fede.nicole.domandetolc.Controllers;

import com.fede.nicole.domandetolc.Controllers.ModelloController.ControllerQuiz;
import com.fede.nicole.domandetolc.parser.ModelloDomanda;
import com.fede.nicole.domandetolc.parser.StatoDomanda;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class QuizController {
    public ControllerQuiz controllerQuiz;

    @FXML
    public RadioButton radioA;
    @FXML
    public RadioButton radioB;
    @FXML
    public RadioButton radioC;
    @FXML
    public RadioButton radioD;
    @FXML
    public RadioButton radioE;
    @FXML
    public Button btnSuccessivo;
    @FXML
    public Button btnPrecedente;
    @FXML
    public Label domanda;

    public void initialize() {


    }

    public void init(){
        preparaDomanda(controllerQuiz.daiDomanda());
    }


    public void onPrecedente() {
        this.controllerQuiz.rispondi(getRispostaSelezionata());
        this.controllerQuiz.precedenteDomanda();
        preparaDomanda(controllerQuiz.daiDomanda());
    }

    public void onSuccessivo() {
        this.controllerQuiz.rispondi(getRispostaSelezionata());
        this.controllerQuiz.prossimaDomanda();
        preparaDomanda(controllerQuiz.daiDomanda());
    }

    public void onInvia() throws IOException {
        this.controllerQuiz.invia();
    }

    private void preparaDomanda(ModelloDomanda domanda) {
        svuotaRadioButtons();
        if(! (domanda.stato() == StatoDomanda.NON_RISPOSTA)) {
            switch(domanda.daiIndiceDomandaSelezionata()){
                case 0:
                    radioA.setSelected(true);
                    break;
                case 1:
                    radioB.setSelected(true);
                    break;
                case 2:
                    radioC.setSelected(true);
                    break;
                case 3:
                    radioD.setSelected(true);
                    break;
                case 4:
                    radioE.setSelected(true);
                    break;
            }
        }
        this.domanda.setText(domanda.domanda());
        this.radioA.setText(domanda.risposte().get(0).risposta());
        this.radioB.setText(domanda.risposte().get(1).risposta());
        this.radioC.setText(domanda.risposte().get(2).risposta());
        this.radioD.setText(domanda.risposte().get(3).risposta());
        this.radioE.setText(domanda.risposte().get(4).risposta());
    }

    private void svuotaRadioButtons() {
        radioA.setSelected(false);
        radioB.setSelected(false);
        radioC.setSelected(false);
        radioD.setSelected(false);
        radioE.setSelected(false);
    }

    public int getRispostaSelezionata(){
        if(radioA.isSelected())
            return 0;
        if(radioB.isSelected())
            return 1;
        if(radioC.isSelected())
            return 2;
        if(radioD.isSelected())
            return 3;
        if(radioE.isSelected())
            return 4;
        return -1;
    }
}
