public class FormaDescontoTaxaPorBairro implements IFormaDescontoTaxaEntrega {
    private String bairroCliente;

    public FormaDescontoTaxaPorBairro(Pedido pedido) {
        this.bairroCliente = pedido.getCliente().getBairro();
    }

    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            if (this.getBairro(pedido) == "Centro") {
                return new CupomDescontoEntrega("bairro", 2);
            } else if (this.getBairro(pedido) == "Bela Vista") {
                return new CupomDescontoEntrega("bairro", 3);
            } else {
                return new CupomDescontoEntrega("bairro", 1.5);
            }
        } else return null;
    }

    public boolean seAplica(Pedido pedido) {
        if ( bairroCliente.equals("Centro") || bairroCliente.equals("Bela Vista") || bairroCliente.equals("Cidade Maravilhosa") ) {
            return true;
        }
        return false;
    }

    private String getBairro(Pedido pedido) {
        return pedido.getCliente().getBairro();
    }
}
