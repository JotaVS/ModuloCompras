package br.com.base.Compras.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Produto")
public class Produtos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.Auto)

    private UUID id;
    @Column(nullable=false, unique=true, length=15)

    private String nome;
    @Column(nullable=false, unique=true, length=15)

    public String descrição;
    @Column(nullable=false, unique=true, length=30)

    public double valor;
    @Column(nullable=false, unique=true, length=15)


}
