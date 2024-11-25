package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Peca;
import hmd.teatroABC.model.entities.Sessao;
import hmd.teatroABC.model.entities.Teatro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    public GridPane plateiaAGrid, plateiaBGrid, camarote1Grid, camarote2Grid, camarote3Grid, camarote4Grid, camarote5Grid;

    public VBox frisa1Box, frisa2Box, frisa3Box, frisa4Box, frisa5Box;

    public ToggleButton A1;


    private static List<ToggleButton> assentos = new ArrayList<>();
    private static List<Integer> assentosOcupadosIndices = new ArrayList<>();
    private static List<Integer> assentosSelecionadosIndices = new ArrayList<>();
    private double total = 0.0;
    private static String pecaSelecionada;
    private static Sessao sessaoSelecionada;

    public void initialize() {
        carregarAssentosDaTela();
        carregarAssentosOcupados();
        atualizarInterface();
        configurarEventosDeToggle();
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
        carregarAssentosOcupados();
        atualizarInterface();
    }


    private void carregarAssentosDaTela() {
        adicionarAssentosDoContainer(plateiaAGrid);
        adicionarAssentosDoContainer(plateiaBGrid);
        adicionarAssentosDoContainer(camarote1Grid);
        adicionarAssentosDoContainer(camarote2Grid);
        adicionarAssentosDoContainer(camarote3Grid);
        adicionarAssentosDoContainer(camarote4Grid);
        adicionarAssentosDoContainer(camarote5Grid);
        adicionarAssentosDoContainer(frisa1Box);
        adicionarAssentosDoContainer(frisa2Box);
        adicionarAssentosDoContainer(frisa3Box);
        adicionarAssentosDoContainer(frisa4Box);
        adicionarAssentosDoContainer(frisa5Box);
    }


    private void adicionarAssentosDoContainer(Node container) {
        if (container instanceof GridPane || container instanceof VBox) {
            for (Node node : ((javafx.scene.layout.Pane) container).getChildren()) {
                if (node instanceof ToggleButton) {
                    assentos.add((ToggleButton) node);
                }
            }
        }
    }


    private static void carregarAssentosOcupados() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/hmd/teatroABC/model/database/pecas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes[0].equals(pecaSelecionada) && partes[1].equals(sessaoSelecionada.name())) {
                    String[] assentosOcupadosStr = partes[3].split(";");
                    for (String assento : assentosOcupadosStr) {
                        int index = encontrarIndicePorNome(assento);
                        if (index != -1) {
                            assentosOcupadosIndices.add(index);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void atualizarInterface() {
        for (int i = 0; i < assentos.size(); i++) {
            ToggleButton assento = assentos.get(i);
            if (assentosOcupadosIndices.contains(i)) {
                assento.setDisable(true);
                assento.setSelected(true);
            }
        }
    }


    private void configurarEventosDeToggle() {
        for (int i = 0; i < assentos.size(); i++) {
            int index = i;
            ToggleButton assento = assentos.get(index);
            assento.setOnAction(event -> {
                if (assento.isSelected()) {
                    if (!assentosOcupadosIndices.contains(index)) {
                        assentosSelecionadosIndices.add(index);
                        total += obterPrecoDoAssento(index);
                    }
                } else {
                    assentosSelecionadosIndices.remove((Integer) index); // Remover seleção
                    total -= obterPrecoDoAssento(index);
                }
                atualizarTotalLabel();
            });
        }
    }


    private static int encontrarIndicePorNome(String nomeAssento) {
        for (int i = 0; i < assentos.size(); i++) {
            if (assentos.get(i).getText().equals(nomeAssento)) {
                return i;
            }
        }
        return -1;
    }


    private double obterPrecoDoAssento(int index) {
        String nomeAssento = assentos.get(index).getText();
        if (nomeAssento.startsWith("A")) return 40.00; // Plateia A
        if (nomeAssento.startsWith("B")) return 60.00; // Plateia B
        if (nomeAssento.startsWith("C")) return 80.00; // Camarotes
        if (nomeAssento.startsWith("F")) return 120.00; // Frisas
        return 250.00; // Padrão
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

