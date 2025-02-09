package com.padaria.controller;

import com.padaria.model.dao.DaoFactory;
import com.padaria.model.entities.Venda;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class RelatorioController {

    String[] escolhas = {"1 Mês", "3 Meses", "12 Meses", "Período"};

    @FXML
    private ChoiceBox<String> choiceBoxEscolhas;
    @FXML
    private DatePicker datePickerInicio;
    @FXML
    private DatePicker datePickerFinal;
    @FXML
    private BarChart<String, Number> barChartGanhos;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    public void initialize() {
        choiceBoxEscolhas.getItems().addAll(escolhas);
        choiceBoxEscolhas.setValue(escolhas[0]);

        // Atualiza o gráfico sempre que a opção for alterada.
        choiceBoxEscolhas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            handleChoiceBox();
        });

        // Atualiza o gráfico inicialmente com a opção padrão ("1 Mês")
        atualizarGrafico(LocalDate.now().minusMonths(1), LocalDate.now(), choiceBoxEscolhas.getValue());
    }

    private void handleChoiceBox() {
        String escolha = choiceBoxEscolhas.getValue();
        switch (escolha) {
            case "1 Mês":
                atualizarGrafico(LocalDate.now().minusMonths(1), LocalDate.now(), escolha);
                break;
            case "3 Meses":
                atualizarGrafico(LocalDate.now().minusMonths(3), LocalDate.now(), escolha);
                break;
            case "12 Meses":
                atualizarGrafico(LocalDate.now().minusMonths(12), LocalDate.now(), escolha);
                break;
            case "Período":
                if (datePickerInicio.getValue() != null && datePickerFinal.getValue() != null) {
                    atualizarGrafico(datePickerInicio.getValue(), datePickerFinal.getValue(), escolha);
                }
                break;
        }
    }

    private void atualizarGrafico(LocalDate dataInicio, LocalDate dataFim, String escolha) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ganhos");

        switch (escolha) {
            case "1 Mês": {
                // Agrupar por dia
                long quantidadeDias = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;
                for (int i = 0; i < quantidadeDias; i++) {
                    LocalDate data = dataInicio.plusDays(i);
                    double ganho = getGanhosParaData(data, data);
                    series.getData().add(new XYChart.Data<>(data.toString(), ganho));
                }
                break;
            }
            case "3 Meses": {
                // Agrupar ganhos por mês
                LocalDate data = dataInicio.withDayOfMonth(1);
                while (!data.isAfter(dataFim)) {
                    LocalDate ultimoDia = data.withDayOfMonth(data.lengthOfMonth());
                    double ganho = getGanhosParaData(data, ultimoDia);
                    series.getData().add(new XYChart.Data<>(data.getMonth().toString() + "/" + data.getYear(), ganho));
                    data = data.plusMonths(1);
                }
                break;
            }
            case "12 Meses": {
                // Agrupar ganhos por mês para um período maior
                LocalDate data = dataInicio.withDayOfMonth(1);
                while (!data.isAfter(dataFim)) {
                    LocalDate ultimoDia = data.withDayOfMonth(data.lengthOfMonth());
                    double ganho = getGanhosParaData(data, ultimoDia);
                    series.getData().add(new XYChart.Data<>(data.getMonth().toString() + "/" + data.getYear(), ganho));
                    data = data.plusMonths(1);
                }
                break;
            }
            case "Período": {
                // Agrupar por dia no intervalo definido pelo usuário
                long dias = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;
                for (int i = 0; i < dias; i++) {
                    LocalDate data = dataInicio.plusDays(i);
                    double ganho = getGanhosParaData(data, data);
                    series.getData().add(new XYChart.Data<>(data.toString(), ganho));
                }
                break;
            }
        }

        // Atualiza o gráfico
        barChartGanhos.getData().clear();
        barChartGanhos.getData().add(series);
    }

    private double getGanhosParaData(LocalDate dataInicio, LocalDate dataFim) {
        List<Venda> vendas = DaoFactory.createVendaDao().listaVendas();
        double total = 0.0;
        for (Venda venda : vendas) {
            LocalDate dataVenda = venda.getData().toLocalDate();
            if ((dataVenda.isEqual(dataInicio) || dataVenda.isAfter(dataInicio)) &&
                    (dataVenda.isEqual(dataFim) || dataVenda.isBefore(dataFim))) {
                total += venda.getValorTotal();
            }
        }
        return total;
    }
}
