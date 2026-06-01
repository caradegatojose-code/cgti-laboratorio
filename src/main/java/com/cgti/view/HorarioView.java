package com.cgti.view;

import com.cgti.model.*;
import com.cgti.repository.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class HorarioView {

    public void mostrar(Stage stage, Usuario admin) {

        Label titulo = new Label("Asignar Horario a Docente");

        // Selector de docente
        ComboBox<Usuario> cmbDocente = new ComboBox<>();
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        List<Usuario> docentes = usuarioRepo.listarTodos().stream()
            .filter(u -> u.getRol() == Rol.DOCENTE)
            .collect(Collectors.toList());
        cmbDocente.setItems(FXCollections.observableArrayList(docentes));

        // Selector de laboratorio
        ComboBox<Laboratorio> cmbLab = new ComboBox<>();
        LaboratorioRepository labRepo = new LaboratorioRepository();
        cmbLab.setItems(FXCollections.observableArrayList(labRepo.listarTodos()));

        // Día de la semana
        ComboBox<DiaSemana> cmbDia = new ComboBox<>();
        cmbDia.getItems().addAll(DiaSemana.values());

        // Horas
        ComboBox<String> cmbHoraInicio = new ComboBox<>();
        ComboBox<String> cmbHoraFin = new ComboBox<>();
        for (int h = 7; h <= 21; h++) {
            cmbHoraInicio.getItems().add(String.format("%02d:00", h));
            cmbHoraInicio.getItems().add(String.format("%02d:30", h));
            cmbHoraFin.getItems().add(String.format("%02d:00", h));
            cmbHoraFin.getItems().add(String.format("%02d:30", h));
        }

        Label lblMensaje = new Label("");
        Button btnGuardar = new Button("Guardar Horario");
        Button btnRegresar = new Button("Regresar");

        btnGuardar.setOnAction(e -> {
            if (cmbDocente.getValue() == null || cmbLab.getValue() == null ||
                cmbDia.getValue() == null || cmbHoraInicio.getValue() == null ||
                cmbHoraFin.getValue() == null) {
                lblMensaje.setText("Completa todos los campos");
                return;
            }
            String[] inicio = cmbHoraInicio.getValue().split(":");
            String[] fin = cmbHoraFin.getValue().split(":");
            LocalTime horaInicio = LocalTime.of(Integer.parseInt(inicio[0]), Integer.parseInt(inicio[1]));
            LocalTime horaFin = LocalTime.of(Integer.parseInt(fin[0]), Integer.parseInt(fin[1]));

            if (horaFin.isBefore(horaInicio) || horaFin.equals(horaInicio)) {
                lblMensaje.setText("La hora fin debe ser mayor a la hora inicio");
                return;
            }

            HorarioDocente horario = new HorarioDocente();
            horario.setDocente((Docente) cmbDocente.getValue());
            horario.setLaboratorio(cmbLab.getValue());
            horario.setDiaSemana(cmbDia.getValue());
            horario.setHoraInicio(horaInicio);
            horario.setHoraFin(horaFin);

            HorarioRepository horarioRepo = new HorarioRepository();
            horarioRepo.guardar(horario);
            lblMensaje.setText("Horario guardado correctamente");
        });

        btnRegresar.setOnAction(e -> new DashboardView().mostrar(stage, admin));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
            titulo,
            new Label("Docente:"), cmbDocente,
            new Label("Laboratorio:"), cmbLab,
            new Label("Día:"), cmbDia,
            new Label("Hora inicio:"), cmbHoraInicio,
            new Label("Hora fin:"), cmbHoraFin,
            btnGuardar, lblMensaje, btnRegresar
        );

        Scene scene = new Scene(layout, 500, 600);
        stage.setTitle("Horarios - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
