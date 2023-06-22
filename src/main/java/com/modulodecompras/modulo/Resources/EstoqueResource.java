package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.Services.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueResource {
    @Autowired
    EstoqueService eServ;

    @PostMapping("/debitar/{id}/{qtde}")
    public ResponseEntity<?> debitarProdutoById(@PathVariable int id, @PathVariable int qtde){
        try{
            return ResponseEntity.ok(eServ.debitarProdutoPeloId(id,qtde));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/adicionar/{id}/{qtde}")
    public ResponseEntity<?> adicionarProdutoById(@PathVariable int id, @PathVariable int qtde) {
        return ResponseEntity.ok(eServ.adicionarProdutoPeloId(id, qtde));
    }


}
