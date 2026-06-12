package com.cgti.view;

import com.cgti.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView {

    public void mostrar(Stage stage, Usuario usuario) {

        Label bienvenida = new Label("Bienvenido, " + usuario.getNombre() + " " + usuario.getNombrePaterno());
        Label rol = new Label("Rol: " + usuario.getRol());

        VBox menu = new VBox(10);

        if (usuario.getRol() == Rol.ADMINISTRADOR) {
            Button btnUsuarios = new Button("Gestionar Usuarios");
            btnUsuarios.setMinWidth(200);
            btnUsuarios.setOnAction(e -> new UsuarioView().mostrar(stage, usuario));
            menu.getChildren().addAll(btnUsuarios );
            Button btnEquipos = new Button("Gestionar Equipos");
            btnEquipos.setMinWidth(200);
            btnEquipos.setOnAction(e -> new EquipoView().mostrar(stage, usuario));
            menu.getChildren().add(btnEquipos);

        } else if (usuario.getRol() == Rol.ALUMNO) {
            Button btnRegistrar = new Button("Registrar Entrada/Salida");
            Button btnApartar = new Button("Apartar Laboratorio");
            btnRegistrar.setMinWidth(200);
            btnApartar.setMinWidth(200);
            btnRegistrar.setOnAction(e -> new RegistroView().mostrar(stage, usuario));
            btnApartar.setOnAction(e -> new ApartadoView().mostrar(stage, usuario));
            menu.getChildren().addAll(btnRegistrar, btnApartar);
        }

        Button btnSalir = new Button("Salir al inicio");
        btnSalir.setMinWidth(200);
        btnSalir.setOnAction(e -> new InicioView().mostrar(stage));

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(bienvenida, rol, menu, btnSalir);

        Scene scene = new Scene(layout, 500, 400);
        stage.setTitle("Dashboard - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
