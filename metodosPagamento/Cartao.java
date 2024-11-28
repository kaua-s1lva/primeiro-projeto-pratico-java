package metodosPagamento;

import interfaces.IMetodoPagamento;
import models.Pedido;

public abstract class Cartao implements IMetodoPagamento {
    private String numeroCartao;
    private String vencimento;
    private long cvc;
    private String nomeTitularCartao;

    public Cartao(String numeroCartao, String vencimento, long cvc, String nomeTitularCartao) {
        this.numeroCartao = numeroCartao;
        this.vencimento = vencimento;
        this.cvc = cvc;
        this.nomeTitularCartao = nomeTitularCartao;
    }

    @Override
    public abstract void processarPagamento(Pedido pedido, double valor);

}
