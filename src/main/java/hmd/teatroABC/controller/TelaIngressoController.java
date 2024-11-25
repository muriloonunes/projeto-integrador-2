package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Peca;
import hmd.teatroABC.model.entities.Sessao;
import hmd.teatroABC.model.entities.Teatro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief Class TelaCompraController
 */
public class TelaIngressoController {
    @FXML
    private Button voltarBotao, continuarBotao;

    public GridPane plateiaAGrid, plateiaBGrid, camarote1Grid, camarote2Grid, camarote3Grid, camarote4Grid, camarote5Grid, balcaoGrid;

    public VBox frisa1Box, frisa2Box, frisa3Box, frisa4Box, frisa5Box, frisa6Box;

    public ToggleButton A1;

    private static List<ToggleButton> assentos = new ArrayList<>();
    private static List<String> plateiaAAssentos = new ArrayList<>();
    private static List<String> plateiaBAssentos = new ArrayList<>();
    private static List<String> camaroteAssentos = new ArrayList<>();
    private static List<String> frisaAssentos = new ArrayList<>();
    private static List<String> balcaoAssentos = new ArrayList<>();
    private double total = 0.0;

    public void initialize() {
        atualizarInterface();
        configurarEventosDeToggle();

        registrarAssentos(plateiaAGrid, plateiaAAssentos);
        registrarAssentos(plateiaBGrid, plateiaBAssentos);
        registrarAssentos(camarote1Grid, camaroteAssentos);
        registrarAssentos(camarote2Grid, camaroteAssentos);
        registrarAssentos(camarote3Grid, camaroteAssentos);
        registrarAssentos(camarote4Grid, camaroteAssentos);
        registrarAssentos(camarote5Grid, camaroteAssentos);
        registrarAssentos(balcaoGrid, balcaoAssentos);
        registrarAssentos(frisa1Box, frisaAssentos);
        registrarAssentos(frisa2Box, frisaAssentos);
        registrarAssentos(frisa3Box, frisaAssentos);
        registrarAssentos(frisa4Box, frisaAssentos);
        registrarAssentos(frisa5Box, frisaAssentos);
        registrarAssentos(frisa6Box, frisaAssentos);
    }

    private void registrarAssentos(Parent container, List<String> listaAssentos) {
        for (Node node : container.getChildrenUnmodifiable()) {
            if (node instanceof ToggleButton button) {
                listaAssentos.add(button.getId());
            }
        }
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
        atualizarInterface();
    }

    private static void atualizarInterface() {

    }

    private void configurarEventosDeToggle() {
//        for (int i = 0; i < assentos.size(); i++) {
//            int index = i;
//            ToggleButton assento = assentos.get(index);
//            assento.setOnAction(event -> {
//                if (assento.isSelected()) {
//                    if (!assentosOcupadosIndices.contains(index)) {
//                        assentosSelecionadosIndices.add(index);
//                        total += obterPrecoDoAssento(index);
//                    }
//                } else {
//                    assentosSelecionadosIndices.remove((Integer) index); // Remover seleção
//                    total -= obterPrecoDoAssento(index);
//                }
//                atualizarTotalLabel();
//            });
//        }
    }

    private void atualizarTotalLabel() {
        // totalLabel.setText(String.format("Total: R$ %.2f", total));
    }


    public void telaInicialTrigger() throws IOException {
        FXMLLoader telaInicialLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_inicial.fxml"));
        Scene telaInicialScene = new Scene(telaInicialLoader.load(), 1189, 810);
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

