package org.example.ferreteriatuerka.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.ferreteriatuerka.HelloApplication;
import org.example.ferreteriatuerka.model.Proveedor;
import org.example.ferreteriatuerka.service.ProveedorService;

import java.util.List;

public class ProveedorController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    @FXML private TextField txtDireccion;

    @FXML private TableView<Proveedor> tblProveedores;
    @FXML private TableColumn<Proveedor, Integer> colId;
    @FXML private TableColumn<Proveedor, String> colNombre;
    @FXML private TableColumn<Proveedor, String> colTelefono;
    @FXML private TableColumn<Proveedor, String> colEmail;
    @FXML private TableColumn<Proveedor, String> colDireccion;

    private final ProveedorService proveedorService = new ProveedorService();



    @FXML
    public void initialize() {

        // Columnas (usan getters: getIdProveedor(), getNombre(), etc.)
        colId.setCellValueFactory(new PropertyValueFactory<>("proveedorId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        // Al seleccionar fila -> llenar inputs
        tblProveedores.getSelectionModel().selectedItemProperty().addListener((obs, old, p) -> {
            if (p != null) {
                txtNombre.setText(p.getNombre());
                txtTelefono.setText(p.getTelefono());
                txtEmail.setText(p.getEmail());
                txtDireccion.setText(p.getDireccion());
            }
        });

        cargarProveedores();
    }

    @FXML
    private void cargarProveedores() {
        try {
            List<Proveedor> lista = proveedorService.getProveedores();
            tblProveedores.setItems(FXCollections.observableArrayList(lista));
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlert("Error", "No se pudo cargar proveedores (API).");
        }
    }

    @FXML
    private void guardarProveedor() {
        try {
            if (txtNombre.getText().isBlank() || txtTelefono.getText().isBlank()) {
                mostrarAlert("Error", "Nombre y Teléfono son obligatorios.");
                return;
            }

            Proveedor seleccionado = tblProveedores.getSelectionModel().getSelectedItem();

            if (seleccionado == null) {
                Proveedor nuevo = new Proveedor(
                        0,
                        txtNombre.getText(),
                        txtTelefono.getText(),
                        txtEmail.getText(),
                        txtDireccion.getText()
                );

                proveedorService.crearProveedor(nuevo);
                mostrarAlert("Listo", "Proveedor creado.");
            } else {
                Proveedor cambios = new Proveedor(
                        seleccionado.getProveedorId(),
                        txtNombre.getText(),
                        txtTelefono.getText(),
                        txtEmail.getText(),
                        txtDireccion.getText()
                );

                proveedorService.actualizarProveedor(seleccionado.getProveedorId(), cambios);
                mostrarAlert("Listo", "Proveedor actualizado.");
            }

            limpiarCampos();
            cargarProveedores();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlert("Error", "No se pudo guardar proveedor (API).");
        }
    }

    @FXML
    private void eliminarProveedor() {
        try {
            Proveedor seleccionado = tblProveedores.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlert("Error", "Selecciona un proveedor para eliminar.");
                return;
            }

            boolean ok = proveedorService.eliminarProveedor(seleccionado.getProveedorId());
            if (ok) {
                mostrarAlert("Listo", "Proveedor eliminado.");
                limpiarCampos();
                cargarProveedores();
            } else {
                mostrarAlert("Error", "No se pudo eliminar (API).");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlert("Error", "No se pudo eliminar proveedor (API).");
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtDireccion.clear();
        tblProveedores.getSelectionModel().clearSelection();
    }


    private void mostrarAlert(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    private void VolverMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
            Scene scene = new Scene(loader.load(), 560, 420);

            Stage stageNuevo = new Stage();
            stageNuevo.setTitle("Menú");
            stageNuevo.setScene(scene);
            stageNuevo.show();

            Stage stageActual = (Stage) tblProveedores.getScene().getWindow();
            stageActual.close();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlert("Error","No se ha podido abrir la ventana.");
        }

    }
}
