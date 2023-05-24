package com.modulodecompras.modulo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table()
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int valor;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private NotaFiscal notaFiscal;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido;

}

