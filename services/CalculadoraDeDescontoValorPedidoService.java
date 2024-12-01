package services;

import interfaces.IFormaDescontoValorPedido;
import models.Pedido;

public class CalculadoraDeDescontoValorPedidoService {
    IFormaDescontoValorPedido desconto;

    public void aplicarDesconto(Pedido pedido, IFormaDescontoValorPedido formaDesconto) {
        if(formaDesconto == null) {
            throw new RuntimeException("forma de desconto não adicionada");
        }
        formaDesconto.calcularDesconto(pedido);
    }
}
