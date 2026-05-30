package com.cgti.view;

import com.cgti.model.*;
import com.cgti.service.UsuarioService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;

public class RegistroUsuarioView {

    private UsuarioService service = new UsuarioService();

    public void mostrar(Stage stage, Usuario usuarioActual) {

        // Campos
        TextField txtNombre = new TextField();
        TextField txtPaterno = new TextField();
        TextField txtMaterno = new TextField();
        DatePicker dpFecha = new DatePicker();
        TextField txtTelefono = new TextField();
        TextField txtCorreo = new TextField();
        PasswordField txtContrasena = new PasswordField();
        ComboBox<Rol> cmbRol = new ComboBox<>();
        cmbRol.getItems().addAll(Rol.values());

        Label lblMensaje = new Label("");

        // Formulario en grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(new Label("Apellido Paterno:"), 0, 1);
        grid.add(txtPaterno, 1, 1);
        grid.add(new Label("Apellido Materno:"), 0, 2);
        grid.add(txtMaterno, 1, 2);
        grid.add(new Label("Fecha Nacimiento:"), 0, 3);
        grid.add(dpFecha, 1, 3);
        grid.add(new Label("Teléfono (opcional):"), 0, 4);
        grid.add(txtTelefono, 1, 4);
        grid.add(new Label("Correo:"), 0, 5);
        grid.add(txtCorreo, 1, 5);
        grid.add(new Label("Contraseña:"), 0, 6);
        grid.add(txtContrasena, 1, 6);
        grid.add(new Label("Rol:"), 0, 7);
        grid.add(cmbRol, 1, 7);

        Button btnGuardar = new Button("Guardar");
        Button btnCancelar = new Button("Cancelar");

        btnCancelar.setOnAction(e -> new UsuarioView().mostrar(stage, usuarioActual));

        btnGuardar.setOnAction(e -> {
            if (txtNombre.getText().isEmpty() || txtPaterno.getText().isEmpty() ||
                txtCorreo.getText().isEmpty() || txtContrasena.getText().isEmpty() ||
                cmbRol.getValue() == null || dpFecha.getValue() == null) {
                lblMensaje.setText("Completa todos los campos obligatorios");
                return;
            }

            Usuario nuevo = new Usuario();
            nuevo.setNombre(txtNombre.getText());
            nuevo.setNombrePaterno(txtPaterno.getText());
            nuevo.setNombreMaterno(txtMaterno.getText());
            nuevo.setFechaNacimiento(dpFecha.getValue());
            nuevo.setTelefono(txtTelefono.getText());
            nuevo.setCorreo(txtCorreo.getText());
            nuevo.setContrasena(txtContrasena.getText());
            nuevo.setRol(cmbRol.getValue());

            String resultado = service.registrar(nuevo);
            lblMensaje.setText(resultado);

            if (resultado.equals("Usuario registrado correctamente")) {
                new UsuarioView().mostrar(stage, usuarioActual);
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
            new Label("Nuevo Usuario"),
            grid, lblMensaje, btnGuardar, btnCancelar
        );

        Scene scene = new Scene(layout, 500, 550);
        stage.setTitle("Registro - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
