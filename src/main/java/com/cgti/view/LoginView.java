package com.cgti.view;

import com.cgti.service.UsuarioService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    private UsuarioService service = new UsuarioService();

    public void mostrar(Stage stage) {
        // Elementos de la pantalla
        Label titulo = new Label("CGTI - Control de Laboratorios");
        Label lblCorreo = new Label("Correo:");
        TextField txtCorreo = new TextField();
        Label lblContrasena = new Label("Contraseña:");
        PasswordField txtContrasena = new PasswordField();
        Button btnEntrar = new Button("Entrar");
        Label lblMensaje = new Label("");

        // Acción del botón
        btnEntrar.setOnAction(e -> {
            String correo = txtCorreo.getText();
            String contrasena = txtContrasena.getText();

            if (correo.isEmpty() || contrasena.isEmpty()) {
                lblMensaje.setText("Completa todos los campos");
                return;
            }

            var usuario = service.buscarPorCorreo(correo);
            if (usuario.isPresent() && 
                usuario.get().getContrasena().equals(contrasena)) {
                lblMensaje.setText("Bienvenido " + usuario.get().getNombre());
            } else {
                lblMensaje.setText("Correo o contraseña incorrectos");
            }
        });

        // Layout - organiza elementos verticalmente
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
            titulo, lblCorreo, txtCorreo,
            lblContrasena, txtContrasena,
            btnEntrar, lblMensaje
        );

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Login - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
