package com.modulodecompras.modulo.Resources;


import com.modulodecompras.modulo.DTO.PedidoDTO;
import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Services.NotFoundExcecion.EntityNotFoundException;
import com.modulodecompras.modulo.Services.PedidosService;
import com.modulodecompras.modulo.Services.dto.ProdutosPedidoDTO;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidosResource {
    @Autowired
    private PedidosService service;


    @PostMapping()
    public ResponseEntity<String> criarPedido(@RequestBody List<ProdutosPedidoDTO> listaProdutos){
        float valorCompra = service.calcularValor(listaProdutos);


        //agora deve rodar a validação da compra com o valorCompra

        boolean validade = true; //simulação

        if (validade){


            //Aqui deve ser criado o pedido no banco de dados

            service.salvarListaItemPedido(listaProdutos);//Após ser criado o pedido no banco de dados será criado a ligação dos pedidos e produtos por aqui.

            return ResponseEntity.status(HttpStatus.CREATED).body("Compra Autorizada e Pedido Criado com SUCESSO");
            
        }
        else {//caso seja negado a compra ele ja finaliza e nada é criado no banco de dados
            
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Compra Negada pelo Financeiro!");
            
        }
        
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>>fidAll(){
        List<PedidoDTO> list = service.fidAll();
        return  ResponseEntity.ok().body(list);

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity <PedidoDTO> finfById(@PathVariable Long id){
        PedidoDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);

    }
//    @PostMapping
//    public  ResponseEntity<PedidoDTO> insert(@RequestBody PedidoDTO dto){
//        dto = service.insert(dto);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(dto.getId()).toUri();
//        // cria um retorno de 201 que padrão de inserção
//        return  ResponseEntity.created(uri).body(dto);
//    }
    }

