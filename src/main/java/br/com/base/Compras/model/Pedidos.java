package br.com.base.Compras.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Pedido")
public class Pedidos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;

    @Column(nullable=false, unique=true, length=15)
    private String nome;

    @Column(nullable=false, unique=true, length=15)
    private Fornecedores fornecedor;

    @Column(nullable=false, unique=true, length=15)
    private List<Produtos> produtos;

}
