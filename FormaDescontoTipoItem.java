import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTipoItem implements IFormaDescontoTaxaEntrega {
    Map<String,Double> descontosPorTipoItem = new HashMap<>();

    public FormaDescontoTipoItem() {
        descontosPorTipoItem.put("Alimentação", 5.0);
        descontosPorTipoItem.put("Educação", 2.0);
        descontosPorTipoItem.put("Lazer", 1.5);
    }

    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        System.out.println("Valor do desconto ate agora: " + pedido.getDescontoConcedido());

        double valorDesconto = 0;
        if (seAplica(pedido)) {
            for (int i=0; i<pedido.getItens().size(); i++) {
                if ( descontosPorTipoItem.get(pedido.getItens().get(i).getTipo()) > 0 ) {
                    valorDesconto += descontosPorTipoItem.get(pedido.getItens().get(i).getTipo());
                    if (pedido.getDescontoConcedido() + valorDesconto > 10) {
                        valorDesconto = 10 - pedido.getDescontoConcedido();
                    }
                }
            }
            //System.out.println("Valor do desconto por tipo item: " + valorDesconto);
            return new CupomDescontoEntrega("Item", valorDesconto);
        } else {
            return null;
        }
    }

    public boolean seAplica(Pedido pedido) {
        ArrayList<Item> itens = pedido.getItens();
        for (int i=0; i<itens.size(); i++) {
            if (descontosPorTipoItem.get(itens.get(i).getTipo()) != null) {
                return true;
            }
        }

        return false;
    }
}
