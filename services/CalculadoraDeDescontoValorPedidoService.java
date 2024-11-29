package services;

import formasDesconto.FormaDescontoCodCupomValorPedido;
import formasDesconto.FormaDescontoTipoClienteValorPedido;
import formasDesconto.FormaDescontoTipoItemValorPedido;
import interfaces.IFormaDescontoValorPedido;
import models.Pedido;

public class CalculadoraDeDescontoValorPedidoService {
    IFormaDescontoValorPedido desconto;

    public void aplicarDescontoTipoCliente(Pedido pedido) {
        desconto = new FormaDescontoTipoClienteValorPedido();
        desconto.calcularDesconto(pedido);
    }

    public void aplicarDescontoCodCupom(Pedido pedido, String cupom) {
        desconto = new FormaDescontoCodCupomValorPedido(cupom);
        desconto.calcularDesconto(pedido);
    }

    public void aplicarDescontoTipoItem(Pedido pedido) {
        desconto = new FormaDescontoTipoItemValorPedido();
        desconto.calcularDesconto(pedido);
    }
}
