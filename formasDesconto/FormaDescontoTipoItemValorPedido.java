package formasDesconto;

import java.util.HashMap;
import java.util.Map;

import interfaces.IFormaDescontoValorPedido;
import models.CupomDescontoValorPedido;
import models.Item;
import models.Pedido;

public class FormaDescontoTipoItemValorPedido implements IFormaDescontoValorPedido {
    private Map<String,Double> descontosPorTipoItem;

    public FormaDescontoTipoItemValorPedido() {
        descontosPorTipoItem = new HashMap<>();
        descontosPorTipoItem.put("Alimentação", 0.05);
        descontosPorTipoItem.put("Educação", 0.2);
        descontosPorTipoItem.put("Lazer", 0.15);
    }

    public void calcularDesconto(Pedido pedido) {
        double valorDesconto = 0;
        if (seAplica(pedido)) {
            for (Item item : pedido.getItens()) {
                if (descontosPorTipoItem.containsKey(item.getTipo())) {
                    valorDesconto += pedido.getValorPedido() * descontosPorTipoItem.get(item.getTipo());
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
            if (descontosPorTipoItem.containsKey(item.getTipo())) {
                return true;
            }
        }
        return false;
    }
}
