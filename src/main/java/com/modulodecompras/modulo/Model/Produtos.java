package com.modulodecompras.modulo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {
    private Long id;
    private String Nome;
    private String Descricao;
    private String fornedor;

}
