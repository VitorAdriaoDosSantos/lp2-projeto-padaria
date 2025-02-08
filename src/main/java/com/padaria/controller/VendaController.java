package com.padaria.controller;

import com.padaria.model.dao.DaoFactory;
import com.padaria.model.entities.Venda;
import com.padaria.model.entities.VendaProdutos;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller responsável por carregar e exibir as vendas e seus produtos
 * em uma TreeTableView.
 *
 * As vendas (nós pais) e os seus itens/produtos (nós filhos) são representados
 * por objetos da classe interna {@link VendaItem}.
 */
public class VendaController {

    // Referência para a TreeTableView definida no arquivo FXML.
    @FXML
    private TreeTableView<VendaItem> tabelaVendas;

    // Declaração das colunas da tabela, definindo os tipos dos dados que serão exibidos.
    @FXML
    private TreeTableColumn<VendaItem, Number> colunaId;
    @FXML
    private TreeTableColumn<VendaItem, LocalDateTime> colunaData;
    @FXML
    private TreeTableColumn<VendaItem, Number> colunaTotal;
    @FXML
    private TreeTableColumn<VendaItem, Number> colunaIdProduto;
    @FXML
    private TreeTableColumn<VendaItem, String> colunaNomeProduto;
    @FXML
    private TreeTableColumn<VendaItem, String> colunaCategoriaProduto;
    @FXML
    private TreeTableColumn<VendaItem, Number> colunaPrecoProduto;
    @FXML
    private TreeTableColumn<VendaItem, Number> colunaQtd;
    @FXML
    private TreeTableColumn<VendaItem, Number> colunaSubtotal;

    /**
     * Método chamado automaticamente pelo JavaFX ao carregar o FXML.
     * Configura as colunas da TreeTableView e carrega os dados das vendas.
     */
    @FXML
    public void initialize() {
        configurarColunas();
        carregarVendas();
    }

