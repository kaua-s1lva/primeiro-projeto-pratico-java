public class FormaDescontoValorPedido implements IFormaDescontoTaxaEntrega {
    private double limiteValorPedido;
    private static double valorDesconto = 5.0;

    public FormaDescontoValorPedido () {

    }

    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            return new CupomDescontoEntrega("valor pedido", valorDesconto);
        }
        return null;
    }

    public boolean seAplica(Pedido pedido) {
        if (pedido.getValorPedido() > 200) {
            return true;
        }
        return false;
    }
}
