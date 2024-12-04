package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Ingresso;
import hmd.teatroABC.model.entities.Pessoa;
import hmd.teatroABC.model.entities.Teatro;
import hmd.teatroABC.util.FXMLLoaderUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
            exibindoLabel.setStyle("-fx-text-fill: red");
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
                    Label precoLabel = new Label("Preço: R$" + ingresso.getPreco());
                    Button exportarBotao = new Button("Exportar ingresso");
                    exportarBotao.setOnAction(_ -> exportarCsv(ingresso));

                    ingressoContainer.getChildren().addAll(cpfLabel, pecaLabel, sessaoLabel, assentoLabel,precoLabel, exportarBotao);
                    vboxContainer.getChildren().add(ingressoContainer);
                }
            }
        }
    }

    public void voltarTrigger() throws IOException {
        FXMLLoader telaInicialLoader = FXMLLoaderUtil.loadFXML("/hmd/teatroABC/tela_inicial.fxml");
        Scene telaInicialScene = new Scene(telaInicialLoader.getRoot(), 1189, 770);
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
            bw.newLine();
            bw.write("Preço: R$" + ingresso.getPreco());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Ingresso exportado com sucesso!");

        Scene cenaAlerta = alert.getDialogPane().getScene();
        cenaAlerta.getRoot().setStyle("-fx-background-color: #262424;");
        Label contentLabel = (Label) alert.getDialogPane().lookup(".content");
        if (contentLabel != null) {
            contentLabel.setStyle("-fx-text-fill: white;");
        }

        alert.showAndWait();
    }

    public void setCpfBuscado(String cpfBuscado) {
        this.cpfBuscado = cpfBuscado;
        exibindoLabel.setText(exibindoLabel.getText() + " " + cpfBuscado);
    }
}
