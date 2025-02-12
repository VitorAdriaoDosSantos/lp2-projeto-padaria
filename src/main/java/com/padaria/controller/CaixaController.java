package com.padaria.controller;

import com.padaria.model.dao.DaoFactory;
import com.padaria.model.entities.Produto;
import com.padaria.model.entities.Venda;
import com.padaria.model.entities.VendaProdutos;
import com.padaria.util.ConfirmationDialog;
import com.padaria.util.Toast;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CaixaController {
    @FXML
    private FlowPane produtoFlowPane;
    @FXML
    private FlowPane carrinhoFlowPane;
    @FXML
    private Label totalLabel;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button buttonEfetuarVenda;

    private final List<Produto> carrinho = new ArrayList<>();

    private static CaixaController instance;
    private Stage stage;

    public CaixaController() {
        instance = this;
    }

    @FXML
    public void initialize() {
        adicionarCardProdutos();
        Platform.runLater(() -> stage = (Stage) produtoFlowPane.getScene().getWindow());
    }

    public static CaixaController getInstance() {
        return instance;
    }

    public void decrementarItem(Produto produto) {
        for (Produto p : carrinho) {
            if (p.getId() == produto.getId()) {
                int qtdAtual = p.getQuantidade();
                qtdAtual--;
                if (qtdAtual == 0) {
                    carrinho.remove(p);
                } else {
                    p.setQuantidade(qtdAtual);
                }
                atualizarCarrinho();
                return;
            }
        }
    }

    public void incrementarItem(Produto produto) {
        for (Produto p : carrinho) {
            if (p.getId() == produto.getId()) {
                int qtdAtual = p.getQuantidade();
                qtdAtual++;
                p.setQuantidade(qtdAtual);
                atualizarCarrinho();
                return;
            }
        }
    }

    public void removerItem(Produto produto) {
        for (Produto p : carrinho) {
            if (p.getId() == produto.getId()) {
                carrinho.remove(p);
                atualizarCarrinho();
                return;
            }
        }
    }

    public void adicionarProduto(Produto produto) {
        for (Produto p : carrinho) {
            if (p.getId() == produto.getId()) {
                int qtdAtual = p.getQuantidade();
                qtdAtual++;
                p.setQuantidade(qtdAtual);
                atualizarCarrinho();

                Toast.show(stage, "Produto adicionado ao carrinho", 1000, Toast.ToastPosition.TOP_CENTER, Toast.ToastType.INFO);
                return;

            }
        }

        carrinho.add(produto);
        Toast.show(stage, "Produto adicionado ao carrinho", 1000, Toast.ToastPosition.TOP_CENTER, Toast.ToastType.INFO);
        atualizarCarrinho();
    }

    private void atualizarCarrinho() {
        carrinhoFlowPane.getChildren().clear();

        double total = 0.0;

        for (Produto produto : carrinho) {
            total += produto.getPreco() * produto.getQuantidade();

            try {
                FXMLLoader produtoLoader = new FXMLLoader(getClass().getResource("/views/cardItem.fxml"));
                AnchorPane produtoCarrinho = produtoLoader.load();
                produtoCarrinho.setPrefWidth(scrollPane.getWidth());

                CardItemController cardItemController = produtoLoader.getController();
                cardItemController.setItem(produto);

                carrinhoFlowPane.getChildren().add(produtoCarrinho);
            } catch (Exception e) {
                System.out.println("Erro ao adicionar o produto: " + e.getMessage());
                e.printStackTrace();
            }
        }

        totalLabel.setText(String.format("Total: R$ %.2f", total));

        for (Produto p: carrinho) {
            System.out.println(p.getNome()+" "+ p.getQuantidade());
        }
    }

    private void adicionarCardProdutos() {
        produtoFlowPane.getChildren().clear();
        List<Produto> produtos = DaoFactory.createProdutoDao().buscarTodos();

        try {
            for (Produto produto : produtos) {
                FXMLLoader produtoLoader = new FXMLLoader(getClass().getResource("/views/cardProduto.fxml"));
                VBox produtoCard = produtoLoader.load();

                CardProdutoController cardProdutoController = produtoLoader.getController();
                cardProdutoController.setProduto(produto);

                produtoFlowPane.getChildren().add(produtoCard);
            }

        } catch (Exception e) {
            System.out.println("Erro ao adicionar o produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void efetuarVenda() {
        if (carrinho.isEmpty()) {
            Toast.show(stage, "Carrinho vazio!", 3000, Toast.ToastPosition.TOP_CENTER, Toast.ToastType.ERROR);
            return;
        } else {
            boolean confirmacao = ConfirmationDialog.show(stage, "Deseja efetuar a venda?");
            if (!confirmacao) {
                return;
            }
        }

        double valorTotal = 0.0;
        List<VendaProdutos> listaVendaProdutos = new ArrayList<>();

        for (Produto p : carrinho) {
            valorTotal += p.getPreco() * p.getQuantidade();
            VendaProdutos vendaProdutos = new VendaProdutos();
            vendaProdutos.setQuantidade(p.getQuantidade());
            vendaProdutos.setSubTotal(p.getPreco() * p.getQuantidade());
            vendaProdutos.setIdProduto(p.getId());
            listaVendaProdutos.add(vendaProdutos);
        }

        Venda venda = new Venda();
        venda.setData(java.time.LocalDateTime.now());
        venda.setItens(listaVendaProdutos);
        venda.setValorTotal(valorTotal);

        DaoFactory.createVendaDao().inserir(venda);
        limparCarrinho(); // Para limpar o carrinho após a venda
        adicionarCardProdutos(); // Para atualizar a tabela de produtos na aba de caixa também

        Stage stage = (Stage) buttonEfetuarVenda.getScene().getWindow();
        Toast.show(stage, "Venda efetuada com sucesso!", 3000,Toast.ToastPosition.TOP_CENTER, Toast.ToastType.SUCCESS);

    }

    private void limparCarrinho() {
        carrinho.clear();
        atualizarCarrinho();
    }
}