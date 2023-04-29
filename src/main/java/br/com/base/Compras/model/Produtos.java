package br.com.base.Compras.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {

    private int id;

    private String nome;

    public String descrição;

    public double valor;


}
