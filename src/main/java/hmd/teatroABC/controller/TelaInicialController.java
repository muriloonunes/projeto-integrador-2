package hmd.teatroABC.controller;

import hmd.teatroABC.model.Peca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TelaInicialController {
    @FXML
    private Button peca1Botao, peca2Botao, peca3Botao;

    @FXML
    private HBox botoesBox;

    @FXML
    private Button botaoManha, botaoTarde, botaoNoite;

    @FXML
    public ImageView imagem1;

    static Peca peca1 = new Peca(new File("src/main/resources/images/wicked_poster.jpg"));

    public void initialize() {
        botoesBox.setVisible(false);
    }

    public void addImagens(Stage stage) {
        stage.getScene().getWindow().setOnShowing(event -> {
            imagem1.setImage(peca1.getPosterImg());
        });
    }

    public void turnoClicado() throws IOException {
        botoesBox.setVisible(true);
    }

    public void botaoTurnoClicado() throws IOException {
        FXMLLoader compraSceneLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_compra.fxml"));
        Scene compraScene = new Scene(compraSceneLoader.load(), 1020, 700);
        Stage compraStage = (Stage) peca1Botao.getScene().getWindow();
        compraStage.setScene(compraScene);
        compraStage.show();
    }
}