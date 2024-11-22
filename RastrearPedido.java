import models.Pedido;

public class RastrearPedido {
    private Pedido pedido;

    public RastrearPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void atualizarParaEmPreparo() {
        pedido.setStatus("Em preparo");
    }

    public void atualizarParaEmTransito() {
        pedido.setStatus("Em trânsito");
    }

    public void atualizarParaEntregue() {
        pedido.setStatus("Entregue");
    }
}
