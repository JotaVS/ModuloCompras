package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.ProdutoService;
import com.modulodecompras.modulo.Services.dto.ProdutoEstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {

    @Autowired
    ProdutoService pServ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produtos saveProdutos(@RequestBody @Valid Produtos produtos){
        return pServ.saveP(produtos);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> updateDisciplina(@PathVariable int id,
                                              @Valid @RequestBody Produtos produtos){
        try{
            return ResponseEntity.ok(pServ.updateProdutos(id, produtos));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaProduto(@PathVariable int id){
        try{
            return ResponseEntity.ok(pServ.apagarProduto(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProdutoById(@PathVariable int id){


        try{
            return ResponseEntity.ok(pServ.buscaProdutoPeloIdDto(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<Produtos>> getAllProduto(){
        return ResponseEntity.ok(pServ.buscaAllProduto());
    }
}
