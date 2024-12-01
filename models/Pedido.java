package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import abstractions.EstadoPedido;
import interfaces.IMetodoPagamento;

public class Pedido {
    private double taxaEntrega;
    private Cliente cliente;
    private ArrayList<Item> itens = new ArrayList<>();
    private List<CupomDescontoEntrega> cuponsDescontoEntrega;
    private List<CupomDescontoValorPedido> cuponsDescontoValorPedido;
    private EstadoPedido estado;
    private IMetodoPagamento metodoPagamento;

    public Pedido (Date data, Cliente cliente, double taxaEntrega) {
        this.cliente = cliente;
        this.taxaEntrega = taxaEntrega;
        cuponsDescontoEntrega = new ArrayList<>();
        cuponsDescontoValorPedido = new ArrayList<>();
    }

    public void adicionarItem (Item item) {
        itens.add(item);
    }

    public double getValorPedido() {
        double valorTotal = getTaxaEntregaComDesconto();
        for (int i=0; i<itens.size(); i++) {
            valorTotal += itens.get(i).getValorTotal();
        }
        return valorTotal;
    }

    public double getValorTotalPedido() {
        return getValorPedido() - getDescontoConcedidoValorPedido();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public double getTaxaEntregaComDesconto() {
        return taxaEntrega - this.getDescontoConcedidoTaxaEntrega();
    }

    public void aplicarDesconto(CupomDescontoEntrega cupom) {
        cuponsDescontoEntrega.add(cupom);
    }

    public void aplicarDescontoValorPedido(CupomDescontoValorPedido cupom) {
        cuponsDescontoValorPedido.add(cupom);
    }

    public double getDescontoConcedidoTaxaEntrega() {
        double descontoTotal = 0;
        for (CupomDescontoEntrega cupom : cuponsDescontoEntrega) {
            descontoTotal += cupom.getValorDesconto();
        }
        if (descontoTotal > this.getTaxaEntrega()) return this.getTaxaEntrega();
        return descontoTotal;
    }

    public double getDescontoConcedidoValorPedido() {
        double descontoTotal = 0;
        for (CupomDescontoValorPedido cupom : cuponsDescontoValorPedido) {
            descontoTotal += cupom.getValorDesconto();
        }
        return descontoTotal;
    }

    public List<CupomDescontoEntrega> getCuponsDescontoEntrega() {
        return Collections.unmodifiableList(this.cuponsDescontoEntrega);
    }

    public List<CupomDescontoValorPedido> getCuponsDescontoValorPedido() {
        return Collections.unmodifiableList(this.cuponsDescontoValorPedido);
    }

    public EstadoPedido getEstado () {
        return this.estado;
    }

    public void setCuponsDescontoEntrega(CupomDescontoEntrega cupom) {
        this.cuponsDescontoEntrega.add(cupom);
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setPagamentoRealizado(IMetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public IMetodoPagamento getPagamentoRealizado() {
        return metodoPagamento;
    }

    @Override
    public String toString() {
        return "\nTaxa de entrega: " + taxaEntrega + 
        "\nStatus do pedido: " + estado.getClass() + 
        "\nNome do cliente: " + cliente.getNome() + 
        "\nDesconto concedido para taxa de entrega: " + this.getDescontoConcedidoTaxaEntrega() + 
        "\nDesconto concedido para valor do pedido: " + this.getDescontoConcedidoValorPedido() + 
        "\nValor total do pedido: " + this.getValorTotalPedido() + 
        "\nPagamento foi realizado? " + metodoPagamento + 
        "\nValor Pedido desconto: " + this.cuponsDescontoValorPedido;
    }
}