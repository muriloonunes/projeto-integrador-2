package hmd.teatroABC.controller;

import hmd.teatroABC.model.Peca;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief class TelaInicialController
 */
public class TelaInicialController {
    public Button botaoManha, botaoTarde, botaoNoite, imprimirBotao, estatisticasBotao;

    public HBox botoesBox, postersBox;

    public ImageView imagem1, imagem2;

    public ToggleButton peca1Botao, peca2Botao, peca3Botao;

    static Peca peca1 = new Peca(new File("src/main/resources/images/wicked_poster.jpg"));

    public void initialize() {
        botoesBox.setVisible(false);
    }

    public void addImagens(Stage stage) {
        stage.getScene().getWindow().setOnShowing(event -> imagem1.setImage(peca1.getPosterImg()));
    }

    public void pecaSelecionada() {
        botoesBox.setVisible(true);
    }

    public void comprarIngressoTrigger() throws IOException {
        FXMLLoader compraSceneLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_ingressos.fxml"));
        Scene compraScene = new Scene(compraSceneLoader.load());
        Stage compraStage = (Stage) peca1Botao.getScene().getWindow();
        compraStage.setScene(compraScene);
        compraStage.show();
    }

    public void imprimirIngressoTrigger() throws IOException {
        FXMLLoader digitarCpf = new FXMLLoader(getClass().getResource("/hmd/teatroABC/digitar_cpf_tela.fxml"));
        DigitarCpfController controllerCpf = digitarCpf.getController();
        Scene digitarCpfScene = new Scene(digitarCpf.load());
        Stage digitarCpfStage = new Stage();
        digitarCpfStage.initOwner(imprimirBotao.getScene().getWindow());
        digitarCpfStage.initModality(Modality.WINDOW_MODAL);
        digitarCpfStage.setScene(digitarCpfScene);
        digitarCpfStage.showAndWait();

        String cpfDigitado = controllerCpf.botaoClicado();
        //TODO: Implementar a lógica de impressão do ingresso
    }

    public void verEstatisticasTrigger() {
        //TODO: Implementar a lógica de visualização de estatísticas
    }

    private boolean isValidCPF(String input) {
        if (input != null && input.matches("\\d{11}")) {
            return false; // Retorna o CPF válido
        }
        return input == null || !input.equalsIgnoreCase("admin");
    }
}