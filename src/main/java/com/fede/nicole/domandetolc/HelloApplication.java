package com.fede.nicole.domandetolc;

import com.fede.nicole.domandetolc.Controllers.ModelloController.ControllerQuiz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ControllerQuiz controllerQuiz = new ControllerQuiz(stage);
        controllerQuiz.vaiAForm();
    }

    public static void main(String[] args) {
        launch();
    }
}