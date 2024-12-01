package models;

public class CupomDescontoValorPedido {
    private String nomeMetodo;
    private double valorDesconto;

    public CupomDescontoValorPedido(String nomeMetodo, double valorDesconto) {
        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
    }

    public String getNomeMetodo() {
        return nomeMetodo;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    @Override
    public String toString() {
        return "\nNome do método: " + nomeMetodo + "\nValor do desconto: " + valorDesconto;
    }
}
