package com.cgti;

import com.cgti.view.InicioView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        new InicioView().mostrar(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
