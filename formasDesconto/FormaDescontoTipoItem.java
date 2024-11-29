package formasDesconto;

import interfaces.IFormaDescontoTaxaEntrega;
import models.CupomDescontoEntrega;
import models.Item;
import models.Pedido;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTipoItem implements IFormaDescontoTaxaEntrega {
    private Map<String,Double> descontosPorTipoItem;

    public FormaDescontoTipoItem() {
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
                    valorDesconto += pedido.getTaxaEntrega() * descontosPorTipoItem.get(item.getTipo());
                }
            }
            pedido.aplicarDesconto(new CupomDescontoEntrega("Desconto por Item", valorDesconto));
        }
    }

    public boolean seAplica(Pedido pedido) {
        for (Item item : pedido.getItens()) {
            if (descontosPorTipoItem.containsKey(item.getTipo())) {
                return true;
            }
        }
        return false;
    }
}
