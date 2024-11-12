import java.util.ArrayList;

public class CalculadoraDeDescontoService {
    private ArrayList<IFormaDescontoTaxaEntrega> metodosDeDesconto = new ArrayList<>();

    public CalculadoraDeDescontoService(Pedido pedido) {
        metodosDeDesconto.add(new FormaDescontoTaxaPorBairro(pedido));
        metodosDeDesconto.add(new FormaDescontoTaxaPorTipoCliente(pedido));
        metodosDeDesconto.add(new FormaDescontoTipoItem());
        metodosDeDesconto.add(new FormaDescontoValorPedido());
    }

    public void calcularDesconto(Pedido pedido) {
        /*
        ArrayList<CupomDescontoEntrega> cuponsDescontoEntrega = new ArrayList<>();
        for (int i=0; i<metodosDeDesconto.size(); i++) {
            if (metodosDeDesconto.get(i).seAplica(pedido)) {
                cuponsDescontoEntrega.add(metodosDeDesconto.get(i).calcularDesconto(pedido));
            }
        }
        return cuponsDescontoEntrega;
         */
        for (IFormaDescontoTaxaEntrega formaDesconto : metodosDeDesconto) {
            formaDesconto.calcularDesconto(pedido);
        }
    }
}
