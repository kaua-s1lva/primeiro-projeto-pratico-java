import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main (String[] args) {
        Cliente cliente = new Cliente("Kaua", "Ouro", 3, "rua vitorio albani", "colina", "alegre");

        Item item = new Item("maça", 3, 2.5, "Alimentação");
        Item item2 = new Item("banana", 5, 2.5, "Alimentação");

        Pedido pedido = new Pedido(new Date(), cliente);
        pedido.adicionarItem(item);
        pedido.adicionarItem(item2);

        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService(pedido);

        calculadora.calcularDesconto(pedido);

        System.out.println(pedido.toString());
    }

    public static Map<String, Double> getTiposCliente() {
        Map<String, Double> tipoCliente = new HashMap<>(); 
        tipoCliente.put("Ouro", 3.00);
        tipoCliente.put("Prata", 2.00);
        tipoCliente.put("Bronze", 1.00);
        return tipoCliente;
    }
}
