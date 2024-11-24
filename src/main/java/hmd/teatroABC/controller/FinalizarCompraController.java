package hmd.teatroABC.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 24/11/2024
 * @brief Class FinalizarCompraController
 */
public class FinalizarCompraController {
    public VBox vboxFidelidade;
    public DatePicker selecionarData;
    public TextField cpfField, nomeField, telefoneField, ruaField, numeroField, complementoField, cepField,
            bairroField, cidadeField;
    public ChoiceBox<String> estadoBox;
    public Button finalizarBotao;
    public ToggleGroup pagamento, querFidelidade;
    public RadioButton fidelidadeSim, fidelidadeNao;

    private final int maxLength = 2;

    public void initialize() {
        vboxFidelidade.setVisible(false);

        List<String> estados = List.of("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
                "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        ObservableList<String> list = FXCollections.observableArrayList(estados);
        estadoBox.setItems(list);

        //https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
        //impede que caracteres não numéricos sejam inseridos no campo
        numeroField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numeroField.setText(newValue.replaceAll("\\D", ""));
                }
            }
        });

        //https://stackoverflow.com/questions/15159988/javafx-2-2-textfield-maxlength
        //impede que caracteres acima de 2 casas sejam inseridos no campo
        numeroField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (numeroField.getText().length() > maxLength) {
                    String s = numeroField.getText().substring(0, maxLength);
                    numeroField.setText(s);
                }
            }
        });

/*        cepField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.replaceAll("\\D", "");
            if (newValue.length() > 5) {
                newValue = newValue.substring(0, 5) + "-" + newValue.substring(5, Math.min(newValue.length(), 8));
            }
            cepField.setText(newValue);
        });*/
    }

    public void mostrarFidelidade() {
        vboxFidelidade.setVisible(true);
    }

    public void ocultarFidelidade() {
        if (vboxFidelidade.isVisible()) {
            vboxFidelidade.setVisible(false);
        }
    }

    private void validarCEP(String cep) {
        //https://blog.ramongomes.com.br/validacao-cep-javascript/#:~:text=retorna%20false.-,function%20validarCEP(cep)%20%7B,-//%20Remover%20espa%C3%A7os%20em
    }

    public void finalizarCompraTrigger() {
        //TODO
    }
}
