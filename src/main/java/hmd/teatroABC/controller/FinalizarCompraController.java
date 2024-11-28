package hmd.teatroABC.controller;

import hmd.teatroABC.model.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hmd.teatroABC.model.entities.Teatro.log;

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
            plateiaALabel, plateiaBLabel, camaroteLabel, frisaLabel, balcaoLabel, erroLabel;

    private boolean cpfValido = false;

    public void initialize() {
        vboxFidelidade.setVisible(false);
        finalizarBotao.setDisable(true);

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

        cpfField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cpfField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            if (cpfField.getText().length() > 11) {
                cpfField.setText(cpfField.getText().substring(0, 11));
            }

            if (cpfField.getText().length() == 11) {
                cpfValido = Pessoa.validarCPF(Long.parseLong(cpfField.getText()));
                if (!cpfValido) {
                    erroLabel.setVisible(true);
                    erroLabel.setText("CPF inválido");
                } else {
                    erroLabel.setText("");
                    erroLabel.setVisible(false);
                }
            }
            desabilitarFinalizarCompra();
        });

        cepField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cepField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        telefoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                telefoneField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        /*
           cepField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.replaceAll("\\D", "");
            if (newValue.length() > 5) {
                newValue = newValue.substring(0, 5) + "-" + newValue.substring(5, Math.min(newValue.length(), 8));
            }
            cepField.setText(newValue);
        });
        */
        criarListeners();
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


    }

    public void cadastroFinal() {
        long verificar = Long.parseLong(String.valueOf(cpfField.getText()));

        boolean naoFidelidade = fidelidadeNao.isSelected();
        boolean escolheuFidelidade = fidelidadeSim.isSelected();

        if (naoFidelidade && Pessoa.validarCPF(verificar)) {
            Pessoa pessoa = new Pessoa(verificar, false);
            criarIngresso(pessoa);
        } else if (escolheuFidelidade && Pessoa.validarCPF(verificar) && validarCEP(cepField.getText())) {
            Pessoa pessoa = new Pessoa(verificar, true, nomeField.getText(), telefoneField.getText(), ruaField.getText()
                    + " " + numeroField.getText() + " " + complementoField.getText() + " " + cepField.getText() + " " + bairroField.getText()
                    + " " + cidadeField.getText() + " " + estadoBox.getValue(), selecionarData.getValue());
            criarIngresso(pessoa);
        }
    }

    private void criarIngresso(Pessoa pessoa) {
        for (String assento : assentosSelecionados) {
            char identificador = assento.charAt(0);
            int segundoNumero = assento.charAt(1);
            Ingresso ing = new Ingresso(getAreaPorIdentificador(identificador, segundoNumero), ingressoController.encontrarPeca(), assento);
            ingressoController.encontrarPeca().adicionarAssento(assento);
            //TODO: adicionar forma de escrever esse assento no arquivo "pecas.txt"



            //TODO: adicionar forma de registrar essa pessoa criada, bem como o ingresso, no arquivo pessoas.txt

            Teatro.log.add(LocalDateTime.now() + " -> " + pessoa.getNome() + " " + pessoa.getCpf() +  " " +
                    pessoa.isEhFidelidade() + " - " + ing.getAssento() + " " + ing.getArea() + " " + ing.getPeca());

            pessoa.adicionarIngresso(ing);
        }
    }

    public void setTelaIngressoController(TelaIngressoController controller) {
        this.ingressoController = controller;
    }

    public static List<String> getLog() {
        return log;
    }

    private void criarListeners() {
        List<TextField> camposTexto = Arrays.asList(nomeField, telefoneField, ruaField, numeroField, complementoField,
                cepField, bairroField, cidadeField);

        // Adicionando o listener a todos os campos de texto
        camposTexto.forEach(campo -> campo.textProperty().addListener((observable, oldValue, newValue) -> {
            desabilitarFinalizarCompra();
        }));

        // Adicionando o listener para os controles de seleção
        List<ToggleGroup> toggleGroups = Arrays.asList(querFidelidade, pagamento); // Adicione outros ToggleGroups se necessário

        // Adicionando o listener aos ToggleGroups
        toggleGroups.forEach(toggleGroup -> toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            desabilitarFinalizarCompra();
        }));

        selecionarData.valueProperty().addListener((observable, oldValue, newValue) -> {
            desabilitarFinalizarCompra();
        });


    }

    private void desabilitarFinalizarCompra() {
        boolean cpfPreenchido = cpfField.getText().length() == 11;
        boolean pagamentoSelecionado = pagamento.getSelectedToggle() != null;

        if (cpfPreenchido && fidelidadeNao.isSelected()) {
            finalizarBotao.setDisable(!pagamentoSelecionado || !cpfValido);
        } else if (cpfPreenchido && fidelidadeSim.isSelected()) {
            boolean camposObrigatoriosPreenchidos = !nomeField.getText().isEmpty() &&
                    !telefoneField.getText().isEmpty() &&
                    !ruaField.getText().isEmpty() &&
                    !numeroField.getText().isEmpty() &&
                    !complementoField.getText().isEmpty() &&
                    !cepField.getText().isEmpty() &&
                    !bairroField.getText().isEmpty() &&
                    !cidadeField.getText().isEmpty() &&
                    estadoBox.getValue() != null &&
                    selecionarData.getValue() != null;
            finalizarBotao.setDisable(!camposObrigatoriosPreenchidos || !pagamentoSelecionado || !cpfValido);
        } else {
            finalizarBotao.setDisable(true);
        }
    }

    private Area getAreaPorIdentificador(char identificador, int segundoNumero) {
        Area area = null;
        switch (identificador) {
            case 'A' -> area = Area.PLATEIA_A;
            case 'B' -> area = Area.PLATEIA_B;
            case 'F' -> {
                if (segundoNumero >= 1 && segundoNumero <= 6) {
                    area = switch (segundoNumero) {
                        case 1 -> Area.FRISA1;
                        case 2 -> Area.FRISA2;
                        case 3 -> Area.FRISA3;
                        case 4 -> Area.FRISA4;
                        case 5 -> Area.FRISA5;
                        case 6 -> Area.FRISA6;
                        default -> null;
                    };
                }
            }
            case 'C' -> {
                if (segundoNumero >= 1 && segundoNumero <= 5) {
                    area = switch (segundoNumero) {
                        case 1 -> Area.CAMAROTE1;
                        case 2 -> Area.CAMAROTE2;
                        case 3 -> Area.CAMAROTE3;
                        case 4 -> Area.CAMAROTE4;
                        case 5 -> Area.CAMAROTE5;
                        default -> null;
                    };
                }
            }
            case 'N' -> area = Area.BALCAO_NOBRE;
            default -> throw new IllegalStateException("Unexpected value: " + identificador);
        }
        ;
        return area;
    }

}
