package estadosPedido;

import abstractions.EstadoPedido;
import models.Pedido;

public class EstadoEntregue extends EstadoPedido {
    public EstadoEntregue(Pedido pedido) {
        super(pedido);
        System.out.println("Pedido está entregue");
    }

    @Override
    public void preparar() {
        throw new RuntimeException("Pedido já está entregue");
    }

    @Override
    public void finalizarPreparo() {
        throw new RuntimeException("Pedido já está entregue");
    }

    @Override
    public void entregar() {
        throw new RuntimeException("Pedido já está entregue");
    }
}
