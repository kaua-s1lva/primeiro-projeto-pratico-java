import java.util.Date;

import interfaces.IMetodoPagamento;
import metodosPagamento.CartaoDebito;
import models.Cliente;
import models.Item;
import models.Pedido;
import services.CalculadoraDeDescontoService;
import services.ControladorDeEstadosPedidoService;
import services.SistemaPagamentoService;

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

        SistemaPagamentoService sistemaPagamento = new SistemaPagamentoService();
        
        //IMetodoPagamento boleto = new Boleto();
        IMetodoPagamento cartaoDebito = new CartaoDebito("252161616", "12/24", 243, pedido.getCliente().getNome());
        
        sistemaPagamento.realizarPagamento(pedido, cartaoDebito, pedido.getValorPedido()); 
        System.out.println(pedido.toString());
    }
}
