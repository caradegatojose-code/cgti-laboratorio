package com.cgti.view;

import com.cgti.model.Usuario;
import com.cgti.service.UsuarioService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class UsuarioView {

    private UsuarioService service = new UsuarioService();

    public void mostrar(Stage stage, Usuario usuarioActual) {
        // Tabla
        TableView<Usuario> tabla = new TableView<>();

        TableColumn<Usuario, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Usuario, String> colPaterno = new TableColumn<>("Apellido Paterno");
        colPaterno.setCellValueFactory(new PropertyValueFactory<>("nombrePaterno"));

        TableColumn<Usuario, String> colCorreo = new TableColumn<>("Correo");
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        TableColumn<Usuario, String> colRol = new TableColumn<>("Rol");
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));

        tabla.getColumns().addAll(colNombre, colPaterno, colCorreo, colRol);

        // Cargar datos
        List<Usuario> usuarios = service.listarTodos();
        tabla.setItems(FXCollections.observableArrayList(usuarios));

        // Botones
        Button btnNuevo = new Button("Nuevo Usuario");
        btnNuevo.setOnAction(e -> new RegistroUsuarioView().mostrar(stage, usuarioActual));
        Button btnRegresar = new Button("Regresar");
        btnRegresar.setOnAction(e -> new DashboardView().mostrar(stage, usuarioActual));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(new Label("Gestión de Usuarios"), tabla, btnNuevo, btnRegresar);
        
        Scene scene = new Scene(layout, 700, 500);
        stage.setTitle("Usuarios - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
