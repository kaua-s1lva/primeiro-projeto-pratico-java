package interfaces;

import models.Pedido;

public interface IFormaDescontoTaxaEntrega {
    public void calcularDesconto(Pedido pedido);
    public boolean seAplica(Pedido pedido);
}
