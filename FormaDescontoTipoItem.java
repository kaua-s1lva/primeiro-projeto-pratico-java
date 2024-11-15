import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTipoItem implements IFormaDescontoTaxaEntrega {
    Map<String,Double> descontosPorTipoItem = new HashMap<>();

    public FormaDescontoTipoItem() {
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
            System.out.println("Valor do desconto por item: " + valorDesconto);
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
