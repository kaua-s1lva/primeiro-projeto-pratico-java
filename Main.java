import java.util.Date;

import models.Cliente;
import models.Item;
import models.Pedido;
import services.CalculadoraDeDescontoService;
import services.ControleDeEstadosPedidoService;

public class Main {
    public static void main (String[] args) {
        Cliente cliente = new Cliente("Kaua", "Ouro", 3, "rua vitorio albani", "Cidade Maravilhosa", "alegre");

        Item item = new Item("maça", 3, 2.5, "Alimentação");
        Item item2 = new Item("banana", 5, 2.5, "Educação");
        Item item3 = new Item("banana", 5, 2.5, "Alimentação");
        Item item4 = new Item("banana", 5, 2.5, "Lazer");
        Item item5 = new Item("banana", 5, 2.5, "Educação");
        Item item6 = new Item("banana", 5, 2.5, "Alimentação");

        Pedido pedido = new Pedido(new Date(), cliente, 11.0);
        pedido.adicionarItem(item);
        pedido.adicionarItem(item2);
        pedido.adicionarItem(item3);
        pedido.adicionarItem(item4);
        pedido.adicionarItem(item5);
        pedido.adicionarItem(item6);

        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.calcularDesconto(pedido);

        //RastrearPedido rastro = new RastrearPedido(pedido);

        //rastro.atualizarParaEmPreparo();
        //rastro.atualizarParaEmTransito();
        ControleDeEstadosPedidoService controle = new ControleDeEstadosPedidoService();

        controle.cvcv();


        System.out.println(pedido.toString());
    }
}
