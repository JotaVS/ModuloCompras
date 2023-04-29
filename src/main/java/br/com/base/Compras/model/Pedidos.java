package br.com.base.Compras.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedidos {

    private int id;

    private String nome;

    private Fornecedores fornecedor;

    private List<Produtos> produtos;
}
