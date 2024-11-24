import java.util.Date;

import models.Cliente;
import models.Item;
import models.Pedido;
import services.CalculadoraDeDescontoService;
import services.ControladorDeEstadosPedidoService;

public class Main {
    public static void main (String[] args) {
        Cliente cliente = new Cliente("Kaua", "Ouro", 3, "rua vitorio albani", "Cidade Maravilhosa", "alegre");

        Item item = new Item("maça", 3, 2.5, "Alimentação");

        Pedido pedido = new Pedido(new Date(), cliente, 10.0);
        pedido.adicionarItem(item);

        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.addFormaDesconto(pedido);

        ControladorDeEstadosPedidoService controladorEstados = new ControladorDeEstadosPedidoService(pedido);

        try {
            controladorEstados.preparar(pedido);
            controladorEstados.finalizarPreparo(pedido);
            controladorEstados.entregar(pedido);
        } catch (RuntimeException e) {
            System.out.println("Falha: " + e.getMessage());
        }

        System.out.println(pedido.toString());
    }
}
