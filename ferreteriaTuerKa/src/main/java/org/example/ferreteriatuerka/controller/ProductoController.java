package org.example.ferreteriatuerka.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.ferreteriatuerka.HelloApplication;
import org.example.ferreteriatuerka.model.Categoria;
import org.example.ferreteriatuerka.model.Producto;
import org.example.ferreteriatuerka.model.Proveedor;
import org.example.ferreteriatuerka.service.CategoriaService;
import org.example.ferreteriatuerka.service.ProductoService;
import org.example.ferreteriatuerka.service.ProveedorService;

import java.util.List;

public class ProductoController {


    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private TextField txtProveedorNombre;
    @FXML private TextField txtImagen;

    @FXML private ComboBox<Categoria> cmbCategoria;

    @FXML private TableView<Producto> tblProductos;
    @FXML private TableColumn<Producto, Integer> colId;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colCategoria;
    @FXML private TableColumn<Producto, String> colProveedor;
    @FXML private TableColumn<Producto, String> colImg;

    private final ProductoService productoService = new ProductoService();
    private final ProveedorService proveedorService = new ProveedorService();
    private final CategoriaService categoriaService = new CategoriaService();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("productoId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaNombre"));
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedorNombre"));
        colImg.setCellValueFactory(new PropertyValueFactory<>("imagen"));

        colImg.setCellFactory(column -> new TableCell<Producto, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagenUrl, boolean empty) {
                super.updateItem(imagenUrl, empty);

                if (empty || imagenUrl == null || imagenUrl.isEmpty()) {
                    setGraphic(null);
                } else {

                    Image image = new Image(
                            imagenUrl,
                            80,
                            80,
                            true,
                            true,
                            true
                    );

                    imageView.setFitWidth(80);
                    imageView.setFitHeight(80);
                    imageView.setPreserveRatio(true);

                    imageView.setImage(image);
                    setGraphic(imageView);
                }
            }
        });


        cargarCategorias();
        cargarProductos();

        tblProductos.getSelectionModel().selectedItemProperty().addListener((obs, old, p) -> {
            if (p != null) {
                txtNombre.setText(p.getNombre());
                txtDescripcion.setText(p.getDescripcion());
                txtPrecio.setText(String.valueOf(p.getPrecio()));
                txtStock.setText(String.valueOf(p.getStock()));
                txtProveedorNombre.setText(p.getProveedorNombre());
                txtImagen.setText(p.getImagen());

                if (p.getCategoriaNombre() != null) {
                    cmbCategoria.getItems().stream()
                            .filter(c -> c.getNombre().equals(p.getCategoriaNombre()))
                            .findFirst()
                            .ifPresent(cmbCategoria::setValue);
                }
            }
        });
    }

    @FXML
    private void cargarProductos() {
        try {
            List<Producto> lista = productoService.getProductos();
            tblProductos.setItems(FXCollections.observableArrayList(lista));
        } catch (Exception e) {
            mostrarAlert("Error", "No se pudieron cargar los productos.");
        }
    }

    private void cargarCategorias() {
        try {
            List<Categoria> lista = categoriaService.getCategorias();
            cmbCategoria.setItems(FXCollections.observableArrayList(lista));
        } catch (Exception e) {
            mostrarAlert("Error", "No se pudieron cargar las categorías.");
        }
    }


    @FXML
    private void guardarProducto() {
        try {

            if (txtNombre.getText().isBlank() || txtPrecio.getText().isBlank()) {
                mostrarAlert("Error", "Nombre y Precio son obligatorios.");
                return;
            }

            double precio = Double.parseDouble(txtPrecio.getText());
            int stock = txtStock.getText().isBlank() ? 0 :
                    Integer.parseInt(txtStock.getText());

            if (precio < 0 || stock < 0) {
                mostrarAlert("Error", "Precio y Stock no pueden ser negativos.");
                return;
            }

            Categoria categoria = cmbCategoria.getValue();
            if (categoria == null) {
                mostrarAlert("Error", "Selecciona una categoría.");
                return;
            }

            if (txtProveedorNombre.getText().isBlank()) {
                mostrarAlert("Error", "Debes escribir un proveedor.");
                return;
            }

            Proveedor proveedor = proveedorService
                    .buscarPorNombre(txtProveedorNombre.getText());

            if (proveedor == null) {
                mostrarAlert("Error", "El proveedor no existe.");
                return;
            }

            Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();

            Producto p = new Producto();

            if (seleccionado != null) {
                p.setProductoId(seleccionado.getProductoId());
            } else {
                p.setProductoId(0);
            }

            p.setNombre(txtNombre.getText());
            p.setDescripcion(txtDescripcion.getText());
            p.setPrecio(precio);
            p.setStock(stock);
            p.setImagen(txtImagen.getText());

            p.setCategoriaId(categoria.getCategoriaId());
            p.setProveedorId(proveedor.getProveedorId());

            if (seleccionado == null) {
                productoService.crearProducto(p);
                mostrarAlert("Éxito", "Producto creado correctamente.");
            } else {
                productoService.actualizarProducto(
                        seleccionado.getProductoId(), p);
                mostrarAlert("Éxito", "Producto actualizado correctamente.");
            }

            limpiarCampos();
            cargarProductos();

        } catch (NumberFormatException e) {
            mostrarAlert("Error", "Precio y Stock deben ser números.");
        } catch (Exception e) {
            mostrarAlert("Error", "No se pudo guardar el producto.");
        }
    }

    @FXML
    private void eliminarProducto() {

        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlert("Error", "Selecciona un producto para eliminar.");
            return;
        }

        try {
            productoService.eliminarProducto(seleccionado.getProductoId());
            mostrarAlert("Éxito", "Producto eliminado correctamente.");
            limpiarCampos();
            cargarProductos();
        } catch (Exception e) {
            mostrarAlert("Error", "No se pudo eliminar el producto.");
        }
    }


    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        txtStock.clear();
        txtProveedorNombre.clear();
        txtImagen.clear();
        cmbCategoria.setValue(null);
        tblProductos.getSelectionModel().clearSelection();
    }

    private void mostrarAlert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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

            Stage stageActual = (Stage) tblProductos.getScene().getWindow();
            stageActual.close();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlert("Error","No se ha podido abrir la ventana.");
        }
    }
}