package org.icesi.junimosvalley;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.icesi.junimosvalley.model.HelloController;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 1200, 600);
        //Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("Junimos Valley!");
        stage.setScene(scene);

        Image icon = new Image(getClass().getResourceAsStream("/icon/icon.png")); // Asegúrate de usar la ruta correcta
        stage.getIcons().add(icon);

        stage.setResizable(false);

        stage.setOnCloseRequest(event -> {
            HelloController controller = fxmlLoader.getController();
            controller.setRunning();
        });
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}