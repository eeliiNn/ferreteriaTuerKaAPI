package org.example.ferreteriatuerka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ferreteria-login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        stage.setTitle("Inicio de sesión");
        stage.setScene(scene);
        stage.show();
    }
}
