package services;

import formasDesconto.FormaDescontoTaxaPorBairro;
import formasDesconto.FormaDescontoTaxaPorTipoCliente;
import formasDesconto.FormaDescontoTipoItem;
import formasDesconto.FormaDescontoValorPedido;
import interfaces.IFormaDescontoTaxaEntrega;
import models.Pedido;

import java.util.ArrayList;

public class CalculadoraDeDescontoTaxaEntregaService {
    private ArrayList<IFormaDescontoTaxaEntrega> metodosDeDesconto = new ArrayList<>();

    public CalculadoraDeDescontoTaxaEntregaService() {
        metodosDeDesconto.add(new FormaDescontoTaxaPorBairro());
        metodosDeDesconto.add(new FormaDescontoTaxaPorTipoCliente());
        metodosDeDesconto.add(new FormaDescontoTipoItem());
        metodosDeDesconto.add(new FormaDescontoValorPedido());
    }

    public void addFormaDesconto(Pedido pedido) {
        for (IFormaDescontoTaxaEntrega formas : metodosDeDesconto) {
            formas.calcularDesconto(pedido);
        }
    }
}
