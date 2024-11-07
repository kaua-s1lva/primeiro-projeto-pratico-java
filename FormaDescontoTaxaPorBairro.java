public class FormaDescontoTaxaPorBairro implements IFormaDescontoTaxaEntrega {

    

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
        if ( this.getBairro(pedido).equals("Centro") || this.getBairro(pedido).equals("Bela Vista") || this.getBairro(pedido).equals("Cidade Maravilhosa") ) {
            return true;
        }
        return false;
    }

    private String getBairro(Pedido pedido) {
        return pedido.getCliente().getBairro();
    }
}
