package hmd.teatroABC.controller;

import hmd.teatroABC.util.FXMLLoaderUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 29/11/2024
 * @brief Class CompraFinalizadaController
 */

public class CompraFinalizadaController {
    public Button okBotao, naoBotao;
    private Stage stageAnterior;
    private String cpfDigitado;

    public void imprimirIngressoTrigger() throws IOException {
        Stage stageCompraFinalizada = (Stage) okBotao.getScene().getWindow();
        stageCompraFinalizada.close();

        FXMLLoader imprimirLoader = FXMLLoaderUtil.loadFXML("/hmd/teatroABC/imprimir_ingresso.fxml");
        Scene imprimirScene = new Scene(imprimirLoader.getRoot(), 1189, 770);
        ImprimirIngressoController controllerImprimir = imprimirLoader.getController();
        stageAnterior.setScene(imprimirScene);
        controllerImprimir.setCpfBuscado(cpfDigitado);
        controllerImprimir.criarIngresso();
        stageAnterior.show();
    }

    public void telaInicialTrigger() throws IOException {
        Stage stageCompraFinalizada = (Stage) naoBotao.getScene().getWindow();
        stageCompraFinalizada.close();

        FXMLLoader telaInicialLoader = FXMLLoaderUtil.loadFXML("/hmd/teatroABC/tela_inicial.fxml");
        Scene telaInicialScene = new Scene(telaInicialLoader.getRoot(), 1189, 770);
        stageAnterior.setScene(telaInicialScene);
        stageAnterior.show();
    }

    public void setStageAnterior(Stage stageAnterior) {
        this.stageAnterior = stageAnterior;
    }

    public void setCpfDigitado(String cpfDigitado) {
        this.cpfDigitado = cpfDigitado;
    }
}
