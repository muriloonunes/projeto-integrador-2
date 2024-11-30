package hmd.teatroABC.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    }
}
