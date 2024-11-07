import java.util.ArrayList;
import java.util.Map;

public class FormaDescontoTipoItem implements IFormaDescontoTaxaEntrega {
    Map<String,Double> descontosPorTipoItem;

    //public FormaDescontoTipoItem()    --construtor

    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        double valorDesconto = 0;
        if (seAplica(pedido)) {
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
            if (itens.get(i).getTipo().equals("Alimentação")) {
                descontosPorTipoItem.put("Alimentação", 5.0);
                return true;
            } else if (itens.get(i).getTipo().equals("Educação")) {
                descontosPorTipoItem.put("Educação", 2.0);
                return true;
            } else if (itens.get(i).getTipo().equals("Lazer")) {
                descontosPorTipoItem.put("Lazer", 1.5);
                return false;
            }
        }

        return false;
    }
}
