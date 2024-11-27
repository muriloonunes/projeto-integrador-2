package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.Area;
import hmd.teatroABC.model.entities.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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
    public Button voltarBotao;

    private ArrayList<String> assentosSelecionados;

    private TelaIngressoController ingressoController = new TelaIngressoController();
    public Label plateiaAAssentosLabel, plateiaBAssentosLabel, frisaAssentosLabel, camaroteAssentosLabel, balcaoAssentosLabel,
            plateiaAValorLabel, plateiaBValorLabel, camaroteValorLabel, frisaValorLabel, balcaoValorLabel, totalLabel, valorTotalLabel,
            plateiaALabel, plateiaBLabel, camaroteLabel, frisaLabel, balcaoLabel;

    private final int maxLength = 2;


    public void initialize() {
        vboxFidelidade.setVisible(false);

        plateiaAAssentosLabel.setText("");
        plateiaBAssentosLabel.setText("");
        frisaAssentosLabel.setText("");
        camaroteAssentosLabel.setText("");
        camaroteAssentosLabel.setText("");
        balcaoAssentosLabel.setText("");
        plateiaAValorLabel.setText("");
        plateiaBValorLabel.setText("");
        camaroteValorLabel.setText("");
        frisaValorLabel.setText("");
        balcaoValorLabel.setText("");

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


    public void resumoDaCompra(ArrayList<String> assentosSelecionados) {
        this.assentosSelecionados = assentosSelecionados;

        double totalPlateiaA = 0.0;
        double totalPlateiaB = 0.0;
        double totalFrisa = 0.0;
        double totalCamarote = 0.0;
        double totalBalcao = 0.0;
        boolean temPlateiaB = false;
        boolean temPlateiaA = false;
        boolean temFrisa = false;
        boolean temCamarote = false;
        boolean temBalcao = false;

        for (String lugares : this.assentosSelecionados) {
            char localidade = lugares.charAt(0);
            switch (localidade) {
                case 'A':
                    plateiaAAssentosLabel.setText(plateiaAAssentosLabel.getText() + lugares + " ");
                    totalPlateiaA += Area.PLATEIA_A.getPreco();
                    plateiaAValorLabel.setText(String.format("%.2f", totalPlateiaA));
                    temPlateiaA = true;
                    break;
                case 'B':
                    plateiaBAssentosLabel.setText(plateiaBAssentosLabel.getText() + lugares + " ");
                    totalPlateiaB += Area.PLATEIA_B.getPreco();
                    plateiaBValorLabel.setText(String.format("%.2f", totalPlateiaB));
                    temPlateiaB = true;
                    break;
                case 'F':
                    frisaAssentosLabel.setText(frisaAssentosLabel.getText() + lugares + " ");
                    totalFrisa += Area.FRISA1.getPreco();
                    frisaValorLabel.setText(String.format("%.2f", totalFrisa));
                    temFrisa = true;
                    break;
                case 'C':
                    camaroteAssentosLabel.setText(camaroteAssentosLabel.getText() + lugares + " ");
                    totalCamarote += Area.CAMAROTE1.getPreco();
                    camaroteValorLabel.setText(String.format("%.2f", totalCamarote));
                    temCamarote = true;
                    break;
                case 'N':
                    balcaoAssentosLabel.setText(balcaoAssentosLabel.getText() + lugares + " ");
                    totalBalcao += Area.BALCAO_NOBRE.getPreco();
                    balcaoValorLabel.setText(String.format("%.2f", totalBalcao));
                    temBalcao = true;
                    break;
                default:
                    System.out.println("Lugar ou valor não encontrados");
            }
        }
        if (!temPlateiaA) {
            plateiaAAssentosLabel.setManaged(false);
            plateiaAValorLabel.setManaged(false);
            plateiaALabel.setManaged(false);
        }
        if (!temPlateiaB) {
            plateiaBAssentosLabel.setManaged(false);
            plateiaBValorLabel.setManaged(false);
            plateiaBLabel.setManaged(false);

        }
        if (!temFrisa) {
            frisaAssentosLabel.setManaged(false);
            frisaValorLabel.setManaged(false);
            frisaLabel.setManaged(false);
        }
        if (!temCamarote) {
            camaroteAssentosLabel.setManaged(false);
            camaroteValorLabel.setManaged(false);
            camaroteLabel.setManaged(false);
        }
        if (!temBalcao) {
            balcaoAssentosLabel.setManaged(false);
            balcaoValorLabel.setManaged(false);
            balcaoLabel.setManaged(false);
        }

        double total = totalPlateiaA + totalPlateiaB + totalFrisa + totalCamarote + totalBalcao;
        valorTotalLabel.setText(String.valueOf(total));

    }

    private boolean validarCEP(String cep) {
        //https://blog.ramongomes.com.br/validacao-cep-javascript/#:~:text=retorna%20false.-,function%20validarCEP(cep)%20%7B,-//%20Remover%20espa%C3%A7os%20em
        return true;
    }

    public void cadastroFinal() {
        long verificar = Long.parseLong(String.valueOf(cpfField.getText()));

        boolean naoFidelidade = fidelidadeNao.isSelected();
        boolean escolheuFidelidade = fidelidadeSim.isSelected();

        if (naoFidelidade && Pessoa.validarCPF(verificar)) {
            Pessoa pessoa = new Pessoa(verificar, false);
        } else if (escolheuFidelidade && Pessoa.validarCPF(verificar) && validarCEP(cepField.getText())) {
            Pessoa pessoa = new Pessoa(verificar, true, nomeField.getText(), telefoneField.getText(), ruaField.getText()
                    + " " + numeroField.getText() + " " + complementoField.getText() + " " + cepField.getText() + " " + bairroField.getText()
                    + " " + cidadeField.getText() + " " + estadoBox.getValue(), selecionarData.getValue());
        }

    }

    public void voltarTrigger() throws IOException {
        FXMLLoader compraSceneLoader = new FXMLLoader(getClass().getResource("/hmd/teatroABC/tela_ingressos.fxml"));
        Scene compraScene = new Scene(compraSceneLoader.load());
        TelaIngressoController controller = compraSceneLoader.getController();
        controller.chamarOutroMetodo();
        controller.configurarAposVoltar(assentosSelecionados);
        controller.configurarAssentos(ingressoController.getPecaSelecionada(), ingressoController.getSessaoSelecionada());
        Stage compraStage = (Stage) voltarBotao.getScene().getWindow();
        compraStage.setScene(compraScene);
        compraStage.show();
    }

    public void finalizarCompraTrigger() {
        //TODO

        cadastroFinal();
//      Pessoa pessoa = new Pessoa(cpf, fid, assentosSelecionados );

        //if(tudo clidado e preenchido, cpf correto){
        //  habilitar finalizar compra;
        //gerar ingresso e pessoa q tem tal ingresso;


    }

    public void setTelaIngressoController(TelaIngressoController controller) {
        this.ingressoController = controller;
    }
}
