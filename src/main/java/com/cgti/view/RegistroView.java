package com.cgti.view;

import com.cgti.model.*;
import com.cgti.service.RegistroService;
import com.cgti.repository.LaboratorioRepository;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistroView {

    private RegistroService registroService = new RegistroService();

    public void mostrar(Stage stage, Usuario usuario) {

        Label titulo = new Label("Registro de Laboratorio");
        Label lblUsuario = new Label("Usuario: " + usuario.getNombre() + " " + usuario.getNombrePaterno());

        Label lblLab = new Label("Selecciona laboratorio:");
        ComboBox<Laboratorio> cmbLab = new ComboBox<>();
        LaboratorioRepository labRepo = new LaboratorioRepository();
        cmbLab.setItems(FXCollections.observableArrayList(labRepo.listarTodos()));

        Button btnEntrada = new Button("Registrar Entrada");
        Button btnSalida = new Button("registrar salida");
        Button btnRegresar = new Button("Regresar");
        Label lblMensaje = new Label("");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titulo, lblUsuario, lblLab, cmbLab);

        // Propósito solo para alumnos
        ComboBox<String> cmbProposito = new ComboBox<>();
        if (usuario.getRol() == Rol.ALUMNO) {
            cmbProposito.getItems().addAll("Tarea", "Exposición", "Uso libre");
            layout.getChildren().addAll(new Label("Propósito:"), cmbProposito);
        }

        layout.getChildren().addAll(btnEntrada, btnSalida, lblMensaje, btnRegresar);

        btnSalida.setOnAction(e -> {
        String resultado = registroService.registrarSalida(usuario.getId());
        lblMensaje.setText(resultado);
        });

        btnEntrada.setOnAction(e -> {
            if (cmbLab.getValue() == null) {
                lblMensaje.setText("Selecciona un laboratorio");
                return;
            }
            if (usuario.getRol() == Rol.ALUMNO && cmbProposito.getValue() == null) {
                lblMensaje.setText("Selecciona un propósito");
                return;
            }
            String proposito = usuario.getRol() == Rol.ALUMNO ? cmbProposito.getValue() : "Clase";
            String resultado = registroService.registrarEntrada(usuario, cmbLab.getValue(), proposito);
            lblMensaje.setText(resultado);
        });

        btnRegresar.setOnAction(e -> new DashboardView().mostrar(stage, usuario));

        Scene scene = new Scene(layout, 500, 450);
        stage.setTitle("Registro - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
