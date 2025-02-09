package com.padaria.controller;

import com.padaria.model.dao.DaoFactory;
import com.padaria.model.entities.Venda;
import com.padaria.model.entities.VendaItem;
import com.padaria.model.entities.VendaProdutos;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class VendaController {

    @FXML private TreeTableView<VendaItem> tabelaVendas;
    @FXML private TreeTableColumn<VendaItem, Number> colunaId, colunaTotal, colunaIdProduto, colunaPrecoProduto, colunaQtd, colunaSubtotal;
    @FXML private TreeTableColumn<VendaItem, String> colunaNomeProduto, colunaCategoriaProduto;
    @FXML private TreeTableColumn<VendaItem, LocalDateTime> colunaData;
    @FXML private RadioButton radioButtonDia, radioButtonMes, radioButtonPeriodo;
    @FXML private DatePicker datePickerInicio, datePickerFinal;
    @FXML private Label labelVendasTotais, labelValorMedio;

    @FXML
    public void initialize() {
        radioButtonMes.setSelected(true);
        configurarColunas();
        atualizarVendas();
    }

    private void atualizarVendas() {
        List<Venda> vendas = DaoFactory.createVendaDao().listaVendas();
        List<Venda> vendasFiltradas = filtrarVendas(vendas);
        atualizarEstatisticas(vendasFiltradas);
        carregarVendas(vendasFiltradas);
    }

    private List<Venda> filtrarVendas(List<Venda> vendas) {
        LocalDate hoje = LocalDate.now();

        if (radioButtonDia.isSelected()) {
            return vendas.stream().filter(v -> v.getData().toLocalDate().isEqual(hoje)).collect(Collectors.toList());
        }
        if (radioButtonMes.isSelected()) {
            return vendas.stream().filter(v -> !v.getData().toLocalDate().isBefore(hoje.minusMonths(1))).collect(Collectors.toList());
        }
        if (radioButtonPeriodo.isSelected() && datePickerInicio.getValue() != null && datePickerFinal.getValue() != null) {
            return vendas.stream().filter(v -> !v.getData().toLocalDate().isBefore(datePickerInicio.getValue()) && !v.getData().toLocalDate().isAfter(datePickerFinal.getValue())).collect(Collectors.toList());
        }
        return vendas;
    }

    private void atualizarEstatisticas(List<Venda> vendas) {
        double total = vendas.stream().mapToDouble(Venda::getValorTotal).sum();
        double media = vendas.isEmpty() ? 0.0 : total / vendas.size();

        labelVendasTotais.setText(String.format("R$ %.2f", total));
        labelValorMedio.setText(String.format("R$ %.2f", media));
    }

    @FXML
    private void handleRadioButton() {
        boolean periodoSelecionado = radioButtonPeriodo.isSelected();
        datePickerInicio.setDisable(!periodoSelecionado);
        datePickerFinal.setDisable(!periodoSelecionado);
        if (!periodoSelecionado) {
            atualizarVendas();
        }
    }

    @FXML
    private void handleButtonAplicar() {
        if (radioButtonPeriodo.isSelected() && datePickerInicio.getValue() != null && datePickerFinal.getValue() != null) {
            atualizarVendas();
        }
    }

    private void carregarVendas(List<Venda> vendas) {
        TreeItem<VendaItem> root = new TreeItem<>(new VendaItem(0, LocalDateTime.now(), 0.0));
        tabelaVendas.setRoot(root);
        tabelaVendas.setShowRoot(false);

        for (Venda venda : vendas) {
            TreeItem<VendaItem> vendaItem = new TreeItem<>(new VendaItem(venda.getId(), venda.getData(), venda.getValorTotal()));

            for (VendaProdutos vendaProdutos : venda.getItens()) {
                vendaItem.getChildren().add(new TreeItem<>(new VendaItem(
                        vendaProdutos.getIdProduto(), vendaProdutos.getProduto().getNome(),
                        vendaProdutos.getProduto().getCategoria(), vendaProdutos.getProduto().getPreco(),
                        vendaProdutos.getQuantidade(), vendaProdutos.getSubTotal()
                )));
            }
            root.getChildren().add(vendaItem);
        }
    }

    private void configurarColunas() {
        colunaId.setCellValueFactory(param -> param.getValue().getValue().id);
        colunaTotal.setCellValueFactory(param -> param.getValue().getValue().total);
        colunaIdProduto.setCellValueFactory(param -> param.getValue().getValue().idProduto);
        colunaNomeProduto.setCellValueFactory(param -> param.getValue().getValue().nomeProduto);
        colunaCategoriaProduto.setCellValueFactory(param -> param.getValue().getValue().categoriaProduto);
        colunaPrecoProduto.setCellValueFactory(param -> param.getValue().getValue().precoProduto);
        colunaQtd.setCellValueFactory(param -> param.getValue().getValue().qtd);
        colunaSubtotal.setCellValueFactory(param -> param.getValue().getValue().subtotal);

        colunaData.setCellValueFactory(param -> param.getValue().getValue().data);
        colunaData.setCellFactory(column -> new TreeTableCell<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            @Override protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.format(formatter));
            }
        });
    }
}