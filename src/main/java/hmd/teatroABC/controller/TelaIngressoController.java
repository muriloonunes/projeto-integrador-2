package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Sessao;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief Class TelaCompraController
 */
public class TelaIngressoController {
    @FXML
    private Button voltarBotao, continuarBotao;

    public GridPane plateiaAGrid, plateiaBGrid, camarote1Grid, camarote2Grid, camarote3Grid, camarote4Grid, camarote5Grid;

    public VBox frisa1Box, frisa2Box, frisa3Box, frisa4Box, frisa5Box, frisa6Box;

    public ToggleButton A1;

    private static List<ToggleButton> assentos = new ArrayList<>();
    private static List<String> assentosSelecionadosIndices  = new ArrayList<>();
    private static final List<String> assentosOcupadosIndices  = new ArrayList<>();
    private static String pecaSelecionada;
    private static Sessao sessaoSelecionada;
    private double total = 0.0;

    public void initialize() {
        carregarAssentosDaTela();
        carregarAssentosOcupados();
        atualizarInterface();
        configurarEventosDeToggle();
    }
    public void inicializarComDados(String pecaSelecionada, Sessao sessaoSelecionada) {
        TelaIngressoController.pecaSelecionada = pecaSelecionada;
        TelaIngressoController.sessaoSelecionada = sessaoSelecionada;


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


    private void carregarAssentosOcupados() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/hmd/teatroABC/model/database/pecas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes[0].equals(pecaSelecionada) && partes[1].equals(sessaoSelecionada.name())) {

                    String[] assentosOcupadosStr = partes[3].split(";");
                    for (String assento : assentosOcupadosStr) {
                        int index = encontrarIndicePorNome(assento);
                        if (index != -1) {
                            assentosOcupadosIndices.add(String.valueOf(index));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void atualizarInterface() {
        for (int i = 0; i < assentos.size(); i++) {
            ToggleButton assento = assentos.get(i);
            if (assentosOcupadosIndices.contains(i)) {
                assento.setDisable(true); // Ocupado
                assento.setSelected(true); // Visualmente selecionado
            }
        }
    }

    // Configurar eventos para selecionar/deselecionar assentos
    private void configurarEventosDeToggle() {
        for (int i = 0; i < assentos.size(); i++) {
            int index = i;
            ToggleButton assento = assentos.get(index);
            assento.setOnAction(event -> {
                if (assento.isSelected()) {
                    if (!assentosOcupadosIndices.contains(index)) {
                        assentosSelecionadosIndices.add(String.valueOf(index));
                        total += obterPrecoDoAssento(index);
                    }
                } else {
                    assentosSelecionadosIndices.remove((Integer) index);
                    total -= obterPrecoDoAssento(index);
                }
                atualizarTotalLabel();
            });
        }
    }

    // Encontrar índice de um assento pelo nome (ex: "A1")
    private int encontrarIndicePorNome(String nomeAssento) {
        for (int i = 0; i < assentos.size(); i++) {
            if (assentos.get(i).getText().equals(nomeAssento)) {
                return i;
            }
        }
        return -1;
    }

    // Retornar preço do assento pelo índice
    private double obterPrecoDoAssento(int index) {
        String nomeAssento = assentos.get(index).getText();
        if (nomeAssento.startsWith("A")) return 40.00;
        if (nomeAssento.startsWith("B")) return 60.00;
        if (nomeAssento.startsWith("C")) return 80.00;
        if (nomeAssento.startsWith("F")) return 120.00;
        return 250.00;
    }

    // Atualizar total em um rótulo (se disponível na interface)
    private void atualizarTotalLabel() {
        // totalLabel.setText(String.format("Total: R$ %.2f", total)); // Descomente se houver um Label na interface
    }

}

//guardadinha dos crias
//public static void configurarAssentos(String pecaSelecionada, Sessao sessaoSelecionada) {
//        Optional<Peca> pecaEscolhida = Teatro.getPecas().stream().filter(peca -> peca.getNome().equals(pecaSelecionada) && peca.getSessao().equals(sessaoSelecionada)).findFirst();
//
//        if (pecaEscolhida.isPresent()) {
//            Peca peca = pecaEscolhida.get();
//            System.out.println("Peça encontrada: " + peca.getNome());
//            System.out.println("Sessão: " + peca.getSessao());
//            System.out.println("Assentos Ocupados: " + String.join(", ", peca.getAssentos()));
//        } else {
//            System.out.println("Nenhuma peça encontrada para a seleção do usuário.");
//        }
//        carregarAssentosOcupados();
//        atualizarInterface();
//    }