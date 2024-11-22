import models.Pedido;

public class RastrearPedido {
    private Pedido pedido;

    public RastrearPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void atualizarParaEmPreparo() {
        pedido.setEstado("Em preparo");
    }

    public void atualizarParaEmTransito() {
        pedido.setEstado("Em trânsito");
    }

    public void atualizarParaEntregue() {
        pedido.setEstado("Entregue");
    }
}
