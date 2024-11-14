import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTaxaPorTipoCliente implements IFormaDescontoTaxaEntrega {
    private Map<String,Double> descontosPorTipoCliente = new HashMap<>();
    private String tipoCliente;

    public FormaDescontoTaxaPorTipoCliente(Pedido pedido) {
        tipoCliente = pedido.getCliente().getTipo();
        /*
        descontosPorTipoCliente.put("Ouro", 3.00);
        descontosPorTipoCliente.put("Prata", 2.00);
        descontosPorTipoCliente.put("Bronze", 1.00);
         */
        Main.getTiposCliente();
    }

    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            //System.out.println("Valor desconto total: " + (descontosPorTipoCliente.get(tipoCliente) + pedido.getDescontoConcedido()));
            if (pedido.getDescontoConcedido() + descontosPorTipoCliente.get(tipoCliente) > 10) {
                return new CupomDescontoEntrega(tipoCliente, 0);
            }
            return new CupomDescontoEntrega(tipoCliente, descontosPorTipoCliente.get(tipoCliente));
        }
        return null;
    }

    public boolean seAplica(Pedido pedido) {
        if (descontosPorTipoCliente.get(tipoCliente) > 0) return true;
        return false;
    }

}