public class FormaDescontoValorPedido implements IFormaDescontoTaxaEntrega {
    private static double valorDesconto = 0.15;

    public void calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            pedido.aplicarDesconto(new CupomDescontoEntrega("Desconto por valor", valorDesconto * pedido.getTaxaEntrega()));
        }
    }

    public boolean seAplica(Pedido pedido) {
        return pedido.getValorPedido() > 2000;
    }
}
