package services;

import java.util.ArrayList;

import estadosPedido.EstadoAberto;
import interfaces.IEstadoPedido;
import models.Pedido;

public class ControleDeEstadosPedidoService {
    private ArrayList<IEstadoPedido> estados = new ArrayList<>();

    public ControleDeEstadosPedidoService(Pedido pedido) {
        estados.add(new EstadoAberto(pedido));
    }
}
