package hmd.teatroABC.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 28/11/2024
 * @brief Class ImprimirIngressoController
 */

public class ImprimirIngressoController {
    public Button voltarBotao;
    public Label exibindoLabel;
    private String cpfBuscado;
    public VBox vboxContainer;

    public void initialize() {

    }

    public void criarIngresso() {
        VBox ingresso = new VBox(5);
        ingresso.getStyleClass().clear();
        ingresso.getStyleClass().add("vbox-ingresso");
        Label cpfLabel = new Label("CPF do titular: " + cpfBuscado);
        Label pecaLabel = new Label("Peca: ");    //TODO adicionar forma de buscar
        Label sessaoLabel = new Label("Sessão: ");    //TODO adicionar forma de buscar
        Label assentoLabel = new Label("Assento: ");    //TODO adicionar forma de buscar

        ingresso.getChildren().addAll(cpfLabel, pecaLabel, sessaoLabel, assentoLabel);
        vboxContainer.getChildren().add(ingresso);
    }

    public void voltarTrigger() throws IOException {
        FXMLLoader telaInicialLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_inicial.fxml"));
        Scene telaInicialScene = new Scene(telaInicialLoader.load(), 1189, 810);
        Stage telaInicialStage = (Stage) voltarBotao.getScene().getWindow();
        telaInicialStage.setScene(telaInicialScene);
        telaInicialStage.show();
    }

    public String getCpfBuscado() {
        return cpfBuscado;
    }

    public void setCpfBuscado(String cpfBuscado) {
        this.cpfBuscado = cpfBuscado;
        exibindoLabel.setText(exibindoLabel.getText() + " " + cpfBuscado);
    }
}
