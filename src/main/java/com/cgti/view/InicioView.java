package com.cgti.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InicioView {

    public void mostrar(Stage stage) {

        Label titulo = new Label("CGTI - Control de Laboratorios");
        Button btnAdmin = new Button("Administrador");
        Button btnAlumno = new Button("Alumno");

        btnAdmin.setMinWidth(200);
        btnAlumno.setMinWidth(200);

        btnAdmin.setOnAction(e -> new LoginView().mostrar(stage));
        btnAlumno.setOnAction(e -> new AlumnoAccesoView().mostrar(stage));

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titulo, btnAdmin, btnAlumno);

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("CGTI - Inicio");
        stage.setScene(scene);
        stage.show();
    }
}
