package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Area;
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

    public GridPane plateiaAGrid;
    public GridPane plateiaBGrid;
    public GridPane camarote1Grid;
    public GridPane camarote2Grid;
    public GridPane camarote3Grid;
    public GridPane camarote4Grid;
    public GridPane camarote5Grid;
    public GridPane balcaoGrid;

    public VBox frisa1Box;
    public VBox frisa2Box;
    public VBox frisa3Box;
    public VBox frisa4Box;
    public VBox frisa5Box;
    public VBox frisa6Box;

    public ToggleButton A1;


    private static final List<String> plateiaAAssentos = new ArrayList<>();
    private static final List<String> plateiaBAssentos = new ArrayList<>();
    private static final List<String> camaroteAssentos = new ArrayList<>();
    private static final List<String> frisaAssentos = new ArrayList<>();
    private static final List<String> balcaoAssentos = new ArrayList<>();
    private static final List<ToggleButton> botoesExistentes = new ArrayList<>();
    private double total = 0.0;

    public void initialize() {
        continuarBotao.setDisable(true);
    }

    protected void chamarOutroMetodo() {
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
                botoesExistentes.add(button);
                button.setOnAction(event -> {
                    habilitarBotaoConfirmar();
                    registrarAssentosEscolhidos();
                });
            }
        }
    }

    public void configurarAssentos(String pecaSelecionada, Sessao sessaoSelecionada) {
        Optional<Peca> pecaEscolhida = Teatro.getPecas().stream().filter(peca -> peca.getNome().equals(pecaSelecionada) && peca.getSessao().equals(sessaoSelecionada)).findFirst();
        List<String> lugaresIndisponiveis;

        if (pecaEscolhida.isPresent()) {
            Peca peca = pecaEscolhida.get();
            System.out.println("Peça encontrada: " + peca.getNome());
            System.out.println("Sessão: " + peca.getSessao());
            System.out.println("Assentos Ocupados: " + String.join(", ", peca.getAssentos()));
            lugaresIndisponiveis = peca.getAssentos();

            for (String lugar : lugaresIndisponiveis) {
                if (!lugar.equals(" ")) {
                    char localidade = lugar.charAt(0);
                    switch (localidade) {
                        case 'A':
                            desativarAssentos(plateiaAGrid, lugar);
                            break;
                        case 'B':
                            desativarAssentos(plateiaBGrid, lugar);
                            break;
                        case 'F':
                            desativarAssentos(frisa1Box, lugar);
                            desativarAssentos(frisa2Box, lugar);
                            desativarAssentos(frisa3Box, lugar);
                            desativarAssentos(frisa4Box, lugar);
                            desativarAssentos(frisa5Box, lugar);
                            desativarAssentos(frisa6Box, lugar);
                            break;
                        case 'C':
                            desativarAssentos(camarote1Grid, lugar);
                            desativarAssentos(camarote2Grid, lugar);
                            desativarAssentos(camarote3Grid, lugar);
                            desativarAssentos(camarote4Grid, lugar);
                            desativarAssentos(camarote5Grid, lugar);
                            break;
                        case 'N':
                            desativarAssentos(balcaoGrid, lugar);
                            break;
                        default:
                            System.out.println("Lugar inexistente");
                    }
                }
            }
        } else {
            System.out.println("Nenhuma peça encontrada para a seleção do usuário.");
        }
    }

    private static void desativarAssentos(Parent container, String lugar) {
        for (Node node : container.getChildrenUnmodifiable()) {
            if (node instanceof ToggleButton button) {
                if (button.getId().equals(lugar)) {
                    button.setDisable(true);
                    break;
                }
            }
        }
    }

    private void registrarAssentosEscolhidos() {
        List<String> botoesClicados = new ArrayList<>();
        for (ToggleButton toggleButton : botoesExistentes) {
            if (toggleButton.isSelected()){
                botoesClicados.add(toggleButton.getId());
                atualizarTotalLabel(toggleButton.getId());
            }
        }
    }

    private void atualizarTotalLabel(String id) {
        char identificadorPreco = id.charAt(0);
        switch (identificadorPreco){
            case 'A':
                total += Area.PLATEIA_A.getPreco();
                break;
            case 'B':
                total += Area.PLATEIA_B.getPreco();
                break;
            case 'F':
                //só o preço msm
                total += Area.FRISA1.getPreco();
                break;
            case 'C':
                total += Area.CAMAROTE1.getPreco();
                break;
            case 'N':
                total += Area.BALCAO_NOBRE.getPreco();
                break;
            default:
                System.out.println("Lugar inexistente, preço não encontrado");
        }
    }

    private void habilitarBotaoConfirmar() {
        for (ToggleButton botao : botoesExistentes) {
            if (botao.isSelected()) {
                continuarBotao.setDisable(false);
                return;
            } else {
                continuarBotao.setDisable(true);
            }
        }
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
    }
}


