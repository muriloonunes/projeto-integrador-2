package hmd.teatroABC.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Murilo Nunes <murilo_no@outlook.com>
 * @date 19/11/2024
 * @brief Class TelaCompraController
 */
public class TelaCompraController {
    @FXML
    private Button botaoVoltar;

    public void voltarClicado() throws IOException {
        FXMLLoader telaInicialLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_inicial.fxml"));
        Scene telaInicialScene = new Scene(telaInicialLoader.load(), 1020, 700);
        Stage telaInicialStage = (Stage) botaoVoltar.getScene().getWindow();
        telaInicialStage.setScene(telaInicialScene);
        telaInicialStage.show();
    }
}
