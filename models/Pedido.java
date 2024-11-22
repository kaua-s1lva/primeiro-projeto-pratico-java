package models;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private double taxaEntrega;
    private Cliente cliente;
    private ArrayList<Item> itens = new ArrayList<>();
    private ArrayList<CupomDescontoEntrega> cuponsDescontoEntrega = new ArrayList<>();
    private String status;

    public Pedido (Date data, Cliente cliente, double taxaEntrega) {
        this.cliente = cliente;
        this.taxaEntrega = taxaEntrega;
        this.status = "Pendente";
    }

    public void adicionarItem (Item item) {
        itens.add(item);
    }

    public double getValorPedido() {
        double valorTotal = taxaEntrega;
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

    public void aplicarDesconto(CupomDescontoEntrega cupom) {
        cuponsDescontoEntrega.add(cupom);
    }

    public double getDescontoConcedido() {
        double descontoTotal = 0;
        for (CupomDescontoEntrega cupom : cuponsDescontoEntrega) {
            System.out.println("nome: " + cupom.getNomeMetodo() + " - " + cupom.getValorDesconto());
            descontoTotal += cupom.getValorDesconto();
        }
        if (descontoTotal > this.getTaxaEntrega()) return this.getTaxaEntrega();
        return descontoTotal;
    }

    public ArrayList<CupomDescontoEntrega> getCuponsDescontoEntrega() {
        return cuponsDescontoEntrega;
    }

    public void setCuponsDescontoEntrega(CupomDescontoEntrega cupom) {
        this.cuponsDescontoEntrega.add(cupom);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "\nTaxa de entrega: " + taxaEntrega + "\nStatus do pedido: " + status + "\nNome do cliente: " + cliente.getNome() + "\nDesconto fornecido: " + this.getDescontoConcedido();
    }
}