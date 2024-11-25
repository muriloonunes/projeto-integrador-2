package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Sessao;
import hmd.teatroABC.model.entities.Teatro;
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
    public Button botaoManha, botaoTarde, botaoNoite, imprimirBotao, estatisticasBotao;

    public HBox botoesBox, postersBox;

    public ImageView imagem1, imagem2, imagem3;

    public ToggleButton peca1Botao, peca2Botao, peca3Botao;

    Sessao sessaoSelecionada;

    public void initialize() {
        botoesBox.setVisible(false);
        imagem1.setImage(Teatro.getPecas().get(0).getPosterImg());
        imagem2.setImage(Teatro.getPecas().get(3).getPosterImg());
        imagem3.setImage(Teatro.getPecas().get(6).getPosterImg());
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
        controller.inicializarComDados(pecaSelecionada, sessaoSelecionada);


        Stage compraStage = (Stage) peca1Botao.getScene().getWindow();
        compraStage.setScene(compraScene);
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
            abrirImprimir();
        }
    }

    public void verEstatisticasTrigger() throws IOException {
        FXMLLoader estatisticaLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/estatisticas_tela.fxml"));
        Scene estatisticasScene = new Scene(estatisticaLoader.load(), estatisticasBotao.getScene().getWidth(), estatisticasBotao.getScene().getHeight());
        Stage estatisticasTelaStage = (Stage) estatisticasBotao.getScene().getWindow();
        estatisticasTelaStage.setScene(estatisticasScene);
        estatisticasTelaStage.show();
    }

    private void abrirImprimir() {
        //todo
    }

}