package abstractions;

import models.Pedido;

public abstract class EstadoPedido {
    protected Pedido pedido;

    public EstadoPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public abstract void preparar();
    public abstract void finalizarPreparo();
    public abstract void entregar();
}
