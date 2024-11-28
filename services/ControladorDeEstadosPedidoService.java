package services;

import estadosPedido.EstadoAberto;
import models.Pedido;

public class ControladorDeEstadosPedidoService {
    public ControladorDeEstadosPedidoService(Pedido pedido) {
        pedido.setEstado(new EstadoAberto(pedido));
    }

    public void preparar(Pedido pedido) {
        pedido.getEstado().preparar();
    }

    public void finalizarPreparo(Pedido pedido) {
        pedido.getEstado().finalizarPreparo();
    }

    public void entregar(Pedido pedido) {
        pedido.getEstado().entregar();
    }
}
