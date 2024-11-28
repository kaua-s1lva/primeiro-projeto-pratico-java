package estadosPedido;

import abstractions.EstadoPedido;
import models.Pedido;

public class EstadoPronto extends EstadoPedido {
    public EstadoPronto(Pedido pedido) {
        super(pedido);
        System.out.println("Pedido está pronto");
    }

    @Override
    public void preparar() {
        throw new RuntimeException("Pedido já está pronto");
    }

    @Override
    public void finalizarPreparo() {
        throw new RuntimeException("Pedido já está pronto");
    }

    @Override
    public void entregar() {
        pedido.setEstado(new EstadoEntregue(pedido));
    }
}
