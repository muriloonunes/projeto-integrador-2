package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Pessoa;
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

    private boolean okClicado = false;

    public void initialize() {
        okBotao.setDisable(true);
        cpfField.textProperty().addListener((observable, oldValue, newValue) -> {
            okBotao.setDisable(newValue.trim().isEmpty());
        });
    }

    public void botaoOkClicado() {
        if (!verificarCpf(cpfField.getText())) {
            erroLabel.setVisible(true);
            erroLabel.setText("CPF inválido");
            return;
        }
        okClicado = true;
        Stage stage = (Stage) okBotao.getScene().getWindow();
        stage.close();
    }

    public void botaoCancelarClicado() {
        okClicado = false;
        Stage stage = (Stage) cancelarBotao.getScene().getWindow();
        stage.close();
    }

    public String pegarCpf() {
        if (okClicado) {
            return cpfField.getText();
        } else {
            return null;
        }
    }

    private boolean verificarCpf(String cpfString) {
        long cpf = Long.parseLong(cpfString);
        return Pessoa.validarCPF(cpf);
    }
}


