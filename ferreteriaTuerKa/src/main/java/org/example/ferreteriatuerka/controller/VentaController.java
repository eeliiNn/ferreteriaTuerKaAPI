package org.example.ferreteriatuerka.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.ferreteriatuerka.HelloApplication;
import org.example.ferreteriatuerka.model.Venta;
import org.example.ferreteriatuerka.service.VentaService;

import java.util.List;

public class VentaController {

    @FXML private TableView<Venta> tblVentas;

    @FXML private TableColumn<Venta, Integer> colVentaId;
    @FXML private TableColumn<Venta, String> colEmailComprador;
    @FXML private TableColumn<Venta, String> colFecha;
    @FXML private TableColumn<Venta, Double> colTotal;
    @FXML private TableColumn<Venta, Integer> colProductoId;
    @FXML private TableColumn<Venta, String> colNombreProducto;
    @FXML private TableColumn<Venta, Integer> colCantidad;
    @FXML private TableColumn<Venta, Double> colPrecioUnitario;

    @FXML private TextField txtVentaId; // opcional (solo display)
    @FXML private TextField txtEmailComprador;
    @FXML private TextField txtFecha;
    @FXML private TextField txtTotal;
    @FXML private TextField txtProductoId;
    @FXML private TextField txtNombreProducto;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtPrecioUnitario;

    private final VentaService ventaService = new VentaService();

    @FXML
    public void initialize() {
        colVentaId.setCellValueFactory(new PropertyValueFactory<>("ventaId"));
        colEmailComprador.setCellValueFactory(new PropertyValueFactory<>("emailComprador"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colProductoId.setCellValueFactory(new PropertyValueFactory<>("productoId"));
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));

        tblVentas.getSelectionModel().selectedItemProperty().addListener((obs, old, v) -> {
            if (v != null) {
                if (txtVentaId != null) txtVentaId.setText(String.valueOf(v.getVentaId()));
                txtEmailComprador.setText(v.getEmailComprador());
                txtFecha.setText(v.getFecha());
                txtTotal.setText(String.valueOf(v.getTotal()));
                txtProductoId.setText(String.valueOf(v.getProductoId()));
                txtNombreProducto.setText(v.getNombreProducto());
                txtCantidad.setText(String.valueOf(v.getCantidad()));
                txtPrecioUnitario.setText(String.valueOf(v.getPrecioUnitario()));
            } else {
                limpiar();
            }
        });

        cargarVentas();
    }

    private void alert(String t, String m) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }

    private void limpiar() {
        if (txtVentaId != null) txtVentaId.clear();
        txtEmailComprador.clear();
        txtFecha.clear();
        txtTotal.clear();
        txtProductoId.clear();
        txtNombreProducto.clear();
        txtCantidad.clear();
        txtPrecioUnitario.clear();
    }

    @FXML
    private void cargarVentas() {
        try {
            List<Venta> lista = ventaService.getVentas();
            tblVentas.setItems(FXCollections.observableArrayList(lista));
        } catch (Exception e) {
            e.printStackTrace();
            alert("Error", "No se pudo cargar ventas (API).");
        }
    }

    @FXML
    private void guardarVenta() {
        try {
            Venta nueva = leerForm();
            Venta creada = ventaService.crearVenta(nueva);
            alert("Listo", "Venta creada con ID: " + creada.getVentaId());
            cargarVentas();
            limpiar();
        } catch (Exception e) {
            e.printStackTrace();
            alert("Error", "No se pudo crear venta.");
        }
    }

    @FXML
    private void actualizarVenta() {
        try {
            Venta seleccionada = tblVentas.getSelectionModel().getSelectedItem();
            if (seleccionada == null) {
                alert("Error", "Selecciona una venta para actualizar.");
                return;
            }

            Venta cambios = leerForm();
            cambios.setVentaId(seleccionada.getVentaId());

            ventaService.actualizarVenta(seleccionada.getVentaId(), cambios);
            alert("Listo", "Venta actualizada.");
            cargarVentas();
        } catch (Exception e) {
            e.printStackTrace();
            alert("Error", "No se pudo actualizar venta (API).");
        }
    }

    @FXML
    private void eliminarVenta() {
        try {
            Venta seleccionada = tblVentas.getSelectionModel().getSelectedItem();
            if (seleccionada == null) {
                alert("Error", "Selecciona una venta.");
                return;
            }

            boolean ok = ventaService.eliminarVenta(seleccionada.getVentaId());
            if (ok) {
                alert("Listo", "Venta eliminada.");
                cargarVentas();
                limpiar();
            } else {
                alert("Error", "No se pudo eliminar la venta.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert("Error", "No se pudo eliminar venta (API).");
        }
    }

    private Venta leerForm() {
        String email = txtEmailComprador.getText().trim();
        String fecha = txtFecha.getText().trim();


        if (email.isEmpty()) throw new IllegalArgumentException("Email comprador obligatorio.");
        if (fecha.isEmpty()) throw new IllegalArgumentException("Fecha obligatoria.");

        int productoId = Integer.parseInt(txtProductoId.getText().trim());
        String nombreProducto = txtNombreProducto.getText().trim();
        int cantidad = Integer.parseInt(txtCantidad.getText().trim());
        double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText().trim());

        double total;
        if (txtTotal.getText().isBlank()) {
            total = cantidad * precioUnitario;
        } else {
            total = Double.parseDouble(txtTotal.getText().trim());
        }

        Venta v = new Venta();
        v.setEmailComprador(email);
        v.setFecha(fecha);
        v.setTotal(total);
        v.setProductoId(productoId);
        v.setNombreProducto(nombreProducto);
        v.setCantidad(cantidad);
        v.setPrecioUnitario(precioUnitario);
        return v;
    }

    @FXML
    private void VolverMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
            Scene scene = new Scene(loader.load(), 560, 420);

            Stage stageNuevo = new Stage();
            stageNuevo.setTitle("Menú");
            stageNuevo.setScene(scene);
            stageNuevo.show();

            Stage stageActual = (Stage) tblVentas.getScene().getWindow();
            stageActual.close();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlert("Error", "No se ha podido abrir la ventana.");
        }
    }
    private void mostrarAlert(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}