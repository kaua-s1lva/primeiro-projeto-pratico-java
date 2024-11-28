package metodosPagamento;

import models.Pedido;

public class CartaoDebito extends Cartao {

    public CartaoDebito(String numeroCartao, String vencimento, long cvc, String nomeTitularCartao) {
        super(numeroCartao, vencimento, cvc, nomeTitularCartao);
    }

    @Override
    public void processarPagamento(Pedido pedido, double valor) {
        pedido.setPagamentoRealizado(this);
    }
}
