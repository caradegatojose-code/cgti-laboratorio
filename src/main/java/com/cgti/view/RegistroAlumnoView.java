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

public class RegistroAlumnoView {

    private UsuarioService service = new UsuarioService();

    public void mostrar(Stage stage, String matricula) {

        Label titulo = new Label("Registro de Alumno");
        Label lblInfo = new Label("Matrícula: " + matricula);

        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        TextField txtPaterno = new TextField();
        txtPaterno.setPromptText("Apellido Paterno");
        TextField txtMaterno = new TextField();
        txtMaterno.setPromptText("Apellido Materno");
        DatePicker dpFecha = new DatePicker();
        TextField txtTelefono = new TextField();
        txtTelefono.setPromptText("Teléfono (opcional)");
        TextField txtCorreo = new TextField();
        txtCorreo.setPromptText("Correo");
        TextField txtCarrera = new TextField();
        txtCarrera.setPromptText("Carrera");
        TextField txtCuatrimestre = new TextField();
        txtCuatrimestre.setPromptText("Cuatrimestre");
        TextField txtGrupo = new TextField();
        txtGrupo.setPromptText("Grupo");

        Label lblMensaje = new Label("");
        Button btnGuardar = new Button("Registrarme");
        Button btnRegresar = new Button("Regresar");

        btnGuardar.setOnAction(e -> {
            if (txtNombre.getText().isEmpty() || txtPaterno.getText().isEmpty() ||
                txtCarrera.getText().isEmpty() || txtCuatrimestre.getText().isEmpty() ||
                txtGrupo.getText().isEmpty() || txtCorreo.getText().isEmpty() ||
                dpFecha.getValue() == null) {
                lblMensaje.setText("Completa todos los campos obligatorios");
                return;
            }
            try {
                Alumno alumno = new Alumno();
                alumno.setNombre(txtNombre.getText());
                alumno.setNombrePaterno(txtPaterno.getText());
                alumno.setNombreMaterno(txtMaterno.getText());
                alumno.setFechaNacimiento(dpFecha.getValue());
                alumno.setTelefono(txtTelefono.getText());
                alumno.setCorreo(txtCorreo.getText());
                alumno.setMatricula(matricula);
                alumno.setCarrera(txtCarrera.getText());
                alumno.setCuatrimestre(Integer.parseInt(txtCuatrimestre.getText()));
                alumno.setGrupo(txtGrupo.getText());
                alumno.setRol(Rol.ALUMNO);
                alumno.setContrasena("sin-contrasena");

                String resultado = service.registrar(alumno);
                lblMensaje.setText(resultado);

                if (resultado.equals("Usuario registrado correctamente")) {
                    service.buscarPorMatricula(matricula).ifPresent(u ->
                        new DashboardView().mostrar(stage, u));
                }
            } catch (Exception ex) {
                lblMensaje.setText("Error: " + ex.getMessage());
            }
        });

        btnRegresar.setOnAction(e -> new AlumnoAccesoView().mostrar(stage));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
            titulo, lblInfo,
            txtNombre, txtPaterno, txtMaterno,
            new Label("Fecha de nacimiento:"), dpFecha,
            txtTelefono, txtCorreo,
            txtCarrera, txtCuatrimestre, txtGrupo,
            btnGuardar, lblMensaje, btnRegresar
        );

        Scene scene = new Scene(layout, 450, 650);
        stage.setTitle("Registro Alumno - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
