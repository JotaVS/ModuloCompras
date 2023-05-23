package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.Services.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class EstoqueResource {
    @Autowired
    EstoqueService eServ;

    @GetMapping("/{id}/{qtde}")
    public ResponseEntity<?> getProdutoById(@PathVariable int id, @PathVariable int qtde){
        try{
            return ResponseEntity.ok(eServ.debitarProdutoPeloId(id,qtde));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
