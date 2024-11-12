import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private double taxaEntrega = 10;
    private Cliente cliente;
    private ArrayList<Item> itens = new ArrayList<>();
    private ArrayList<CupomDescontoEntrega> cuponsDescontoEntrega = new ArrayList<>();

    public Pedido (Date data, Cliente cliente) {
        this.cliente = cliente;
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

    public void aplicarDesconto(CalculadoraDeDescontoService desconto) {
        desconto = new CalculadoraDeDescontoService(this);
        desconto.calcularDesconto(this);
    }

    public double getDescontoConcedido() {
        double descontoTotal = 0;
        for (int i=0; i<cuponsDescontoEntrega.size(); i++) {
            descontoTotal += cuponsDescontoEntrega.get(i).getValorDesconto();
        }
        return descontoTotal;
    }

    public ArrayList<CupomDescontoEntrega> getCuponsDescontoEntrega() {
        return cuponsDescontoEntrega;
    }

    public String toString() {
        return "\nTaxa de entrega: " + taxaEntrega + "\nNome do cliente: " + cliente.getNome() + "\nDesconto fornecido: " + this.getDescontoConcedido();
    }
}