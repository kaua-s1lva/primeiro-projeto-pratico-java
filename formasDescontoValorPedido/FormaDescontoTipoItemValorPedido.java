package formasDescontoValorPedido;

import java.util.HashMap;
import java.util.Map;

import interfaces.IFormaDescontoValorPedido;
import models.CupomDescontoValorPedido;
import models.Item;
import models.Pedido;

public class FormaDescontoTipoItemValorPedido implements IFormaDescontoValorPedido {
    private Map<String,Double> descontos;

    public FormaDescontoTipoItemValorPedido() {
        descontos = new HashMap<>();
        descontos.put("Alimentação", 0.05);
        descontos.put("Educação", 0.2);
        descontos.put("Lazer", 0.15);
    }

    public void calcularDesconto(Pedido pedido) {
        double valorDesconto = 0;
        if (seAplica(pedido)) {
            for (Item item : pedido.getItens()) {
                if (descontos.containsKey(item.getTipo())) {
                    valorDesconto += pedido.getValorPedido() * descontos.get(item.getTipo());
                }
            }
            pedido.aplicarDescontoValorPedido(new CupomDescontoValorPedido("Desconto por Item", valorDesconto));
        }
    }

    public boolean seAplica(Pedido pedido) {
        for (CupomDescontoValorPedido cupom : pedido.getCuponsDescontoValorPedido()) {
            if (cupom.getNomeMetodo().equals("Desconto por Cliente") || 
                cupom.getNomeMetodo().equals("Desconto por Código de Cupom")) return false;
        }

        for (Item item : pedido.getItens()) {
            if (descontos.containsKey(item.getTipo())) {
                return true;
            }
        }
        return false;
    }
}
