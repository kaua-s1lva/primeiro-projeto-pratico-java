import java.util.Date;

import formasDescontoValorPedido.FormaDescontoCodCupomValorPedido;
import formasDescontoValorPedido.FormaDescontoTipoClienteValorPedido;
import formasDescontoValorPedido.FormaDescontoTipoItemValorPedido;
import interfaces.IMetodoPagamento;
import metodosPagamento.CartaoDebito;
import models.Cliente;
import models.Item;
import models.Pedido;
import services.CalculadoraDeDescontoTaxaEntregaService;
import services.CalculadoraDeDescontoValorPedidoService;
import services.ControladorDeEstadosPedidoService;
import services.SistemaPagamentoService;

public class Main {
    public static void main (String[] args) {
        Cliente cliente = new Cliente("Kaua", "Ouro", 3, "rua vitorio albani", "Cidade Maravilhosa", "alegre");

        Item item = new Item("maça", 3, 2.5, "Alimentação");

        Pedido pedido = new Pedido(new Date(), cliente, 10.0);
        pedido.adicionarItem(item);

        CalculadoraDeDescontoTaxaEntregaService calculadora = new CalculadoraDeDescontoTaxaEntregaService();

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

        CalculadoraDeDescontoValorPedidoService calculadoraValorPedido = new CalculadoraDeDescontoValorPedidoService();

        try{
            calculadoraValorPedido.aplicarDesconto(pedido, new FormaDescontoCodCupomValorPedido("DESC10"));
            calculadoraValorPedido.aplicarDesconto(pedido, new FormaDescontoTipoItemValorPedido());
            calculadoraValorPedido.aplicarDesconto(pedido, new FormaDescontoTipoClienteValorPedido());
        } catch (RuntimeException e) {
            System.out.println("Falha: " + e);
        }
        System.out.println(pedido.toString());
    }
}
