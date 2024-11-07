import java.util.ArrayList;

public class CalculadoraDeDescontoService {
    private ArrayList<IFormaDescontoTaxaEntrega> metodosDeDesconto = new ArrayList<>();

    public CalculadoraDeDescontoService() {
        metodosDeDesconto.add(new FormaDescontoTaxaPorBairro());
        metodosDeDesconto.add(new FormaDescontoTaxaPorTipoCliente());
        metodosDeDesconto.add(new FormaDescontoTipoItem());
        metodosDeDesconto.add(new FormaDescontoValorPedido());
    }

    public ArrayList<CupomDescontoEntrega> calcularDesconto(Pedido pedido) {
        ArrayList<CupomDescontoEntrega> cuponsDescontoEntrega = new ArrayList<>();
        for (int i=0; i<metodosDeDesconto.size(); i++) {
            if (metodosDeDesconto.get(i).seAplica(pedido)) {
                cuponsDescontoEntrega.add(metodosDeDesconto.get(i).calcularDesconto(pedido));
            }
        }
        return cuponsDescontoEntrega;
    }
}
