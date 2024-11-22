package estadosPedido;

import interfaces.IEstadoPedido;
import models.Pedido;

public class EstadoAberto implements IEstadoPedido {

    public EstadoAberto(Pedido pedido) {
        pedido.setEstado("Em aberto");
    }

    @Override
    public void preparar() {

    }

    @Override
    public void finalizarPreparo() {
        throw new RuntimeException("Pedido ainda não está em preparo");
    }

    @Override
    public void entregar() {
        throw new RuntimeException("Pedido ainda não está pronto");
    }
}
