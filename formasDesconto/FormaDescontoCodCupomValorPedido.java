package formasDesconto;

import java.util.HashMap;
import java.util.Map;

import interfaces.IFormaDescontoValorPedido;
import models.CupomDescontoValorPedido;
import models.Pedido;

public class FormaDescontoCodCupomValorPedido implements IFormaDescontoValorPedido {
    private Map<String,Double> cupons;
    private String cupomCliente;

    public FormaDescontoCodCupomValorPedido(String cupomCliente) {
        cupons = new HashMap<>();
        cupons.put("DESC10", 0.1);
        cupons.put("DESC20", 0.2);
        cupons.put("DESC30", 0.3);
        this.cupomCliente = cupomCliente;
    }

    public void calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            double valorDesconto = cupons.get(cupomCliente) * pedido.getValorPedido();
            pedido.aplicarDescontoValorPedido(new CupomDescontoValorPedido("Desconto por Código de Cupom", valorDesconto));
        }
    }

    public boolean seAplica(Pedido pedido) {
        for(CupomDescontoValorPedido cupom : pedido.getCuponsDescontoValorPedido()) {
            if (cupom.getNomeMetodo().equals("Desconto por Item")) return false;
        }

        return cupons.containsKey(cupomCliente);
    }
}
