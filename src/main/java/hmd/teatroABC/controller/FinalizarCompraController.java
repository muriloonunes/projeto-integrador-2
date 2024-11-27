package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
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

    private final TelaIngressoController ingressoController = new TelaIngressoController();
    public Label plateiaAAssentosLabel, plateiaBAssentosLabel, frisaAssentosLabel, camaroteAssentosLabel, balcaoAssentosLabel;

    private ArrayList<String> assentosSelecionados;

    private final int maxLength = 2;

    public void initialize() {
        assentosSelecionados = (ArrayList<String>) ingressoController.getbotoesClicados();
        resumoDaCompra();
        vboxFidelidade.setVisible(false);

        List<String> estados = List.of("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
                "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        ObservableList<String> list = FXCollections.observableArrayList(estados);
        estadoBox.setItems(list);

        //https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
        //impede que caracteres não numéricos sejam inseridos no campo
        numeroField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numeroField.setText(newValue.replaceAll("\\D", ""));
            }
        });

        //https://stackoverflow.com/questions/15159988/javafx-2-2-textfield-maxlength
        //impede que caracteres acima de 2 casas sejam inseridos no campo
        numeroField.textProperty().addListener((ov, oldValue, newValue) -> {
            if (numeroField.getText().length() > maxLength) {
                String s = numeroField.getText().substring(0, maxLength);
                numeroField.setText(s);
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

    public void resumoDaCompra() {
        for (String lugares : assentosSelecionados) {
            char localidade = lugares.charAt(0);
            switch (localidade) {
                case 'A':
                    plateiaAAssentosLabel.setText(plateiaAAssentosLabel.getText() + lugares + " ");
                    break;
                case 'B':
                    plateiaBAssentosLabel.setText(plateiaBAssentosLabel.getText() + lugares + " ");
                    break;
                case 'F':
                    frisaAssentosLabel.setText(frisaAssentosLabel.getText() + lugares + " ");
                    break;
                case 'C':
                    camaroteAssentosLabel.setText(camaroteAssentosLabel.getText() + lugares + " ");
                    break;
                case 'N':
                    balcaoAssentosLabel.setText(balcaoAssentosLabel.getText() + lugares + " ");
                    break;
                default:
                    System.out.println("Não é um lugar");
            }
        }
    }

    private void validarCEP(String cep) {
        //https://blog.ramongomes.com.br/validacao-cep-javascript/#:~:text=retorna%20false.-,function%20validarCEP(cep)%20%7B,-//%20Remover%20espa%C3%A7os%20em
    }

    public void finalizarCompraTrigger() {
        //TODO
        boolean fid = fidelidadeSim.isSelected();

        //tem q tá tudo preenchido né zé
        //só comecin
        long verificar = Long.parseLong(String.valueOf(cpfField.getText()));
        if (Pessoa.validarCPF(verificar)) {
            System.out.println("Aí faz o resto");
        }


//        Pessoa pessoa = new Pessoa(cpf, fid, assentosSelecionados );


    }
}