    /**
     * Configura as colunas da TreeTableView, definindo como extrair os dados de cada
     * objeto {@link VendaItem} e, no caso da coluna de data, definindo um cellFactory
     * para formatar a data de forma legível.
     */
    private void configurarColunas() {
        // Configura os cellValueFactories para as colunas numéricas e de texto.
        colunaId.setCellValueFactory((TreeTableColumn.CellDataFeatures<VendaItem, Number> param) ->
                param.getValue().getValue().id);
        colunaTotal.setCellValueFactory((TreeTableColumn.CellDataFeatures<VendaItem, Number> param) ->
                param.getValue().getValue().total);
        colunaIdProduto.setCellValueFactory((TreeTableColumn.CellDataFeatures<VendaItem, Number> param) ->
                param.getValue().getValue().idProduto);
        colunaNomeProduto.setCellValueFactory((TreeTableColumn.CellDataFeatures<VendaItem, String> param) ->
                param.getValue().getValue().nomeProduto);
        colunaCategoriaProduto.setCellValueFactory((TreeTableColumn.CellDataFeatures<VendaItem, String> param) ->
                param.getValue().getValue().categoriaProduto);
        colunaPrecoProduto.setCellValueFactory((TreeTableColumn.CellDataFeatures<VendaItem, Number> param) ->
                param.getValue().getValue().precoProduto);
        colunaQtd.setCellValueFactory((TreeTableColumn.CellDataFeatures<VendaItem, Number> param) ->
                param.getValue().getValue().qtd);
        colunaSubtotal.setCellValueFactory((TreeTableColumn.CellDataFeatures<VendaItem, Number> param) ->
                param.getValue().getValue().subtotal);

        // Configuração para a coluna de data:
        // - Utiliza a propriedade LocalDateTime armazenada em VendaItem.
        // - Utiliza um cellFactory customizado para formatar a data no padrão "dd/MM/yyyy HH:mm:ss".
        colunaData.setCellValueFactory((TreeTableColumn.CellDataFeatures<VendaItem, LocalDateTime> param) ->
                param.getValue().getValue().data);
        colunaData.setCellFactory(column -> new TreeTableCell<VendaItem, LocalDateTime>() {
            // Define o formatador para exibir a data e hora.
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Formata a data conforme o padrão definido e exibe.
                    setText(item.format(formatter));
                }
            }
        });
    }

    /**
     * Carrega as vendas e seus respectivos itens (produtos) da base de dados,
     * criando uma estrutura de árvore (TreeItem) para ser exibida na TreeTableView.
     *
     * Cada venda é representada por um nó pai, e cada produto vendido é representado
     * por um nó filho.
     */
    private void carregarVendas() {
        // Cria um nó root para a árvore. Esse nó não será exibido (setShowRoot(false)).
        TreeItem<VendaItem> root = new TreeItem<>(new VendaItem(0, LocalDateTime.now(), 0.0));
        tabelaVendas.setRoot(root);
        tabelaVendas.setShowRoot(false);

        try {
            // Obtém a lista de vendas utilizando o DAO.
            List<Venda> listaVendas = DaoFactory.createVendaDao().listaVendas();

            // Para cada venda na lista:
            for (Venda venda : listaVendas) {
                // Cria um nó (pai) representando a venda.
                // Certifique-se de que o método venda.getData() retorne um LocalDateTime.
                TreeItem<VendaItem> vendaItem = new TreeItem<>(new VendaItem(
                        venda.getId(),
                        venda.getData(),
                        venda.getValorTotal()
                ));

                // Para cada produto contido na venda, cria um nó filho.
                List<VendaProdutos> listaItens = venda.getItens();
                for (VendaProdutos vendaProdutos : listaItens) {
                    TreeItem<VendaItem> produtoItem = new TreeItem<>(new VendaItem(
                            vendaProdutos.getIdProduto(),
                            vendaProdutos.getProduto().getNome(),
                            vendaProdutos.getProduto().getCategoria(),
                            vendaProdutos.getProduto().getPreco(),
                            vendaProdutos.getQuantidade(),
                            vendaProdutos.getSubTotal()
                    ));
                    // Adiciona o produto como filho do nó da venda.
                    vendaItem.getChildren().add(produtoItem);
                }

                // Adiciona o nó da venda (com seus filhos) à raiz da árvore.
                tabelaVendas.getRoot().getChildren().add(vendaItem);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar as vendas: " + e.getMessage());
        }
    }

    /**
     * Classe interna que representa um item na TreeTableView.
     *
     * Esta classe é usada tanto para representar uma venda (nó pai) quanto
     * para representar um produto contido na venda (nó filho). Os campos não
     * utilizados em cada caso são inicializados com valores padrão.
     */
    public static class VendaItem {
        // Campos referentes à venda (nó pai):
        final SimpleIntegerProperty id;
        final SimpleObjectProperty<LocalDateTime> data;
        final SimpleDoubleProperty total;

        // Campos referentes aos produtos da venda (nó filho):
        final SimpleIntegerProperty idProduto;
        final SimpleStringProperty nomeProduto;
        final SimpleStringProperty categoriaProduto;
        final SimpleDoubleProperty precoProduto;
        final SimpleIntegerProperty qtd;
        final SimpleDoubleProperty subtotal;

        /**
         * Construtor para representar uma venda (nó pai).
         *
         * @param id    O ID da venda.
         * @param data  A data e hora da venda.
         * @param total O valor total da venda.
         */
        public VendaItem(int id, LocalDateTime data, double total) {
            this.id = new SimpleIntegerProperty(id);
            this.data = new SimpleObjectProperty<>(data);
            this.total = new SimpleDoubleProperty(total);
            // Inicializa os campos referentes ao produto com valores padrão.
            this.idProduto = new SimpleIntegerProperty(0);
            this.nomeProduto = new SimpleStringProperty("");
            this.categoriaProduto = new SimpleStringProperty("");
            this.precoProduto = new SimpleDoubleProperty(0);
            this.qtd = new SimpleIntegerProperty(0);
            this.subtotal = new SimpleDoubleProperty(0);
        }

        /**
         * Construtor para representar um produto contido na venda (nó filho).
         *
         * @param idProduto       O ID do produto.
         * @param nomeProduto     O nome do produto.
         * @param categoriaProduto A categoria do produto.
         * @param precoProduto    O preço do produto.
         * @param qtd             A quantidade vendida.
         * @param subtotal        O subtotal deste item.
         */
        public VendaItem(int idProduto, String nomeProduto, String categoriaProduto, double precoProduto, int qtd, double subtotal) {
            this.idProduto = new SimpleIntegerProperty(idProduto);
            this.nomeProduto = new SimpleStringProperty(nomeProduto);
            this.categoriaProduto = new SimpleStringProperty(categoriaProduto);
            this.precoProduto = new SimpleDoubleProperty(precoProduto);
            this.qtd = new SimpleIntegerProperty(qtd);
            this.subtotal = new SimpleDoubleProperty(subtotal);
            // Inicializa os campos referentes à venda com valores padrão.
            this.id = new SimpleIntegerProperty(0);
            this.data = new SimpleObjectProperty<>(null);
            this.total = new SimpleDoubleProperty(0);
        }
    }
}
