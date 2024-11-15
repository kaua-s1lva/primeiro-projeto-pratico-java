import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTaxaPorTipoCliente implements IFormaDescontoTaxaEntrega {
    private Map<String,Double> clientes = new HashMap<>();

    public FormaDescontoTaxaPorTipoCliente() {
        clientes.put("Ouro", 0.3);
        clientes.put("Prata", 0.2);
        clientes.put("Bronze", 0.1);
    }

    public void calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            double valorDesconto = clientes.get(pedido.getCliente().getTipo()) * pedido.getTaxaEntrega();
            System.out.println("Valor do desconto por cliente: " + valorDesconto);
            pedido.aplicarDesconto(new CupomDescontoEntrega("Desconto por Cliente", valorDesconto));
        }
    }

    public boolean seAplica(Pedido pedido) {
        return clientes.containsKey(pedido.getCliente().getTipo());
    }

}