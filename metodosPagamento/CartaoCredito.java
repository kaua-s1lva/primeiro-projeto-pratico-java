package metodosPagamento;

import models.Pedido;

public class CartaoCredito extends Cartao {
    private int parcelas;

    public CartaoCredito(String numeroCartao, String vencimento, long cvc, String nomeTitularCartao, int parcelas) {
        super(numeroCartao, vencimento, cvc, nomeTitularCartao);
        this.parcelas = parcelas;
    }

    @Override
    public void processarPagamento(Pedido pedido, double valor) {
        pedido.setPagamentoRealizado(this);
    }
}
