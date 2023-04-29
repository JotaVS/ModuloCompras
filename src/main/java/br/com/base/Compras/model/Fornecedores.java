package br.com.base.Compras.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedores {

    private int id;

    private String telefone;

    private String cnpj;

    private String endereço;

}
