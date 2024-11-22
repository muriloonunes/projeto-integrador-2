package hmd.teatroABC.view;

import hmd.teatroABC.controller.TelaInicialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 19/11/2024
 * @brief Class TeatroView
 */

public class TeatroView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_inicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); //950, 810
        TelaInicialController controller = fxmlLoader.getController();

        stage.setTitle("Teatro ABC");
        stage.setScene(scene);
        controller.addImagens(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}