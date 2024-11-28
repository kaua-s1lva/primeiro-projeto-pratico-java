package services;

import interfaces.IMetodoPagamento;
import models.Pedido;

public class SistemaPagamentoService {
    public void realizarPagamento(Pedido pedido, IMetodoPagamento metodoPagamento, double valor) {
        metodoPagamento.processarPagamento(pedido, valor);
    }
}
