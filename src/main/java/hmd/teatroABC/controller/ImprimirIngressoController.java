package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Ingresso;
import hmd.teatroABC.model.entities.Pessoa;
import hmd.teatroABC.model.entities.Teatro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
        List<Pessoa> pessoas = Teatro.buscarPessoaPorCpf(cpfBuscado);
        if (pessoas.isEmpty()) {
            exibindoLabel.setText("Nenhum ingresso encontrado para o CPF " + cpfBuscado);
        } else {
            for (Pessoa pessoa : pessoas) {
                for (Ingresso ingresso : pessoa.getIngressos()) {
                    VBox ingressoContainer = new VBox(5);
                    ingressoContainer.getStyleClass().clear();
                    ingressoContainer.getStyleClass().add("vbox-ingresso");
                    Label cpfLabel = new Label("CPF do titular: " + cpfBuscado);
                    Label pecaLabel = new Label("Peca: " + ingresso.getPeca().getNome());
                    Label sessaoLabel = new Label("Sessão: " + ingresso.getPeca().getSessao());
                    Label assentoLabel = new Label("Assento: " + ingresso.getAssento());
                    Button exportarBotao = new Button("Exportar ingresso");
                    exportarBotao.setOnAction(event -> exportarCsv(ingresso));

                    ingressoContainer.getChildren().addAll(cpfLabel, pecaLabel, sessaoLabel, assentoLabel, exportarBotao);
                    vboxContainer.getChildren().add(ingressoContainer);
                }
            }
        }
    }

    public void voltarTrigger() throws IOException {
        FXMLLoader telaInicialLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_inicial.fxml"));
        Scene telaInicialScene = new Scene(telaInicialLoader.load(), 1189, 810);
        Stage telaInicialStage = (Stage) voltarBotao.getScene().getWindow();
        telaInicialStage.setScene(telaInicialScene);
        telaInicialStage.show();
    }

    private void exportarCsv(Ingresso ingresso) {
        File ingressoExportado = new File("src/main/resources/out/ingresso.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ingressoExportado))) {
            bw.write("INGRESSO");
            bw.newLine();
            bw.write("CPF do titular: " + cpfBuscado);
            bw.newLine();
            bw.write("Peca: " + ingresso.getPeca().getNome());
            bw.newLine();
            bw.write("Sessão: " + ingresso.getPeca().getSessao());
            bw.newLine();
            bw.write("Assento: " + ingresso.getAssento());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCpfBuscado() {
        return cpfBuscado;
    }

    public void setCpfBuscado(String cpfBuscado) {
        this.cpfBuscado = cpfBuscado;
        exibindoLabel.setText(exibindoLabel.getText() + " " + cpfBuscado);
    }
}
