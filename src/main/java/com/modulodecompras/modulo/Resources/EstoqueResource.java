package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.Services.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueResource {
    @Autowired
    EstoqueService eServ;

    @Operation(description = "Api responsavel por retirar quantidade de produto do estoque!")
    @PostMapping("/debitar/{id}/{qtde}")
    public ResponseEntity<?> debitarProdutoById(@PathVariable Long id, @PathVariable int qtde){
        try{
            return ResponseEntity.ok(eServ.debitarProdutoPeloId(id,qtde));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(description = "Api responsavel por adicionar quantidade de produto no estoque!")
    @PostMapping("/adicionar/{id}/{qtde}")
    public ResponseEntity<?> adicionarProdutoById(@PathVariable Long id, @PathVariable int qtde) {
        return ResponseEntity.ok(eServ.adicionarProdutoPeloId(id, qtde));
    }

    @Operation(description = "Api responsavel por verificar disponibiliade de EPI no estoque!")
    @GetMapping("/verificar-epi/{matricula}/{idEPI}")
    public ResponseEntity<?> verificarEPIPorMatricula(@PathVariable String matricula, @PathVariable Long idEPI) {
        boolean possuiEPI = eServ.verificarEPIPorMatricula(matricula, idEPI);
        if (possuiEPI) {
            return ResponseEntity.ok("O EPI está disponível no estoque.");
        } else {
            return ResponseEntity.ok("O EPI não está disponível no estoque.");
        }
    }



}
