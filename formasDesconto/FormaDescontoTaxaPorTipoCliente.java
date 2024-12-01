package formasDesconto;

import interfaces.IFormaDescontoTaxaEntrega;
import models.CupomDescontoEntrega;
import models.Pedido;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTaxaPorTipoCliente implements IFormaDescontoTaxaEntrega {
    private Map<String,Double> clientes;

    public FormaDescontoTaxaPorTipoCliente() {
        clientes = new HashMap<>();
        clientes.put("Ouro", 0.3);
        clientes.put("Prata", 0.2);
        clientes.put("Bronze", 0.1);
    }

    @Override
    public void calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            double valorDesconto = clientes.get(pedido.getCliente().getTipo()) * pedido.getTaxaEntrega();
            pedido.aplicarDesconto(new CupomDescontoEntrega("Desconto por Cliente", valorDesconto));
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        return clientes.containsKey(pedido.getCliente().getTipo());
    }

}