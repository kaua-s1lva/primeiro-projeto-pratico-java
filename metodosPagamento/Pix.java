package metodosPagamento;

import interfaces.IMetodoPagamento;
import models.Pedido;

public class Pix implements IMetodoPagamento {
    @Override
    public void processarPagamento(Pedido pedido, double valor) {
        
    }
}
