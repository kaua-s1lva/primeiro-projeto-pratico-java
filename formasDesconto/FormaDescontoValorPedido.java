package formasDesconto;
import interfaces.IFormaDescontoTaxaEntrega;
import models.CupomDescontoEntrega;
import models.Pedido;

public class FormaDescontoValorPedido implements IFormaDescontoTaxaEntrega {
    private static double valorDesconto = 0.15;

    @Override
    public void calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            pedido.aplicarDesconto(new CupomDescontoEntrega("Desconto por valor", valorDesconto * pedido.getTaxaEntrega()));
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        return pedido.getValorPedido() > 2000;
    }
}
