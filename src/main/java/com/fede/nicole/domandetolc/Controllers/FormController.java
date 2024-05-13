package com.fede.nicole.domandetolc.Controllers;

import com.fede.nicole.domandetolc.Controllers.ModelloController.ControllerQuiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;


public class FormController {
    public ControllerQuiz controllerQuiz;

    @FXML
    public Button bottonePath;
    @FXML
    public TextField pathDomanda;

    @FXML
    public ChoiceBox<String> selectFiles;

    @FXML
    public TextField inputInizio;

    @FXML
    public TextField inputFine;


    public void initialize() {
        selectFiles.getItems().add("Biologia");
        selectFiles.getItems().add("Chimica");
    }
    @FXML
    public void onSelezionaPath() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona il file delle domande");
        File file = fileChooser.showOpenDialog(bottonePath.getScene().getWindow());
        if(file != null){
            pathDomanda.setText(file.getAbsolutePath());
            controllerQuiz.aggiungiPath(file.getAbsolutePath());
        }

    }

    public void invia(ActionEvent actionEvent) throws IOException, URISyntaxException {
        if(selectFiles.getValue().equals("Biologia")){
            controllerQuiz.aggiungiPath(Paths.get(getClass().getResource("/com/fede/nicole/domandetolc/Domande/Biologia.txt").toURI()).toString());
        }else if(selectFiles.getValue().equals("Chimica")){
            controllerQuiz.aggiungiPath(Paths.get(getClass().getResource("/com/fede/nicole/domandetolc/Domande/Chimica.txt").toURI()).toString());
        }

        controllerQuiz.aggiungiLimite(Integer.parseInt(inputInizio.getText()), Integer.parseInt(inputFine.getText()));
        controllerQuiz.vaiAQuiz();
    }
}
