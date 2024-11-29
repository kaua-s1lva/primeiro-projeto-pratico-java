package formasDescontoValorPedido;

import interfaces.IFormaDescontoValorPedido;
import models.CupomDescontoValorPedido;
import models.Pedido;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTipoClienteValorPedido implements IFormaDescontoValorPedido {
    private Map<String,Double> clientes;

    public FormaDescontoTipoClienteValorPedido() {
        clientes = new HashMap<>();
        clientes.put("Ouro", 0.3);
        clientes.put("Prata", 0.2);
        clientes.put("Bronze", 0.1);
    }

    public void calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            double valorDesconto = clientes.get(pedido.getCliente().getTipo()) * pedido.getValorPedido();
            pedido.aplicarDescontoValorPedido(new CupomDescontoValorPedido("Desconto por Cliente", valorDesconto));
        }
    }

    public boolean seAplica(Pedido pedido) {
        for(CupomDescontoValorPedido cupom : pedido.getCuponsDescontoValorPedido()) {
            if (cupom.getNomeMetodo().equals("Desconto por Item")) return false;
        }

        return clientes.containsKey(pedido.getCliente().getTipo());
    }

}