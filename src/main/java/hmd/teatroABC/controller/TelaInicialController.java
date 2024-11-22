package hmd.teatroABC.controller;

import hmd.teatroABC.model.Peca;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief class TelaInicialController
 */
public class TelaInicialController {
    public Button botaoManha, botaoTarde, botaoNoite;

    public HBox botoesBox, postersBox;

    public ImageView imagem1, imagem2;

    public ToggleButton peca1Botao, peca2Botao, peca3Botao;

    static Peca peca1 = new Peca(new File("src/main/resources/images/wicked_poster.jpg"));

    public void initialize() {
        botoesBox.setVisible(false);
    }

    public void addImagens(Stage stage) {
        stage.getScene().getWindow().setOnShowing(event -> {
            imagem1.setImage(peca1.getPosterImg());
        });
    }

    public void pecaSelecionada() {
        botoesBox.setVisible(true);
    }

    public void botaoTurnoClicado() throws IOException {
        FXMLLoader compraSceneLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_ingressos.fxml"));
        Scene compraScene = new Scene(compraSceneLoader.load());
        Stage compraStage = (Stage) peca1Botao.getScene().getWindow();
        compraStage.setScene(compraScene);
        compraStage.show();
    }
}