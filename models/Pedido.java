package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import abstractions.EstadoPedido;
import interfaces.IFormaDescontoValorPedido;
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
        return taxaEntrega - this.getDescontoConcedido();
    }

    public void aplicarDesconto(CupomDescontoEntrega cupom) {
        cuponsDescontoEntrega.add(cupom);
    }

    public void aplicarDescontoValorPedido(CupomDescontoValorPedido cupom) {
        cuponsDescontoValorPedido.add(cupom);
    }

    public double getDescontoConcedido() {
        double descontoTotal = 0;
        for (CupomDescontoEntrega cupom : cuponsDescontoEntrega) {
            descontoTotal += cupom.getValorDesconto();
        }
        if (descontoTotal > this.getTaxaEntrega()) return this.getTaxaEntrega();
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

    public String toString() {
        for (CupomDescontoValorPedido cupom : this.cuponsDescontoValorPedido) {
            System.out.println("Valor do desconto no valor do pedido: " + cupom);
        }
        return "\nTaxa de entrega: " + taxaEntrega + 
        "\nStatus do pedido: " + estado.getClass() + 
        "\nNome do cliente: " + cliente.getNome() + 
        "\nDesconto fornecido: " + this.getDescontoConcedido() + 
        "\nValor total do pedido: " + this.getValorPedido() + 
        "\nPagamento foi realizado? " + metodoPagamento + 
        "\nValor Pedido desconto: " + this.cuponsDescontoValorPedido;
    }
}