package org.example.ferreteriatuerka.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.example.ferreteriatuerka.HelloApplication;
import javafx.scene.control.Alert;

public class MenuController {

    @FXML
    private Label lblProveedor;

    @FXML
    private Label lblProducto;

    @FXML
    private Label lblVenta;
    @FXML
    ImageView imgSide;


    @FXML
    public void initialize() {
    }

    @FXML
    private void Alert(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    @FXML
    private void Productos(){
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Productos.fxml"));
            Scene scene = new Scene(loader.load(), 800, 680);

            Stage stageNuevo = new Stage();
            stageNuevo.setTitle("Productos");
            stageNuevo.setScene(scene);
            stageNuevo.show();

            Stage stageActual = (Stage) lblProducto.getScene().getWindow();
            stageActual.close();
        } catch (Exception e) {
            e.printStackTrace();
            Alert("Error","No se ha podido abrir la ventana.");
        }

    }
    @FXML
    private void Proveedores(){
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Proveedores.fxml"));
            Scene scene = new Scene(loader.load(), 800, 560);

            Stage stageNuevo = new Stage();
            stageNuevo.setTitle("Proveedores");
            stageNuevo.setScene(scene);
            stageNuevo.show();

            Stage stageActual = (Stage) lblProveedor.getScene().getWindow();
            stageActual.close();
        } catch (Exception e) {
            e.printStackTrace();
            Alert("Error","No se ha podido abrir la ventana.");
        }

    }

    @FXML
    private void Ventas(){
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Ventas.fxml"));
            Scene scene = new Scene(loader.load(), 960, 720);

            Stage stageNuevo = new Stage();
            stageNuevo.setTitle("Ventas");
            stageNuevo.setScene(scene);
            stageNuevo.show();

            Stage stageActual = (Stage) lblVenta.getScene().getWindow();
            stageActual.close();
        } catch (Exception e) {
            e.printStackTrace();
            Alert("Error","No se ha podido abrir la ventana.");
        }

    }

}
