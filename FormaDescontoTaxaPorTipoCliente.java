import java.util.Map;

public class FormaDescontoTaxaPorTipoCliente implements IFormaDescontoTaxaEntrega {
    private Map<String,Double> descontosPorTipoCliente;
    private String tipoCliente;

    public FormaDescontoTaxaPorTipoCliente(Pedido pedido) {
        tipoCliente = pedido.getCliente().getTipo();
        descontosPorTipoCliente.put("Ouro", 3.00);
        descontosPorTipoCliente.put("Prata", 2.00);
        descontosPorTipoCliente.put("Bronze", 1.00);
    }

    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            return new CupomDescontoEntrega(tipoCliente, descontosPorTipoCliente.get(tipoCliente));
        }
        return null;
    }

    public boolean seAplica(Pedido pedido) {
        if (descontosPorTipoCliente.get(tipoCliente) > 0) return true;
        return false;
    }

}