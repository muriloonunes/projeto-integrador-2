package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Peca;
import hmd.teatroABC.model.entities.Teatro;
import hmd.teatroABC.model.objects.Estatistica;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 22/11/2024
 * @brief Class TelaEstatisticasController
 */

public class TelaEstatisticasController {
    public Label totalVendasLabel, pecaMaisVendidaLabel, pecaMenosVendidaLabel, sessaoMaisOcupadaLabel, sessaoMenosOcupadaLabel,
            lucroMedioLabel1, lucroMedioLabel2, lucroMedioLabel3, sessaoMais1Label, sessaoMenos1Label, sessaoMais2Label, sessaoMenos2Label, sessaoMais3Label, sessaoMenos3Label;

    public Button voltarBotao, botaoExportar;

    private Estatistica estatisticas = new Estatistica();

    public void initialize() {
        estatisticas.carregarEstatisticas();
        totalVendasLabel.setText(totalVendasLabel.getText() + " " + estatisticas.calcularTotalVendas());
        pecaMaisVendidaLabel.setText(pecaMaisVendidaLabel.getText() + estatisticas.calcularPecaMaisVendida());
        pecaMenosVendidaLabel.setText(pecaMenosVendidaLabel.getText() + estatisticas.calcularPecaMenosVendida());
        sessaoMaisOcupadaLabel.setText(sessaoMaisOcupadaLabel.getText() + estatisticas.calcularSessaoMaisOcupada());
        sessaoMenosOcupadaLabel.setText(sessaoMenosOcupadaLabel.getText() + estatisticas.calcularSessaoMenosOcupada());
        lucroMedioLabel1.setText(lucroMedioLabel1.getText() + estatisticas.getLucroMedioWicked());
        lucroMedioLabel2.setText(lucroMedioLabel2.getText() + estatisticas.getLucroMedioReiLeao());
        lucroMedioLabel3.setText(lucroMedioLabel3.getText() + estatisticas.getLucroMedioAuto());
//        System.out.println(estatisticas.calcularTotalVendas());
//        System.out.println(estatisticas.calcularPecaMaisVendida());
//        System.out.println(estatisticas.calcularPecaMenosVendida());
//        System.out.println(estatisticas.calcularSessaoMaisOcupada());
//        System.out.println(estatisticas.calcularSessaoMenosOcupada());
    }

    public void telaInicialTrigger() throws IOException {
        FXMLLoader telaInicialLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_inicial.fxml"));
        Scene telaInicialScene = new Scene(telaInicialLoader.load(), 1189, 770);
        Stage telaInicialStage = (Stage) voltarBotao.getScene().getWindow();
        telaInicialStage.setScene(telaInicialScene);
        telaInicialStage.show();
    }

    @FXML
    private void exportarCsv() {
        //TODO

        File estatisticaExportada = new File("src/main/resources/out/estatisticas.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(estatisticaExportada))) {
            bw.write("Estatística,Valor");
            bw.newLine();
            bw.write("Total de Vendas," + estatisticas.calcularTotalVendas());
            bw.newLine();
            bw.write("Peça Mais Vendida," + estatisticas.calcularPecaMaisVendida());
            bw.newLine();
            bw.write("Peça Menos Vendida," + estatisticas.calcularPecaMenosVendida());
            bw.newLine();
            bw.write("Sessão Mais Ocupada," + estatisticas.calcularSessaoMaisOcupada());
            bw.newLine();
            bw.write("Sessão Menos Ocupada," + estatisticas.calcularSessaoMenosOcupada());
            bw.newLine();
            bw.write("Lucro Médio (Wicked)," + estatisticas.getLucroMedioWicked());
            bw.newLine();
            bw.write("Lucro Médio (Rei Leão)," + estatisticas.getLucroMedioReiLeao());
            bw.newLine();
            bw.write("Lucro Médio (Auto da Compadecida)," + estatisticas.getLucroMedioAuto());
            bw.newLine();

            for (Peca peca : Teatro.getPecas()) {
                String maisLucrativa = estatisticas.calcularSessaoMaisLucrativaPorPeca(peca);
                String menosLucrativa = estatisticas.calcularSessaoMenosLucrativaPorPeca(peca);

                bw.write("Peça: " + peca.getNome() + " - Sessão Mais Lucrativa," + maisLucrativa);
                bw.newLine();
                bw.write("Peça: " + peca.getNome() + " - Sessão Menos Lucrativa," + menosLucrativa);
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
