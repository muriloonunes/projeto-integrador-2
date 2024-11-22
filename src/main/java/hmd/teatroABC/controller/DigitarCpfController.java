package hmd.teatroABC.controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Murilo Nunes <murilo_no@outlook.com>
 * @date 22/11/2024
 * @brief Class DigitarCpfController
 */
public class DigitarCpfController {
    public Label erroLabel;

    public TextField cpfField;

    public Button okBotao, cancelarBotao;

    public void initialize() {
        okBotao.setDisable(true);
        cpfField.textProperty().addListener((observable, oldValue, newValue) -> {
            okBotao.setDisable(newValue.trim().isEmpty());
        });
    }

    public String botaoClicado() {
        //TODO: implementar logica do retorno para botao ok e botao cancelar
        return null;
    }
}
