package com.cgti.view;

import com.cgti.model.Usuario;
import com.cgti.service.UsuarioService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class UsuarioView {

    private UsuarioService service = new UsuarioService();

    public void mostrar(Stage stage, Usuario usuarioActual) {

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

        List<Usuario> usuarios = service.listarTodos();
        tabla.setItems(FXCollections.observableArrayList(usuarios));

        Button btnNuevo = new Button("Nuevo Usuario");
        Button btnEliminar = new Button("Eliminar Usuario");
        Button btnRegresar = new Button("Regresar");

        btnNuevo.setOnAction(e -> new RegistroUsuarioView().mostrar(stage, usuarioActual));

        btnEliminar.setOnAction(e -> {
            Usuario seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                return;
            }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmar");
            confirm.setContentText("¿Eliminar a " + seleccionado.getNombre() + "?");
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    service.eliminar(seleccionado.getId());
                    new UsuarioView().mostrar(stage, usuarioActual);
                }
            });
        });

        btnRegresar.setOnAction(e -> new DashboardView().mostrar(stage, usuarioActual));

        HBox botones = new HBox(10, btnNuevo, btnEliminar, btnRegresar);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(new Label("Gestión de Usuarios"), tabla, botones);

        Scene scene = new Scene(layout, 700, 500);
        stage.setTitle("Usuarios - CGTI");
        stage.setScene(scene);
        stage.show();
    }
}
