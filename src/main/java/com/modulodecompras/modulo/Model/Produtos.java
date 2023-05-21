package com.modulodecompras.modulo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @NotNull
    private String nome;

//    @NotNull
    private String descricao;

    private float valorUnidade;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedores fornecedores;

    @OneToMany(mappedBy = "produtos")
    private List<Estoque> estoque;

    @ManyToMany(mappedBy = "produtosped")
    private List<Pedido> pedido;

}

