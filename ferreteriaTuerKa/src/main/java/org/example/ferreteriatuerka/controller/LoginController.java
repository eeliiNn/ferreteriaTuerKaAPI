package org.example.ferreteriatuerka.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import org.example.ferreteriatuerka.HelloApplication;
import org.example.ferreteriatuerka.model.Admin;
import org.example.ferreteriatuerka.service.AdminService;


public class LoginController {
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContra;
    @FXML
    private Button btnIngresar;

    private final AdminService adminService = new AdminService();

    @FXML
    public void initialize() {


    }

    @FXML
    private void mostrarAlert(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void Ingresar(){
        try {
            String usuario = txtUsuario.getText().trim();
            String contra = txtContra.getText().trim();

            if(usuario == null || usuario.isBlank() || contra == null || contra.isBlank()){
                mostrarAlert("Error","Completa usuario y contraseña.");
                return;
            }

            Admin admin = adminService.login(usuario, contra);
            if(admin == null || admin.getUsuarioId() <= 0){
                mostrarAlert("Login","Usuario o contraseña incorrectos.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
            Scene scene = new Scene(loader.load(), 560, 420);

            Stage stageNuevo = new Stage();
            stageNuevo.setTitle("Productos");
            stageNuevo.setScene(scene);
            stageNuevo.show();

            Stage stageActual = (Stage) btnIngresar.getScene().getWindow();
            stageActual.close();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlert("Error","No se pudo conectar a la API.");
        }
    }
}