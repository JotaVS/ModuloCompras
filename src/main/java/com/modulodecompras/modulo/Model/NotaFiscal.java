package com.modulodecompras.modulo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table()
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int codNota;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;


}
