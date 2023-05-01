package br.com.base.Compras.model;


import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="Fornecedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedores implements Serializable {//essa classe implementa o serializable
    private static final long serialVersionUID = 1L; //definição do id do serializable, conversao de objetos java para bytes
    //para serem salvos no banco de dados.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;

    @Column(nullable=false, unique=true, length=15)
    private String telefone;

    @Column(nullable=false, unique=true, length=14)
    private String cnpj;

    @Column(nullable=false, unique=true, length=15)
    private String rua;

    @Column(nullable=false, unique=true, length=15)
    private String bairro;

    @Column(nullable=false, unique=true, length=5)
    private String numero;

    @Column(nullable=false, unique=true, length=20)
    private String cidade;

    @Column(nullable=false, unique=true, length=20)
    private String estado;


}
