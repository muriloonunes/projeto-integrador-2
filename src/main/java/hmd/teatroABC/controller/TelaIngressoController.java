package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Peca;
import hmd.teatroABC.model.entities.Sessao;
import hmd.teatroABC.model.entities.Teatro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief Class TelaCompraController
 */
public class TelaIngressoController {
    @FXML
    private Button voltarBotao, continuarBotao;

    public GridPane plateiaAGrid, plateiaBGrid, camarote1Grid, camarote2Grid, camarote3Grid, camarote4Grid, camarote5Grid;

    public VBox frisa1Box, frisa2Box, frisa3Box, frisa4Box, frisa5Box;

    public ToggleButton A1;

    public void initialize() {
//        System.out.println(A1.getParent().getId());
//        System.out.println(GridPane.getColumnIndex(A1));
    }

    public static void configurarAssentos(String pecaSelecionada, Sessao sessaoSelecionada) {
        Optional<Peca> pecaEscolhida = Teatro.getPecas().stream().filter(peca -> peca.getNome().equals(pecaSelecionada) && peca.getSessao().equals(sessaoSelecionada)).findFirst();

        if (pecaEscolhida.isPresent()) {
            Peca peca = pecaEscolhida.get();
            System.out.println("Peça encontrada: " + peca.getNome());
            System.out.println("Sessão: " + peca.getSessao());
            System.out.println("Assentos Ocupados: " + String.join(", ", peca.getAssentos()));
        } else {
            System.out.println("Nenhuma peça encontrada para a seleção do usuário.");
        }
    }

    public void telaInicialTrigger() throws IOException {
        FXMLLoader telaInicialLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_inicial.fxml"));
        Scene telaInicialScene = new Scene(telaInicialLoader.load());
        Stage telaInicialStage = (Stage) voltarBotao.getScene().getWindow();
        telaInicialStage.setScene(telaInicialScene);
        telaInicialStage.show();
    }

    public void finalizarCompraTrigger() throws IOException {
        FXMLLoader finalizarCompraLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/finalizar_compra.fxml"));
        Scene finalizarCompraScene = new Scene(finalizarCompraLoader.load());
        Stage finalizarCompraStage = (Stage) continuarBotao.getScene().getWindow();
        finalizarCompraStage.setScene(finalizarCompraScene);
        finalizarCompraStage.show();
        //TODO: Implementar a lógica de finalização da compra
    }
}
