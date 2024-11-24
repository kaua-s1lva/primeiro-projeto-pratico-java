package formasDesconto;

import interfaces.IFormaDescontoTaxaEntrega;
import models.CupomDescontoEntrega;
import models.Pedido;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTaxaPorBairro implements IFormaDescontoTaxaEntrega {
    private Map<String, Double> bairros = new HashMap<>();

    public FormaDescontoTaxaPorBairro() {
        bairros.put("Centro", 0.2);
        bairros.put("Bela Vista", 0.3);
        bairros.put("Cidade Maravilhosa", 0.15);
    }

    public void calcularDesconto(Pedido pedido) {
        if (seAplica(pedido)) {
            pedido.aplicarDesconto(new CupomDescontoEntrega
                ("Desconto por bairro", bairros.get(pedido.getCliente().getBairro()) * pedido.getTaxaEntrega()));
        }
    }

    public boolean seAplica(Pedido pedido) {
        return bairros.containsKey(pedido.getCliente().getBairro());
    }
}
