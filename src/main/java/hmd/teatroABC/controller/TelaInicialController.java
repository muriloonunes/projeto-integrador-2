package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Sessao;
import hmd.teatroABC.model.entities.Teatro;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief class TelaInicialController
 */

public class TelaInicialController {
    public Button botaoManha, botaoTarde, botaoNoite, imprimirBotao, estatisticasBotao, temporaryButton;

    public HBox botoesBox, postersBox, rootBox;

    public ImageView imagem1, imagem2, imagem3;

    public ToggleButton peca1Botao, peca2Botao, peca3Botao;

    public void initialize() {
        botoesBox.setVisible(false);
        imagem1.setImage(Teatro.getPecas().get(0).getPosterImg());
        imagem2.setImage(Teatro.getPecas().get(3).getPosterImg());
        imagem3.setImage(Teatro.getPecas().get(6).getPosterImg());

        DoubleProperty stageWidth = new SimpleDoubleProperty();
        DoubleProperty stageHeight = new SimpleDoubleProperty();

        imagem1.fitWidthProperty().bind(Bindings.multiply(stageWidth, 0.5));
        imagem1.fitHeightProperty().bind(Bindings.multiply(stageHeight, 0.5));
        imagem1.setPreserveRatio(true);

        imagem2.fitWidthProperty().bind(Bindings.multiply(stageWidth, 0.5));
        imagem2.fitHeightProperty().bind(Bindings.multiply(stageHeight, 0.5));
        imagem2.setPreserveRatio(true);


        imagem3.fitWidthProperty().bind(Bindings.multiply(stageWidth, 0.5));
        imagem3.fitHeightProperty().bind(Bindings.multiply(stageHeight, 0.5));
        imagem3.setPreserveRatio(true);

        // Vincular a largura e altura do stage (Scene) à propriedade stageWidth e stageHeight
        stageWidth.bind(rootBox.widthProperty());
        stageHeight.bind(rootBox.heightProperty());
    }

    public void pecaSelecionada() {
        botoesBox.setVisible(true);
    }

    public void comprarIngressoTrigger(ActionEvent event) throws IOException {
        String pecaSelecionada;
        if (peca1Botao.isSelected()) {
            pecaSelecionada = "Wicked";
        } else if (peca2Botao.isSelected()) {
            pecaSelecionada = "Rei Leao";
        } else {
            pecaSelecionada = "Auto da Compadecida";
        }
        Sessao sessaoSelecionada;
        if (event.getSource() == botaoManha) {
            sessaoSelecionada = Sessao.MANHA;
        } else if (event.getSource() == botaoTarde) {
            sessaoSelecionada = Sessao.TARDE;
        } else {
            sessaoSelecionada = Sessao.NOITE;
        }

        FXMLLoader compraSceneLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_ingressos.fxml"));
        Scene compraScene = new Scene(compraSceneLoader.load());
        TelaIngressoController controller = compraSceneLoader.getController();
        Stage compraStage = (Stage) peca1Botao.getScene().getWindow();
        compraStage.setScene(compraScene);
        controller.chamarOutroMetodo();
        controller.configurarAssentos(pecaSelecionada, sessaoSelecionada);
        compraStage.show();
    }

    public void imprimirIngressoTrigger() throws IOException {
        FXMLLoader digitarCpf = new FXMLLoader(getClass().getResource("/hmd/teatroABC/digitar_cpf_tela.fxml"));
        Scene digitarCpfScene = new Scene(digitarCpf.load());
        DigitarCpfController controllerCpf = digitarCpf.getController();
        Stage digitarCpfStage = new Stage();
        digitarCpfStage.initOwner(imprimirBotao.getScene().getWindow());
        digitarCpfStage.initModality(Modality.WINDOW_MODAL);
        digitarCpfStage.setScene(digitarCpfScene);
        digitarCpfStage.showAndWait();

        String cpfDigitado = controllerCpf.pegarCpf();
        if (cpfDigitado != null) {
            abrirImprimir(cpfDigitado);
        }
    }

    public void verEstatisticasTrigger() throws IOException {
        FXMLLoader estatisticaLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/estatisticas_tela.fxml"));
        Scene estatisticasScene = new Scene(estatisticaLoader.load(), 1189, 810);
        Stage estatisticasTelaStage = (Stage) estatisticasBotao.getScene().getWindow();
        estatisticasTelaStage.setScene(estatisticasScene);
        estatisticasTelaStage.show();
    }

    private void abrirImprimir(String cpfDigitado) throws IOException {
        System.out.println(cpfDigitado);
        FXMLLoader imprimirLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/imprimir_ingresso.fxml"));
        Scene imprimirScene = new Scene(imprimirLoader.load(), 1189, 810);
        ImprimirIngressoController controllerImprimir = imprimirLoader.getController();
        Stage imprimirStage = (Stage) imprimirBotao.getScene().getWindow();
        imprimirStage.setScene(imprimirScene);
        controllerImprimir.setCpfBuscado(cpfDigitado);
        controllerImprimir.criarIngresso();
        imprimirStage.show();
    }

    public void tempFinalizarTrigger() throws IOException {
        FXMLLoader finalizarCompraLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/finalizar_compra.fxml"));
        Scene finalizarCompraScene = new Scene(finalizarCompraLoader.load());
        Stage finalizarCompraStage = (Stage) temporaryButton.getScene().getWindow();
        finalizarCompraStage.setScene(finalizarCompraScene);
        finalizarCompraStage.show();
        //TODO APAGAR ISSO E DELETAR O BOTAO PELO AMOR DE JESUS CRISTO
    }

}