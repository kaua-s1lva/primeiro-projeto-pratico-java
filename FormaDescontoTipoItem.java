import java.util.ArrayList;
import java.util.Map;

public class FormaDescontoTipoItem implements IFormaDescontoTaxaEntrega {
    Map<String,Double> descontosPorTipoItem;

    public FormaDescontoTipoItem() {
        descontosPorTipoItem.put("Alimentação", 5.0);
        descontosPorTipoItem.put("Educação", 2.0);
        descontosPorTipoItem.put("Lazer", 1.5);
    }

    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        double valorDesconto = 0;
        if (seAplica(pedido) && pedido.getDescontoConcedido() > 10) {
            for (Map.Entry<String,Double> entry : descontosPorTipoItem.entrySet()) {
                valorDesconto += entry.getValue();
            }
            return new CupomDescontoEntrega("Item", valorDesconto);
        } else {
            return null;
        }
    }

    public boolean seAplica(Pedido pedido) {
        ArrayList<Item> itens = pedido.getItens();
        for (int i=0; i<itens.size(); i++) {
            if (descontosPorTipoItem.get(itens.get(i).getTipo()) > 0) {
                return true;
            }
        }

        return false;
    }
}
