package com.cgti.view;

import com.cgti.model.Alumno;
import com.cgti.model.Rol;
import com.cgti.model.Usuario;
import com.cgti.service.UsuarioService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Optional;

public class AlumnoAccesoView {

    private UsuarioService service = new UsuarioService();

    public void mostrar(Stage stage) {

        Label titulo = new Label("Acceso Alumno");
        Label lblMatricula = new Label("Matrícula:");
        TextField txtMatricula = new TextField();
        txtMatricula.setPromptText("Ingresa tu matrícula");
        Button btnEntrar = new Button("Entrar");
        Button btnRegresar = new Button("Regresar");
        Label lblMensaje = new Label("");

        btnEntrar.setOnAction(e -> {
            String matricula = txtMatricula.getText().trim();
            if (matricula.isEmpty()) {
                lblMensaje.setText("Ingresa tu matrícula");
                return;
            }

            // Buscar alumno por matrícula
            Optional<Usuario> resultado = service.buscarPorMatricula(matricula);

            if (resultado.isPresent()) {
                // Alumno existe, entra directo
                new DashboardView().mostrar(stage, resultado.get());
            } else {
                // No existe, mostrar registro
                new RegistroAlumnoView().mostrar(stage, matricula);
            }
        });

        btnRegresar.setOnAction(e -> new InicioView().mostrar(stage));

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titulo, lblMatricula, txtMatricula, btnEntrar, lblMensaje, btnRegresar);

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Acceso Alumno - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
