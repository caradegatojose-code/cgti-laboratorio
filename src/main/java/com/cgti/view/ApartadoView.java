package com.cgti.view;

import com.cgti.model.*;
import com.cgti.repository.EquipoRepository;
import com.cgti.service.ApartadoService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDateTime;

public class ApartadoView {

    private ApartadoService apartadoService = new ApartadoService();

    public void mostrar(Stage stage, Usuario usuario) {

        Label titulo = new Label("Apartar Laboratorio");

        ComboBox<Laboratorio> cmbLab = new ComboBox<>();
        cmbLab.getItems().addAll(Laboratorio.values());

        DatePicker dpFecha = new DatePicker();

        ComboBox<String> cmbHoraInicio = new ComboBox<>();
        ComboBox<String> cmbHoraFin = new ComboBox<>();
        for (int h = 7; h <= 21; h++) {
            cmbHoraInicio.getItems().add(String.format("%02d:00", h));
            cmbHoraInicio.getItems().add(String.format("%02d:30", h));
            cmbHoraFin.getItems().add(String.format("%02d:00", h));
            cmbHoraFin.getItems().add(String.format("%02d:30", h));
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
            titulo,
            new Label("Laboratorio:"), cmbLab,
            new Label("Fecha:"), dpFecha,
            new Label("Hora inicio:"), cmbHoraInicio,
            new Label("Hora fin:"), cmbHoraFin
        );

        ComboBox<String> cmbProposito = new ComboBox<>();
        ComboBox<Equipo> cmbEquipo = new ComboBox<>();

        if (usuario.getRol() == Rol.ALUMNO) {
            cmbProposito.getItems().addAll("Tarea", "Exposición", "Uso libre");
            EquipoRepository equipoRepo = new EquipoRepository();
            cmbLab.setOnAction(e -> {
                if (cmbLab.getValue() != null) {
                    cmbEquipo.setItems(FXCollections.observableArrayList(
                        equipoRepo.listarPorLaboratorio(cmbLab.getValue().name())));
                }
            });
            layout.getChildren().addAll(
                new Label("Propósito:"), cmbProposito,
                new Label("Máquina:"), cmbEquipo
            );
        } else {
            cmbProposito.getItems().addAll("Clase", "Asesoría", "Proyecto", "Otro");
            layout.getChildren().addAll(new Label("Propósito:"), cmbProposito);
        }

        Label lblMensaje = new Label("");
        Button btnApartar = new Button("Solicitar Apartado");
        Button btnRegresar = new Button("Regresar");

        btnApartar.setOnAction(e -> {
            if (cmbLab.getValue() == null || dpFecha.getValue() == null ||
                cmbHoraInicio.getValue() == null || cmbHoraFin.getValue() == null ||
                cmbProposito.getValue() == null) {
                lblMensaje.setText("Completa todos los campos");
                return;
            }
            try {
                String[] inicio = cmbHoraInicio.getValue().split(":");
                String[] fin = cmbHoraFin.getValue().split(":");
                LocalDateTime fechaInicio = dpFecha.getValue()
                    .atTime(Integer.parseInt(inicio[0]), Integer.parseInt(inicio[1]));
                LocalDateTime fechaFin = dpFecha.getValue()
                    .atTime(Integer.parseInt(fin[0]), Integer.parseInt(fin[1]));

                if (fechaFin.isBefore(fechaInicio) || fechaFin.equals(fechaInicio)) {
                    lblMensaje.setText("La hora fin debe ser mayor a la hora inicio");
                    return;
                }

                String resultado;
                if (usuario.getRol() == Rol.ALUMNO) {
                    if (cmbEquipo.getValue() == null) {
                        lblMensaje.setText("Selecciona una máquina");
                        return;
                    }
                    resultado = apartadoService.apartarEquipo(usuario, cmbLab.getValue(),
                        cmbEquipo.getValue(), fechaInicio, fechaFin, cmbProposito.getValue());
                } else {
                    resultado = apartadoService.apartarLaboratorio(usuario, cmbLab.getValue(),
                        fechaInicio, fechaFin, cmbProposito.getValue());
                }
                lblMensaje.setText(resultado);
            } catch (Exception ex) {
                lblMensaje.setText("Error: " + ex.getMessage());
            }
        });

        btnRegresar.setOnAction(e -> new DashboardView().mostrar(stage, usuario));
        layout.getChildren().addAll(btnApartar, lblMensaje, btnRegresar);

        Scene scene = new Scene(layout, 500, 650);
        stage.setTitle("Apartado - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
