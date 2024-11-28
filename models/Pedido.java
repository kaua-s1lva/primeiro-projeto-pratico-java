package models;

import java.util.ArrayList;
import java.util.Date;

import abstractions.EstadoPedido;
import interfaces.IMetodoPagamento;

public class Pedido {
    private double taxaEntrega;
    private Cliente cliente;
    private ArrayList<Item> itens = new ArrayList<>();
    private ArrayList<CupomDescontoEntrega> cuponsDescontoEntrega = new ArrayList<>();
    private EstadoPedido estado;
    private IMetodoPagamento metodoPagamento;

    public Pedido (Date data, Cliente cliente, double taxaEntrega) {
        this.cliente = cliente;
        this.taxaEntrega = taxaEntrega;
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

    public double getDescontoConcedido() {
        double descontoTotal = 0;
        for (CupomDescontoEntrega cupom : cuponsDescontoEntrega) {
            descontoTotal += cupom.getValorDesconto();
        }
        if (descontoTotal > this.getTaxaEntrega()) return this.getTaxaEntrega();
        return descontoTotal;
    }

    public ArrayList<CupomDescontoEntrega> getCuponsDescontoEntrega() {
        return cuponsDescontoEntrega;
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
        return "\nTaxa de entrega: " + taxaEntrega + "\nStatus do pedido: " + estado.getClass() + "\nNome do cliente: " + cliente.getNome() + "\nDesconto fornecido: " + this.getDescontoConcedido() + "\nValor total do pedido: " + this.getValorPedido() + "\nPagamento foi realizado? " + metodoPagamento;
    }
}