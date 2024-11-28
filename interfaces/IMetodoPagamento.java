package interfaces;

import models.Pedido;

public interface IMetodoPagamento {
    public void processarPagamento(Pedido pedido, double valor);
}
