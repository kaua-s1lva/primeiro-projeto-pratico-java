import java.util.Date;

public class Main {
    public static void main (String[] args) {
        Cliente cliente = new Cliente("Kaua", "Ouro", 3, "rua vitorio albani", "colina", "alegre");

        Item item = new Item("maça", 3, 2.5, "Alimentação");
        Item item2 = new Item("banana", 5, 2.5, "Alimentação");

        Pedido pedido = new Pedido(new Date(), cliente);
        pedido.adicionarItem(item);
        pedido.adicionarItem(item2);

        pedido.aplicarDesconto();

        System.out.println(pedido.toString());
    }
}
