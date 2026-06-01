package com.cgti.view;
import com.cgti.model.Docente;
import com.cgti.model.Usuario;
import com.cgti.model.Rol;
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
        Button btnCerrarSesion = new Button("Cerrar Sesión");


        // Menú según rol
        VBox menu = new VBox(10);
        if (usuario.getRol() == Rol.ADMINISTRADOR) {
            Button btnUsuarios = new Button("Gestionar Usuarios");
            btnUsuarios.setOnAction(e -> new UsuarioView().mostrar(stage, usuario));
            Button btnHorarios = new Button("Asignar Horarios");
            btnHorarios.setOnAction(e -> new HorarioView().mostrar(stage, usuario));
            menu.getChildren().add(btnHorarios); 
            menu.getChildren().add(btnUsuarios);
        } else if (usuario.getRol() == Rol.DOCENTE) {
            Button btnRegistrar = new Button("Registrar Entrada/Salida");
            btnRegistrar.setOnAction(e -> new RegistroView().mostrar(stage, usuario));
            menu.getChildren().add(btnRegistrar);
            Button btnApartar = new Button("Apartar Laboratorio");
            btnApartar.setOnAction(e -> new ApartadoView().mostrar(stage, usuario));
            menu.getChildren().add(btnApartar);
        } else if (usuario.getRol() == Rol.ALUMNO) {
            Button btnConsultar = new Button("Registrar uso de Laboratorio");
            btnConsultar.setOnAction(e -> new RegistroView().mostrar(stage, usuario));
            Button btnApartar = new Button("Apartar Laboratorio");
            btnApartar.setOnAction(e -> new ApartadoView().mostrar(stage, usuario));
            menu.getChildren().add(btnApartar);
            menu.getChildren().add(btnConsultar);        }

        // Cerrar sesión
        btnCerrarSesion.setOnAction(e -> new LoginView().mostrar(stage));

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(bienvenida, rol, menu, btnCerrarSesion);

        Scene scene = new Scene(layout, 500, 400);
        stage.setTitle("Dashboard - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
